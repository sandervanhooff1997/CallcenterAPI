package domain.models;

import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "Subscription.getById", query = "select s from Subscription s where s.id = :id"),
        @NamedQuery(name = "Subscription.getAll", query = "select s from Subscription s")
})
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Max(100)
    private String name;

    private float monthPrice;

    private Date fromDate;

    private Date toDate;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    @ManyToOne
    private Call customer;

    @ManyToOne
    private Product product;

    public Subscription() {
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

    public float getMonthPrice() {
        return monthPrice;
    }

    public void setMonthPrice(float monthPrice) {
        this.monthPrice = monthPrice;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
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

    public Call getCustomer() {
        return customer;
    }

    public void setCustomer(Call customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", monthPrice=" + monthPrice +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", created=" + created +
                ", updated=" + updated +
                ", customer=" + customer +
                ", product=" + product +
                '}';
    }
}
