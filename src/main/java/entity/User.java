package entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})
public class User {
    private Long id;

    private String firstName;

    private String lastName;

    private int age;

    private Sex sex;

    private UserStatus userStatus;
}


