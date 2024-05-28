package dev.saxo.trading.controller;

import dev.saxo.trading.dto.OrderDto;
import dev.saxo.trading.errors.web.SaxoResponse;
import dev.saxo.trading.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(
            summary = "Registers / places a new order",
            description = "Process a new trading order trading",
            operationId = "processOrder"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Order processed (placed)",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SaxoResponse.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The specified account does not exists",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SaxoResponse.class))
                    }
            )
    })
    @PostMapping
    public ResponseEntity<SaxoResponse> processOrder(@RequestBody OrderDto orderDto) {
        orderService.processOrder(orderDto, false);
        return ResponseEntity.ok(SaxoResponse.builder().code(201).message("Success").build());
    }


    @Operation(
            summary = "Registers / places a new order for YESTERDAY (one day before)",
            description = "This is just for testing: places an order with a different date, just to have trades and orders with more than one CREATED_DATE",
            operationId = "processOrderYesterday"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Order processed (placed)",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SaxoResponse.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The specified account does not exists",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SaxoResponse.class))
                    }
            )
    })
    @PostMapping(path = "/yesterday")
    public ResponseEntity<SaxoResponse> processOrderYesterday(@RequestBody OrderDto orderDto) {
        orderService.processOrder(orderDto, true);
        return ResponseEntity.ok(SaxoResponse.builder().code(201).message("Success").build());
    }
}
