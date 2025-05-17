package mk.ukim.finki.coursehelper.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Embedding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "chunk_id")
    private DocumentChunk documentChunk;

    private String vector;

    private String model;

    private LocalDate embedding_date;

    public Embedding() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public DocumentChunk getDocumentChunk() { return documentChunk; }
    public void setDocumentChunk(DocumentChunk documentChunk) { this.documentChunk = documentChunk; }


    public String getVector() { return vector; }

    public void setVector(String vector) { this.vector = vector; }

    public String getModel() { return model; }

    public void setModel(String model) { this.model = model; }

    public LocalDate getEmbedding_date() { return embedding_date; }

    public void setEmbedding_date(LocalDate embedding_date) { this.embedding_date = embedding_date; }
}
