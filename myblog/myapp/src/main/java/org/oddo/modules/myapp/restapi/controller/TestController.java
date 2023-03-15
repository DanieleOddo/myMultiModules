package org.oddo.modules.myapp.restapi.controller;

import org.oddo.modules.myapp.exception.ApiFaultResponse;
import org.oddo.modules.myapp.exception.ApiRestException;
import org.oddo.modules.myapp.validation.ValidatingPicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private ValidatingPicService validatingPicService;

    public TestController(ValidatingPicService validatingPicService) {
        this.validatingPicService = validatingPicService;
    };

    @GetMapping("/{pic}")
    @Operation(summary = "Returns the Fact with the given pic", description = "Rule 1 - Don't overuse exceptions : No exception is used here. Usage of Optional to control the response.")
    @Parameter(name = "pic", description = "identifier of the legal entity", required = true)
    @ApiResponse(responseCode = "200", description = "Success. Fact returned.")
    @ApiResponse(responseCode = "204", description = "No Fact fpunded for legal entity indicate")
    @ApiResponse(responseCode = "400", description = "Not valid legal entity", content = @Content(schema = @Schema(implementation = ApiFaultResponse.class)))
    @ApiResponse(responseCode = "404", description = "Fact not found", content = @Content(schema = @Schema(implementation = ApiFaultResponse.class)))
    public ResponseEntity findByPic(@PathVariable String pic) {

        if (!validatingPicService.isPicValid(pic)) {

            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    // new ApiFaultResponse(HttpStatus.BAD_REQUEST, "Legal Entity Not Found",
                            // "DSFact: LEgalEntity Absent"));
                            throw ApiRestException.instance(HttpStatus.NO_CONTENT).message("No Valid PIC");
        }

        return ResponseEntity.ok().body("ciao");
    }

}
