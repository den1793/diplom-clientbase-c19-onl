package by.clientbase.diplomclientbasec19onl.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author Denis Smirnov on 14.06.2023
 */
@Entity
@Table(name = "clients")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String clientName;


    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses;




}