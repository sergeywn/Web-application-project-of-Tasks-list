package dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountDto {

    Integer id;
    String accountName;
    String firstName;
    String lastName;
    Boolean roleAdmin;
    String email;

}
