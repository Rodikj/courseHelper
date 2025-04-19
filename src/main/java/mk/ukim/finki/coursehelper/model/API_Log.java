package mk.ukim.finki.coursehelper.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor

public class API_Log
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String method;

    private int status_code;

    private LocalDateTime timestamp;

    //endpoint
}
