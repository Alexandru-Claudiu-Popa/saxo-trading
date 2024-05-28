package dev.saxo.trading.controller;

import dev.saxo.trading.dto.AccountDto;
import dev.saxo.trading.errors.AccountNotFoundException;
import dev.saxo.trading.errors.web.SaxoResponse;
import dev.saxo.trading.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @Operation(
            summary = "Registers / creates a new account",
            description = "Creates a new client account for trading",
            operationId = "addAccount"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Account created",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SaxoResponse.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "This account already exists",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SaxoResponse.class))
                    }
            )
    })
    @PostMapping
    public void create(@RequestBody AccountDto accountDto) {
        accountService.create(accountDto);
    }

    @Operation(
            summary = "Retrieve an account by it's secret key",
            description = "Finds a client account using the key as parameter (secret key is considered as an ID here)",
            operationId = "findAccount"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the account",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AccountDto.class))
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Invalid key supplied",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SaxoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Account not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SaxoResponse.class)))
    })
    @GetMapping(path = "/{secretKey}")
    public ResponseEntity<AccountDto> get(@PathVariable String secretKey) throws AccountNotFoundException {
        accountService.dailyRisk(secretKey);
        return ResponseEntity.ok(accountService.findDtoById(secretKey));
    }
}
