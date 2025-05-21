package mk.ukim.finki.coursehelper.model;

import jakarta.persistence.*;

@Entity
public class ConversationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String video;

    private String toolCalls;

    private String toolResults;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public ConversationHistory() {
    }

    public ConversationHistory(Long id, String role, String content, Course course) {
        this.id = id;
        this.role = role;
        this.content = content;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getToolCalls() {
        return toolCalls;
    }

    public void setToolCalls(String toolCalls) {
        this.toolCalls = toolCalls;
    }

    public String getToolResults() {
        return toolResults;
    }

    public void setToolResults(String toolResults) {
        this.toolResults = toolResults;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
