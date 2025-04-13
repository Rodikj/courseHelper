package mk.ukim.finki.coursehelper.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class VideoLink
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //user?
    private Long UserId;

    //video link to course relation unclear

    //...
}
