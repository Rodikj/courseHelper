package mk.ukim.finki.coursehelper.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class File
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id", unique = true)
    private Course course;

    private String md5;

    private String file_name;

    private String file_type;

    private LocalDate upload_date;

    private boolean processed;



}
