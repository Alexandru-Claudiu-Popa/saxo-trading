package dev.saxo.trading.repo;

import dev.saxo.trading.entity.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface TradeRepository extends JpaRepository<TradeEntity, String> {

    @Query(
            value = "SELECT SUM(T.BUY_SELL_PRICE * O.POSITION_SIZE) FROM TRADES T LEFT JOIN ORDERS O ON T.ORDER_ID = O.ID WHERE T.SAXO_SECRET_KEY = ?1 AND T.STATUS='OPEN' AND CAST(T.OPEN_DATE_TIME AS DATE) = CURRENT_DATE",
            nativeQuery = true
    )
    BigDecimal getDailyExposure(String secretKey);

    @Query(
            value = "SELECT SUM(T.BUY_SELL_PRICE) FROM TRADES T LEFT JOIN ORDERS O ON T.ORDER_ID = O.ID WHERE T.SAXO_SECRET_KEY = ?1 AND T.STATUS='OPEN'",
            nativeQuery = true
    )
    BigDecimal getTotalExposure(String secretKey);
}
