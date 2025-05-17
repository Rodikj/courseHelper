package mk.ukim.finki.coursehelper.model;

import jakarta.persistence.*;

@Entity
public class DocumentChunk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long FileId;

    private String chunk_text;

    private String chunk_index; //not sure

    public DocumentChunk() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public File getFile() { return file; }

    public void setFile(File file) { this.file = file; }

    public String getChunk_text() { return chunk_text; }

    public void setChunk_text(String chunk_text) { this.chunk_text = chunk_text; }

    public int getChunk_index() { return chunk_index; }

    public void setChunk_index(int chunk_index) { this.chunk_index = chunk_index; }
}
