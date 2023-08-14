package by.clientbase.diplomclientbasec19onl.dto;

import by.clientbase.diplomclientbasec19onl.entity.User;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Denis Smirnov on 13.08.2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Size(min = 3, max = 50)
    @Pattern(regexp = "([A-Za-z])*", message = "The firstname contains invalid characters")
    private String name;

    @Size(min = 3, max = 50)
    @Pattern(regexp = "([A-Za-z])*", message = "The lastname contains invalid characters")
    private String lastname;

    @Pattern(regexp = "\\w*", message = "The username contains invalid characters")
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "The password short or contain invalid characters")
    private String password;

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "The email field does not contain a valid email address")
    private String email;


    private String telephone;

    @Size(min = 3, max = 50)
    @Pattern(regexp = "([A-Za-z])*", message = "The position contains invalid characters")
    private String position;



}
