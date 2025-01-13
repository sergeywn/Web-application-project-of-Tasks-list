package mapper;

import dto.AccountDto;
import entity.AccountEntity;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ReadUserMapper implements Mapper<AccountEntity, AccountDto> {

    public static final ReadUserMapper INSTANCE = new ReadUserMapper();

    @Override
    public AccountDto mapFrom(AccountEntity object) {
        return AccountDto.builder()
                .id(object.getId())
                .accountName(object.getAccountName())
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .roleAdmin(object.getRoleAdmin())
                .email(object.getEmail())
                .build();
    }

    public static ReadUserMapper getInstance() {
        return INSTANCE;
    }

}
