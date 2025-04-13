package mk.ukim.finki.coursehelper.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor



public class User
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;


    public User(String name, String email)
    {
        this.name = name;
        this.email = email;
    }
}
