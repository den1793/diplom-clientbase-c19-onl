package by.clientbase.diplomclientbasec19onl.dto;

import by.clientbase.diplomclientbasec19onl.entity.Address;
import by.clientbase.diplomclientbasec19onl.entity.Telephone;
import lombok.*;

import javax.validation.constraints.Pattern;
import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ClientDTO {

    private Long id;

    @Pattern(regexp = "\\w*", message = "The clientName contains invalid characters")
    private String clientName;

    @Pattern(regexp = "([A-Za-z])*", message = "The Supervisor contains invalid characters")
    private String supervisorName;

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "The email field does not contain a valid email address")
    private String email;


    private List<Address> addresses;


    private List<Telephone> telephones;


}
