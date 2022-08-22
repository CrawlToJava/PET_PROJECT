package entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class User {

    private Long id;

    private String firstName;

    private String secondName;

    private int age;

    private Sex sex;

    private UserStatus userStatus;

}


