package dev.saxo.trading.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.saxo.trading.errors.SymbolNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class MarketPriceSimulatorService {

    private final Map<String, Price> pricesMap = new HashMap();

    public MarketPriceSimulatorService(ResourceLoader resLoader) throws IOException {

        Resource resource = resLoader.getResource("classpath:prices.json");

        Price[] pricesList;
        ObjectMapper objectMapper = new ObjectMapper();
        pricesList = objectMapper.readValue(resource.getFile(), Price[].class);
        for (Price price: pricesList) {
            pricesMap.put(price.getSymbol(), price);
        }
    }

    private final Random random = new Random();

    public double newPrice(double buyPrice) {
        return buyPrice * (1 + (double) ((random.nextInt(40)) - 20) / 100);
    }

    public BigDecimal initialSellPrice(String symbol) {

        if (!pricesMap.containsKey(symbol)) {
            throw new SymbolNotFoundException(symbol);
        }
        return pricesMap.get(symbol).getSell();
    }

    public BigDecimal initialBuyPrice(String symbol) {

        if (!pricesMap.containsKey(symbol)) {
            throw new SymbolNotFoundException(symbol);
        }
        return pricesMap.get(symbol).getBuy();
    }

}
