package domain.models;

import com.sun.istack.NotNull;
import org.apache.bval.constraints.Email;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Employee.getById", query = "select e from Employee e where e.id = :id"),
        @NamedQuery(name = "Employee.getAll", query = "select e from Employee e")
})
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @Email
    @NotNull
    private String email;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    @OneToMany(targetEntity = Call.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List calls;

    @ManyToOne
    private Company company;

    public Employee() {
    }

    public Employee(String firstname, String lastname, String email, List calls, Company company) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.calls = calls;
        this.company = company;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", calls=" + calls +
                ", company=" + company +
                '}';
    }
}
