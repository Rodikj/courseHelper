package mk.ukim.finki.coursehelper.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor

public class RetrievalResult
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ids , more info

    //what does score mean in this instance
}
