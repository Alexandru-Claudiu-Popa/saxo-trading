package dev.saxo.trading.mapper;

import dev.saxo.trading.dto.OrderDto;
import dev.saxo.trading.entity.OrderEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderMapper {

    public OrderEntity orderDtoToOrderEntity(OrderDto orderDto) {

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setSymbol(orderDto.getSymbol());
        orderEntity.setBuySell(orderDto.getBuySell());
        orderEntity.setOrderType(orderDto.getOrderType());
        orderEntity.setPositionSize(orderDto.getPositionSize());
        orderEntity.setStrategy(orderDto.getStrategy());
        orderEntity.setSlPercentPerTrade(orderDto.getSlPercentPerTrade());

        return orderEntity;
    }

    public OrderDto orderEntityToOrderDto(OrderEntity orderEntity) {

        OrderDto orderDto = new OrderDto();

        orderDto.setSymbol(orderEntity.getSymbol());
        orderDto.setBuySell(orderEntity.getBuySell());
        orderDto.setOrderType(orderEntity.getOrderType());
        orderDto.setPositionSize(orderEntity.getPositionSize());
        orderDto.setStrategy(orderEntity.getStrategy());
        orderDto.setSlPercentPerTrade(orderEntity.getSlPercentPerTrade());

        return orderDto;
    }
}
