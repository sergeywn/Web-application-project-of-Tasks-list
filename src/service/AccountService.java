package service;

import dao.AccountDao;
import dto.AccountDto;
import mapper.ReadUserMapper;

import java.util.Optional;

public class AccountService {

    public static final AccountService INSTANCE = new AccountService();
    private final AccountDao accountDao = AccountDao.getInstance();
    private final ReadUserMapper readUserMapper = ReadUserMapper.getInstance();

    public Optional<AccountDto> login(String login, String password) {
        return accountDao.findByLoginAndPassword(login, password).map(readUserMapper::mapFrom);
    }

    public static AccountService getInstance() {
        return INSTANCE;
    }

}
