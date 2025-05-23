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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public LocalDate getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(LocalDate upload_date) {
        this.upload_date = upload_date;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}
