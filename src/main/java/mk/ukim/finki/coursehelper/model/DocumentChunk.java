// package mk.ukim.finki.coursehelper.model;

// import jakarta.persistence.*;

// @Entity
// public class DocumentChunk {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

// //    @ManyToOne
// //    @JoinColumn(name = "file_id", nullable = false)
// //    private File file;

//     @ManyToOne
//     @JoinColumn(name = "source_id")
//     private Source source;

// //     @ManyToOne
// //     @JoinColumn(name = "source_id")
// //     private Source source;

// //     private String chunk_text;

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
