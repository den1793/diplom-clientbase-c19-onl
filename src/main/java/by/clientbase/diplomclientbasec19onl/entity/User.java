package by.clientbase.diplomclientbasec19onl.entity;


import by.clientbase.diplomclientbasec19onl.enums.Role;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, name = "name")
    private String name;

    @Column(length = 40, name = "last_name")
    private String lastname;

    @Column(unique = true, length = 40)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Telephone> telephones;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> tasks;

    @Column(nullable = false)
    private String position;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

