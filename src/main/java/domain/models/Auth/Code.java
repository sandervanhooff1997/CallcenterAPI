package domain.models.Auth;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "codes")
public class Code {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Employee employee;

    private String code;

    private boolean verified;

    private Date verifiedAt;

    private Date expireDate;

    private String fingerPrint;

    public Code() {    }

    public Code(Employee employee, String code, String fingerPrint) {
        this.employee = employee;
        this.code = code;
        this.verified = false;
        this.fingerPrint = fingerPrint;

        // 30mins expire date on codes
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MINUTE, 30);
        this.expireDate = c.getTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Date getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(Date verifiedAt) {
        this.verifiedAt = verifiedAt;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    @Override
    public String toString() {
        return "Code{" +
                "id=" + id +
                ", employee=" + employee +
                ", code='" + code + '\'' +
                ", verified=" + verified +
                ", verifiedAt=" + verifiedAt +
                ", expireDate=" + expireDate +
                ", fingerPrint='" + fingerPrint + '\'' +
                '}';
    }
}
