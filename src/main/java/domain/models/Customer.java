package domain.models;

import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Customer.getById", query = "select c from Customer c where c.id = :id"),
        @NamedQuery(name = "Customer.getAll", query = "select c from Customer c")
})
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    @OneToMany(targetEntity = Call.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List calls;

    public Customer() {
    }

    public Customer(String firstname, String lastname, List calls) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.calls = calls;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public List getCalls() {
        return calls;
    }

    public void setCalls(List calls) {
        this.calls = calls;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", calls=" + calls +
                '}';
    }
}
