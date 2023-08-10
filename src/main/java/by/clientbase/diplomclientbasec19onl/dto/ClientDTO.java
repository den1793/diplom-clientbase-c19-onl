package by.clientbase.diplomclientbasec19onl.dto;

import by.clientbase.diplomclientbasec19onl.entity.Telephone;
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
