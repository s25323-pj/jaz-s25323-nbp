package com.SzyRosz.jazs25323nbp.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class NbpRate {
    private BigDecimal mid;

    public BigDecimal getMid() {
        return mid;
    }

    public void setMid(BigDecimal mid) {
        this.mid = mid;
    }
}
