package by.clientbase.diplomclientbasec19onl.entity;

import by.clientbase.diplomclientbasec19onl.enums.Status;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "tasks")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "performer_id", nullable = false)
    private User performer;

    @ManyToOne(targetEntity = Client.class)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Enumerated(EnumType.STRING)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Status status;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate createdAt;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Attachment> attachments = new ArrayList<>();


    @Override
    public String toString() {
        return "Task: " + '\n' +
                "createdAt: " + createdAt + '\n' +
                "client: " + client.getClientName() + '\n' +
                "supervisor: " + client.getSupervisorName() + '\n' +
                "phone: " + client.getTelephones() + '\n' +
                "address: " + client.getAddresses() + '\n' +
                "description: " + description;
    }
}
