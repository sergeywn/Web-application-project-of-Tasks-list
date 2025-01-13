package dao;

import entity.AccountEntity;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.util.List;
import java.util.Optional;

public class AccountDao implements Dao<Integer, AccountEntity> {

    private static final AccountDao INSTANCE = new AccountDao();

    private static final String GET_BY_LOGIN_AND_PASSWORD_SQL = """
            SELECT t.id, t.account_name, t.first_name, t.last_name, t.pass, t.role_admin, t.email
            FROM family_app.public.accounts t
            where t.account_name = ? AND t.pass = ?
            """;

    @SneakyThrows
    public Optional<AccountEntity> findByLoginAndPassword (String login, String password) {
        try (var connection = ConnectionManager.open();
             var prepareStatement = connection.prepareStatement(GET_BY_LOGIN_AND_PASSWORD_SQL)) {
            AccountEntity accountEntity = null;

            connection.setAutoCommit(false);
            prepareStatement.setString(1, login);
            prepareStatement.setString(2, password);

            var resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                accountEntity = AccountEntity.builder()
                        .id(resultSet.getObject("id", Integer.class))
                        .accountName(resultSet.getObject("account_name",String.class))
                        .firstName(resultSet.getObject("first_name", String.class))
                        .lastName(resultSet.getObject("last_name", String.class))
                        .password(resultSet.getObject("pass", String.class))
                        .roleAdmin(resultSet.getObject("role_admin", Boolean.class))
                        .email(resultSet.getObject("email", String.class))
                        .build();
            }
            return Optional.ofNullable(accountEntity);
        }
    }

    @Override
    public List<AccountEntity> findAll() {
        return null;
    }

    @Override
    public List<AccountEntity> findById(List<Integer> k) {
        return null;
    }

    @Override
    public List<AccountEntity> add(List<AccountEntity> e) {
        return null;
    }

    @Override
    public boolean update(List<AccountEntity> e) {
        return false;
    }

    @Override
    public boolean delete(List<Integer> k) {
        return false;
    }


    public static AccountDao getInstance() {
        return INSTANCE;
    }
}
