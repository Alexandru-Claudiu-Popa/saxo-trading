package dev.saxo.trading.service;

import dev.saxo.trading.dto.OrderDto;
import dev.saxo.trading.entity.AccountEntity;
import dev.saxo.trading.entity.OrderEntity;
import dev.saxo.trading.entity.TradeEntity;
import dev.saxo.trading.errors.DailyRiskException;
import dev.saxo.trading.errors.TotalRiskException;
import dev.saxo.trading.mapper.OrderMapper;
import dev.saxo.trading.repo.OrderRepository;
import dev.saxo.trading.repo.TradeRepository;
import dev.saxo.trading.util.MarketPriceSimulatorService;
import dev.saxo.trading.util.enums.OrderStatus;
import dev.saxo.trading.util.enums.TradeStatus;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {

    private final AccountService accountService;
    private final OrderRepository orderRepository;
    private final TradeRepository tradeRepository;

    private final OrderMapper orderMapper;

    private final MarketPriceSimulatorService priceSimulatorService;

    public void processOrder(OrderDto orderDto, boolean yesterday) {

        BigDecimal buyPrice = priceSimulatorService.initialBuyPrice(orderDto.getSymbol());
        BigDecimal sellPrice = priceSimulatorService.initialSellPrice(orderDto.getSymbol());

        BigDecimal dailyRisk = accountService.dailyRisk(orderDto.getSecret());
        BigDecimal totalRisk = accountService.maxRisk(orderDto.getSecret());

        AccountEntity accountEntity = accountService.findEntityById(orderDto.getSecret());

        switch (orderDto.getBuySell().toUpperCase()) {
            case "BUY":
                if (accountEntity.getDailyRisk().compareTo(buyPrice.multiply(BigDecimal.valueOf(orderDto.getPositionSize())).add(dailyRisk)) < 0) {
                    throw new DailyRiskException(orderDto.getSecret());
                }
                if (accountEntity.getMaxRisk().compareTo(buyPrice.multiply(BigDecimal.valueOf(orderDto.getPositionSize())).add(totalRisk)) < 0) {
                    throw new TotalRiskException(orderDto.getSecret());
                }
                break;
            case "SELL":
                if (accountEntity.getDailyRisk().compareTo(sellPrice.multiply(BigDecimal.valueOf(orderDto.getPositionSize())).add(dailyRisk)) < 0) {
                    throw new DailyRiskException(orderDto.getSecret());
                }
                if (accountEntity.getMaxRisk().compareTo(sellPrice.multiply(BigDecimal.valueOf(orderDto.getPositionSize())).add(totalRisk)) < 0) {
                    throw new TotalRiskException(orderDto.getSecret());
                }
                break;
            default:
        }

        OrderEntity orderEntity = orderMapper.orderDtoToOrderEntity(orderDto);
        orderEntity.setAccount(accountEntity);

        if (yesterday) {
            orderEntity.setCreatedTime(DateUtils.addDays(new Date(), -1).toInstant());
        } else {
            orderEntity.setCreatedTime(new Date().toInstant());
        }

        orderEntity.setStatus(OrderStatus.EXECUTED.name());
        orderEntity.setId(UUID.randomUUID().toString());
        orderRepository.save(orderEntity);

        TradeEntity tradeEntity = new TradeEntity();
        tradeEntity.setAccount(accountEntity);
        tradeEntity.setStatus(TradeStatus.OPEN.name());
        switch (orderDto.getBuySell().toUpperCase()) {
            case "BUY":
                tradeEntity.setBuySellPrice(buyPrice);
                break;
            case "SELL":
                tradeEntity.setBuySellPrice(sellPrice);
                break;
            default:
        }
        tradeEntity.setOrder(orderEntity);
        if (yesterday) {
            tradeEntity.setOpenDateTime(DateUtils.addDays(new Date(), -1).toInstant());
        } else {
            tradeEntity.setOpenDateTime(new Date().toInstant());
        }
        tradeEntity.setId(UUID.randomUUID().toString());
        tradeRepository.save(tradeEntity);
    }
}
