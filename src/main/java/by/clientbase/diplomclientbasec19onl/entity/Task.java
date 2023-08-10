package by.clientbase.diplomclientbasec19onl.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author Denis Smirnov on 14.06.2023
 */
@Entity
@Table(name = "tasks")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = User.class)
    private User creator;

    @ManyToOne(targetEntity = User.class)
    private User performer;

    @ManyToOne(targetEntity = Client.class)
    private Client client;


    /*@Enumerated(EnumType.STRING)
    private Status status;*/

    @Column(nullable = false)
    private String description;


}
