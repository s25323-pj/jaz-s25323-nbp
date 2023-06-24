package com.SzyRosz.jazs25323nbp.service;

import com.SzyRosz.jazs25323nbp.model.NbpApiResponse;
import com.SzyRosz.jazs25323nbp.model.NbpRate;
import com.SzyRosz.jazs25323nbp.repository.CurrencyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class CurrencyService {
    private static final String NBP_API_URL = "http://api.nbp.pl/api/exchangerates/rates/{table}/{code}/{startDate}/{endDate}/?format=json";

    private final RestTemplate restTemplate;
    private final CurrencyRateRepository currencyRateRepository;


    @Autowired
    public CurrencyService(RestTemplate restTemplate, CurrencyRateRepository currencyRateRepository, CurrencyRateRepository currencyRateRepository1) {
        this.restTemplate = restTemplate;
        this.currencyRateRepository = currencyRateRepository1;
    }

    public BigDecimal getAverageRateForCurrency(String currency, LocalDate startDate, LocalDate endDate) {
        List<NbpRate> rates = getRatesForCurrency(currency, startDate, endDate);

        BigDecimal sumRate = rates.stream()
                .map(NbpRate::getMid)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal averageRate = BigDecimal.ZERO;
        if (!rates.isEmpty()) {
            averageRate = sumRate.divide(BigDecimal.valueOf(rates.size()), 4, RoundingMode.HALF_UP);
        }
        return averageRate;
    }

    private List<NbpRate> getRatesForCurrency(String currency, LocalDate startDate, LocalDate endDate) {
        String apiUrl = NBP_API_URL.replace("{currency}", currency)
                .replace("{startDate}", startDate.toString())
                .replace("{endDate}", endDate.toString());

        NbpApiResponse response = restTemplate.getForObject(apiUrl, NbpApiResponse.class);
        return response != null ? response.getRates() : Collections.emptyList();
    }
}
