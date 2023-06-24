package com.SzyRosz.jazs25323nbp.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NbpApiResponse {
    private List<NbpRate> rates;

    public List<NbpRate> getRates() {
        return rates;
    }

    public void setRates(List<NbpRate> rates) {
        this.rates = rates;
    }
}
