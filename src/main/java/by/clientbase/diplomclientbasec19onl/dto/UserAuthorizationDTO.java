package by.clientbase.diplomclientbasec19onl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

/**
 * @author Denis Smirnov on 15.06.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthorizationDTO {

    @Pattern(regexp = "\\w*", message = "The username contains invalid characters")
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "The password short or contain invalid characters")
    private String password;
}
