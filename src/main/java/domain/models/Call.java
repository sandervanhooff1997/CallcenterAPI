package domain.models;

import domain.models.Auth.Employee;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Call.getById", query = "select c from Call c where c.id = :id"),
        @NamedQuery(name = "Call.getAll", query = "select c from Call c")
})
@Table(name = "calls")
public class Call {

    @Id
    @GeneratedValue
    private Long id;

    @Positive
    private int duration;

    @NotNull
    private Date date;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Employee employee;

    @OneToMany(targetEntity = Subscription.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List subscriptions;

    public Call() {    }

    public Call(@Positive int duration, Date date, Customer customer, Employee employee, List subscriptions) {
        this.duration = duration;
        this.date = date;
        this.customer = customer;
        this.employee = employee;
        this.subscriptions = subscriptions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Override
    public String toString() {
        return "Call{" +
                "id=" + id +
                ", duration=" + duration +
                ", date=" + date +
                ", created=" + created +
                ", updated=" + updated +
                ", customer=" + customer +
                ", employee=" + employee +
                ", subscriptions=" + subscriptions +
                '}';
    }
}
