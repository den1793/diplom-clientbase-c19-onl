package by.clientbase.diplomclientbasec19onl.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author Denis Smirnov on 14.06.2023
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Telephone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @Override
    public String toString() {
        return  number;
    }
}
