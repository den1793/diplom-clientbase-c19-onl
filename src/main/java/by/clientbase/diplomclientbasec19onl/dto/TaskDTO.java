package by.clientbase.diplomclientbasec19onl.dto;

import by.clientbase.diplomclientbasec19onl.entity.Client;
import by.clientbase.diplomclientbasec19onl.entity.Status;
import by.clientbase.diplomclientbasec19onl.entity.User;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Denis Smirnov on 16.06.2023
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TaskDTO {

    @Id
    private Long id;

    @Size(min = 3, max = 20)
    @Pattern(regexp = "^[a-zA-Z_.]*$",
            message = "Creator name must contain only latin letters, underscores and dots!")
    private String creator;

    @Pattern(regexp = "^[a-zA-Z_.]*$",
            message = "Executor name must contain only latin letters, underscores and dots!")
    private String performer;
    @Pattern(regexp = "^[a-zA-Z_.]*$")
    private String client;

    @Pattern(regexp = "^[a-zA-Z_.]*$")
    private String description;

}
