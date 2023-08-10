package by.clientbase.diplomclientbasec19onl.dto;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Denis Smirnov on 20.06.2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TelephoneDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String number;
}
