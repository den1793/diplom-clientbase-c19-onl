package by.clientbase.diplomclientbasec19onl.entity;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Email {

    private String subject;
    private String from;
    private String to;
    private String text;


}
