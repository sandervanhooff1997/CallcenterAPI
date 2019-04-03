package domain.models;

import com.sun.istack.NotNull;
import domain.models.Auth.Employee;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Company.getById", query = "select c from Company c where c.id = :id"),
        @NamedQuery(name = "Company.getAll", query = "select c from Company c")
})
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Min(1)
    @Max(100)
    private String name;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    @OneToMany(targetEntity = Employee.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List employees;

    @OneToMany(targetEntity = Product.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List products;

    public Company() {
    }

    public Company(String name, List employees, List products) {
        this.name = name;
        this.employees = employees;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List getEmployees() {
        return employees;
    }

    public void setEmployees(List employees) {
        this.employees = employees;
    }

    public List getProducts() {
        return products;
    }

    public void setProducts(List products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", employees=" + employees +
                ", products=" + products +
                '}';
    }
}
