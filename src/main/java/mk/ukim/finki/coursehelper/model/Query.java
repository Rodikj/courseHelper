// package mk.ukim.finki.coursehelper.model;

<<<<<<< HEAD
// import jakarta.persistence.*;
// import java.time.LocalDateTime;

// @Entity
// public class Query {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     //have to see how to implement user
=======
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Query {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
>>>>>>> cc5b632 (modified everything)

//     private String query_text;

//     private LocalDateTime timestamp;

//     public Query() {}

<<<<<<< HEAD
//     public Long getId() { return id; }

//     public void setId(Long id) { this.id = id; }

//     public User getUser() { return user; }

//     public void setUser(User user) { this.user = user; }

//     public String getQuery_text() { return query_text; }

//     public void setQuery_text(String query_text) { this.query_text = query_text; }

//     public LocalDateTime getTimestamp() { return timestamp; }

//     public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
// }
=======
    public Query() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public String getQuery_text() { return query_text; }

    public void setQuery_text(String query_text) { this.query_text = query_text; }

    public LocalDateTime getTimestamp() { return timestamp; }

    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
>>>>>>> cc5b632 (modified everything)
