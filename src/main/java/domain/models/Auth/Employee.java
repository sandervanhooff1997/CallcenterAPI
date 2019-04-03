package domain.models.Auth;

import com.sun.istack.NotNull;
import domain.models.Call;
import domain.models.Company;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Employee.getById", query = "select e from Employee e where e.id = :id"),
        @NamedQuery(name = "Employee.getByEmail", query = "select e from Employee e where e.email = :email"),
        @NamedQuery(name = "Employee.getByEmailAndPassword", query = "select e from Employee e where e.email = :email and e.password = :password"),
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

    @NotNull
    private String email;

    private String password;

    private boolean isAdmin;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    @OneToMany(targetEntity = Call.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List calls;

    @OneToMany(targetEntity = Role.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List roles;

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
        this.roles = new ArrayList();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validForRegistration () {
        return !this.email.isEmpty() && !this.password.isEmpty() && !this.firstname.isEmpty() && !this.lastname.isEmpty();
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void AssignRole (Role role) {
        this.roles.add(role);
    }

    public void RemoveRole (Role role) {
        this.roles.remove(role);
    }

    public List getRoles() {
        return roles;
    }

    public void setRoles(List roles) {
        this.roles = roles;
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
                ", isAdmin=" + isAdmin +
                '}';
    }
}
