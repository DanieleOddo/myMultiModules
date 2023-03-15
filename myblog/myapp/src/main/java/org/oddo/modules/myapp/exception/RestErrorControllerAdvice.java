package org.oddo.modules.myapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.micrometer.tracing.Tracer;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class RestErrorControllerAdvice extends ResponseEntityExceptionHandler {

        @Autowired
        private Tracer tracer;

        public RestErrorControllerAdvice(Tracer tracer) {
                this.tracer = tracer;
        }

        @ExceptionHandler(ApiRestException.class)
        private ResponseEntity<ApiFaultResponse> handleApiRestException(ApiRestException e, HttpServletRequest request,
                        HandlerMethod method) {
                String uniqueErrorId = (tracer.currentSpan() != null) ? tracer.currentSpan().context().spanId()
                                : null;
                logError(e, request, method, uniqueErrorId);

                ApiFaultResponse details = new ApiFaultResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                                "http://ec.europa.eu/digit/archcc/eh/internal-server-error", e.getMessage());
                details.put("uniqueErrorId", uniqueErrorId);

                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                                .body(details);
        }

        @ExceptionHandler(Exception.class)
        private ResponseEntity<ApiFaultResponse> catchAll(Exception e, HttpServletRequest request,
                        HandlerMethod method) {

                String uniqueErrorId = (tracer.currentSpan() != null) ? tracer.currentSpan().context().spanId()
                                : null;
                logError(e, request, method, uniqueErrorId);

                ApiFaultResponse details = new ApiFaultResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                                "http://ec.europa.eu/digit/archcc/eh/internal-server-error",
                                "Internal Server Error");
                details.put("uniqueErrorId", uniqueErrorId);

                return ResponseEntity
                                .status(details.getStatus())
                                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                                .body(details);
        }

        private void logError(Exception e, HttpServletRequest request, HandlerMethod method, String uniqueErrorId) {
                log.error("uniqueErrorId: {}, path: {}, controller: {}", uniqueErrorId, request.getServletPath(),
                                method.getShortLogMessage(), e);
        }

}
