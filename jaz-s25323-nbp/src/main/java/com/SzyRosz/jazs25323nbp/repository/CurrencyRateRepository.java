package com.SzyRosz.jazs25323nbp.repository;

import com.SzyRosz.jazs25323nbp.model.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {

}