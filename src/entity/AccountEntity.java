package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountEntity {
    private Integer id;
    private String accountName;
    private String firstName;
    private String lastName;
    private String password;
    private Boolean roleAdmin;
    private String email;
}
