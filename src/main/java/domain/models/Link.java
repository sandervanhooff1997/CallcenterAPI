package domain.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "links")
public class Link {
    @Id
    @GeneratedValue
    private Long id;
    private String uri;
    private String rel;
    private String method;

    public Link(String uri, String rel, String method) {
        this.uri = uri;
        this.rel = rel;
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "Link{" +
                "uri='" + uri + '\'' +
                ", rel='" + rel + '\'' +
                '}';
    }
}
