package by.clientbase.diplomclientbasec19onl.dto;

import by.clientbase.diplomclientbasec19onl.entity.Client;
import by.clientbase.diplomclientbasec19onl.entity.User;
import by.clientbase.diplomclientbasec19onl.enums.Status;
import lombok.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TaskDTO {

    private long id;

    private User creator;

    private User performer;

    private Client client;

    @Pattern(regexp = "^[a-zA-Z_.]*$")
    private String description;

    private Status status;

    private LocalDate createdAt;



}
