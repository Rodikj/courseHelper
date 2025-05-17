package mk.ukim.finki.coursehelper.model;

import jakarta.persistence.*;

@Entity
public class RetrievalResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "query_id", nullable = false)
    private Query query;

    @ManyToOne
    @JoinColumn(name = "chunk_id", nullable = false)
    private DocumentChunk documentChunk;

    private double score;

    public RetrievalResult() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Query getQuery() { return query; }

    public void setQuery(Query query) { this.query = query; }

    public DocumentChunk getDocumentChunk() { return documentChunk; }

    public void setDocumentChunk(DocumentChunk documentChunk) { this.documentChunk = documentChunk; }

    public double getScore() { return score; }

    public void setScore(double score) { this.score = score; }
}
