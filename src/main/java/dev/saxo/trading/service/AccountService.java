package dev.saxo.trading.service;

import dev.saxo.trading.dto.AccountDto;
import dev.saxo.trading.entity.AccountEntity;
import dev.saxo.trading.errors.AccountNotFoundException;
import dev.saxo.trading.mapper.AccountMapper;
import dev.saxo.trading.repo.AccountRepository;
import dev.saxo.trading.repo.TradeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class AccountService {

    private final AccountRepository accountRepository;
    private final TradeRepository tradeRepository;

    private final AccountMapper accountMapper;

    public void create(AccountDto accountDto) {
        AccountEntity accountEntity = accountMapper.accountDtoToEntity(accountDto);
        accountEntity.setCreatedTime(new Date().toInstant());
        accountRepository.save(accountEntity);
    }

    public AccountEntity findEntityById(String saxoSecretKey) throws AccountNotFoundException {
        Optional<AccountEntity> accountEntityOptional = accountRepository.findBySaxoSecretKey(saxoSecretKey);
        if (accountEntityOptional.isEmpty()) {
            throw new AccountNotFoundException(saxoSecretKey);
        }
        return accountEntityOptional.get();
    }

    public AccountDto findDtoById(String saxoSecretKey) throws AccountNotFoundException {
        AccountEntity accountEntity = findEntityById(saxoSecretKey);
        return accountMapper.accountEntityToDto(accountEntity);
    }

    public BigDecimal dailyRisk(String saxoSecretKey) {

        BigDecimal dailyExposure = tradeRepository.getDailyExposure(saxoSecretKey);
        if (dailyExposure == null) {
            return BigDecimal.ZERO;
        }

        return tradeRepository.getDailyExposure(saxoSecretKey);
    }

    public BigDecimal maxRisk(String saxoSecretKey) {

        BigDecimal totalExposure = tradeRepository.getTotalExposure(saxoSecretKey);
        if (totalExposure == null) {
            return BigDecimal.ZERO;
        }

        return tradeRepository.getTotalExposure(saxoSecretKey);
    }

}
