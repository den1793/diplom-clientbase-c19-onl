package by.clientbase.diplomclientbasec19onl.entity;

import lombok.*;

import javax.persistence.*;



@Entity
@Data
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    private String country;
    @ToString.Exclude
    private String city;
    @ToString.Exclude
    private String street;

    @Override
    public String toString() {
        return  country;
    }


}
