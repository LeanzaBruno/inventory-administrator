package dev.kertz.inventory.repository;

import dev.kertz.inventory.model.VAT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface VATRepository extends JpaRepository<VAT, BigDecimal> { }
