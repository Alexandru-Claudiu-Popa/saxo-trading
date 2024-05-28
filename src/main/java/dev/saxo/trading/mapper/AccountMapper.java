package dev.saxo.trading.mapper;

import dev.saxo.trading.dto.AccountDto;
import dev.saxo.trading.entity.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountDto accountEntityToDto(AccountEntity accountEntity) {
        AccountDto accountDto = new AccountDto();
        accountDto.setName(accountEntity.getName());
        accountDto.setSaxoApiKey(accountEntity.getSaxoApiKey());
        accountDto.setSaxoSecretKey(accountEntity.getSaxoSecretKey());
        accountDto.setDailyRisk(accountEntity.getDailyRisk());
        accountDto.setMaxRisk(accountEntity.getMaxRisk());
        return accountDto;
    }

    public AccountEntity accountDtoToEntity(AccountDto accountDto) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setName(accountDto.getName());
        accountEntity.setSaxoApiKey(accountDto.getSaxoApiKey());
        accountEntity.setSaxoSecretKey(accountDto.getSaxoSecretKey());
        accountEntity.setDailyRisk(accountDto.getDailyRisk());
        accountEntity.setMaxRisk(accountDto.getMaxRisk());
        return accountEntity;
    }
}
