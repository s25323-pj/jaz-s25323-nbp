package com.SzyRosz.jazs25323nbp.controller;

import com.SzyRosz.jazs25323nbp.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/currency")
public class CurrencyControler {
    private final CurrencyService currencyService;

    public CurrencyControler(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Operation(summary = "Get average rate for currency", description = "Returns average rate for chosen time")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content),
            @ApiResponse(responseCode = "404", description = "Currency not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @GetMapping("/{currency}/{startDate}/{endDate}")
    public ResponseEntity<Double> getAverageRateForCurrency(
            @PathVariable String currency,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        BigDecimal averageRate = currencyService.getAverageRateForCurrency(currency, startDate, endDate);
        if (averageRate == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(averageRate.doubleValue());
    }
}
