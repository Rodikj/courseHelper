package mk.ukim.finki.coursehelper.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
<<<<<<< HEAD
<<<<<<< HEAD
@DiscriminatorValue("FILE")
public class File extends Source {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "course_id")
//    private Course course;
=======
public class File {

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
@DiscriminatorValue("FILE")
public class File extends Source {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "course_id")
//    private Course course;
>>>>>>> 1742606 (changes with FileController and some processing ai files)

    @Column(nullable = false, length = 32)
    private String md5;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_type", nullable = false)
    private String fileType;

    @Column(name = "upload_date", nullable = false)
    private LocalDate uploadDate;

    @Column(nullable = false)
    private boolean processed;

    public File() {}
<<<<<<< HEAD
=======

<<<<<<< HEAD
    public Long getId() {
        return id;
    }
>>>>>>> cc5b632 (modified everything)

    // getters & setters

=======
    // getters & setters

>>>>>>> 1742606 (changes with FileController and some processing ai files)
//    public Long getId() {
//        return id;
//    }
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public User getUser() {
//        return user;
//    }
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Course getCourse() {
//        return course;
//    }
//    public void setCourse(Course course) {
//        this.course = course;
//    }

    public String getMd5() {
        return md5;
    }
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }
    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public boolean isProcessed() {
        return processed;
    }
    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}
