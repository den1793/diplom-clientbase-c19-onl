package by.clientbase.diplomclientbasec19onl.dto;

import by.clientbase.diplomclientbasec19onl.entity.Address;
import by.clientbase.diplomclientbasec19onl.entity.Client;
import by.clientbase.diplomclientbasec19onl.entity.Telephone;
import by.clientbase.diplomclientbasec19onl.entity.User;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author Denis Smirnov on 21.06.2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ClientDTO {

    @Id
    private Long id;

    private String clientName;

    private String address;

    private String description;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Telephone> telephones;


}
