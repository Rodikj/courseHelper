package mk.ukim.finki.coursehelper.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
<<<<<<< HEAD
<<<<<<< HEAD
@DiscriminatorValue("VIDEO")
public class VideoLink extends Source{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "course_id")
//    private Course course;
=======
public class VideoLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
>>>>>>> cc5b632 (modified everything)
=======
@DiscriminatorValue("VIDEO")
public class VideoLink extends Source{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "course_id")
//    private Course course;
>>>>>>> 1742606 (changes with FileController and some processing ai files)

    private String yt_id_link;

    private String md5;

    private String file_name;

    private String file_type;

    private LocalDate upload_date;

    private boolean processed;

    public VideoLink() {}

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 1742606 (changes with FileController and some processing ai files)
//    public Long getId() { return id; }
//
//    public void setId(Long id) { this.id = id; }
//
//    public User getUser() { return user; }
//
//    public void setUser(User user) { this.user = user; }
//
//    public Course getCourse() { return course; }
//
//    public void setCourse(Course course) { this.course = course; }
<<<<<<< HEAD
=======
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Course getCourse() { return course; }

    public void setCourse(Course course) { this.course = course; }
>>>>>>> cc5b632 (modified everything)
=======
>>>>>>> 1742606 (changes with FileController and some processing ai files)

    public String getYt_id_link() { return yt_id_link; }

    public void setYt_id_link(String yt_id_link) { this.yt_id_link = yt_id_link; }

    public String getMd5() { return md5; }

    public void setMd5(String md5) { this.md5 = md5; }

    public String getFile_name() { return file_name; }

    public void setFile_name(String file_name) { this.file_name = file_name; }

    public String getFile_type() { return file_type; }

    public void setFile_type(String file_type) { this.file_type = file_type; }

    public LocalDate getUpload_date() { return upload_date; }

    public void setUpload_date(LocalDate upload_date) { this.upload_date = upload_date; }

    public boolean isProcessed() { return processed; }

    public void setProcessed(boolean processed) { this.processed = processed; }
}
