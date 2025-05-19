// package mk.ukim.finki.coursehelper.model;

<<<<<<< HEAD
// import jakarta.persistence.*;

// @Entity
// public class DocumentChunk {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
=======
import jakarta.persistence.*;

@Entity
public class DocumentChunk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
>>>>>>> cc5b632 (modified everything)

<<<<<<< HEAD
// //    @ManyToOne
// //    @JoinColumn(name = "file_id", nullable = false)
// //    private File file;
=======
//    @ManyToOne
//    @JoinColumn(name = "file_id", nullable = false)
//    private File file;

    @ManyToOne
    @JoinColumn(name = "source_id")
    private Source source;
>>>>>>> 1742606 (changes with FileController and some processing ai files)

//     @ManyToOne
//     @JoinColumn(name = "source_id")
//     private Source source;

//     private String chunk_text;

<<<<<<< HEAD
//     private String chunk_index; //not sure

//     public DocumentChunk() {}

//     public Long getId() { return id; }

//     public void setId(Long id) { this.id = id; }

// //    public File getFile() { return file; }
// //
// //    public void setFile(File file) { this.file = file; }

//     public Source getSource() { return source; }
//     public void setSource(Source source) { this.source = source; }

//     public String getChunk_text() { return chunk_text; }

//     public void setChunk_text(String chunk_text) { this.chunk_text = chunk_text; }

//     public int getChunk_index() { return chunk_index; }

//     public void setChunk_index(int chunk_index) { this.chunk_index = chunk_index; }
// }
=======
    public DocumentChunk() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

//    public File getFile() { return file; }
//
//    public void setFile(File file) { this.file = file; }

    public Source getSource() { return source; }
    public void setSource(Source source) { this.source = source; }

    public String getChunk_text() { return chunk_text; }

    public void setChunk_text(String chunk_text) { this.chunk_text = chunk_text; }

    public int getChunk_index() { return chunk_index; }

    public void setChunk_index(int chunk_index) { this.chunk_index = chunk_index; }
}
>>>>>>> cc5b632 (modified everything)
