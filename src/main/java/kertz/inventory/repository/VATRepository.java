package kertz.Supermarket.repository;

import kertz.Supermarket.model.VAT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface VATRepository extends JpaRepository<VAT, BigDecimal> { }
