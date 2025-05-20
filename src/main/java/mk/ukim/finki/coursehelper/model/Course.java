package mk.ukim.finki.coursehelper.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String course_name;

    //private String select_type_of_material;

//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
//    private List<File> files = new ArrayList<>();
//
//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
//    private List<VideoLink> videos = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Source> sources = new ArrayList<>();

    public Course() {}

    public Long getId() { return id; }

    //public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public String getCourse_name() { return course_name; }

    public void setCourse_name(String course_name) { this.course_name = course_name; }

//    public String getSelect_type_of_material() { return select_type_of_material; }
//
//    public void setSelect_type_of_material(String select_type_of_material) {
//        this.select_type_of_material = select_type_of_material;
//    }


//    public List<File> getFiles() {
//        return files;
//    }
//
//    public void setFiles(List<File> files) {
//        this.files = files;
//    }
//
//    public List<VideoLink> getVideos() {
//        return videos;
//    }
//
//    public void setVideos(List<VideoLink> videos) {
//        this.videos = videos;
//    }


    public void setId(Long id) {
        this.id = id;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }
}
