package mk.ukim.finki.coursehelper.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class File
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //user?
    private Long UserId;

    @ManyToOne
    private Course course;

    private String md5; //hash something

    private String filename;

    private String file_type;

    private String upload_date; // needs to be turned into Date

    private String processed; // we shall see



}
