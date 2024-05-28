package dev.saxo.trading.repo;

import dev.saxo.trading.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {
    Optional<AccountEntity> findBySaxoSecretKey(String secretKey);
}
