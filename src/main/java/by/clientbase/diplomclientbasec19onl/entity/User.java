package by.clientbase.diplomclientbasec19onl.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Denis Smirnov on 14.06.2023
 */
@Entity
@Table(name = "users")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, name = "name")
    private String name;

    @Column(length = 40, name = "last_name")
    private String lastname;

    @Column(unique = true, length = 250, nullable = false)
    private String username;

    @Column(nullable = false, length = 250)
    private String password;

    @Column(nullable = false, length = 250)
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Telephone> telephones;

    private String position;

    /*@Enumerated(EnumType.STRING)
    private Role role;*/
}

