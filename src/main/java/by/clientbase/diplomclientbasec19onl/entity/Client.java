package by.clientbase.diplomclientbasec19onl.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
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
    private Long id;

    @Column(unique = true, length = 40, nullable = false)
    private String clientName;

    @Column(nullable = false, length = 40)
    private String supervisorName;

    @Column(nullable = false, length = 40)
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Telephone> telephones;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses;




}