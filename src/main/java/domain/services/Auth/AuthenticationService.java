package domain.services.Auth;

import domain.models.Auth.Employee;
import domain.services.EmployeeService;
import domain.utils.AuthenticationUtils;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.logging.Logger;

@Local
@Stateless
@Default
public class AuthenticationService {
    protected Logger logger = Logger.getLogger(this.getClass().getName());

    @EJB
    JWTService jwtService;

    @EJB
    MailService mailService;

    @EJB
    EmployeeService employeeService;

    public boolean register (Employee employee) {
        if (employee == null)
            return false;

        if (!employee.validForRegistration())
            return false;

        try {
            // encode password with SHA256
            employee.setPassword(AuthenticationUtils.encodeSHA256(employee.getPassword()));
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return false;
        }

        employeeService.create(employee);

        return true;
    }

    public String login (String email, String password) {
        if (email.isEmpty() || password.isEmpty())
            return null;

        try {
            // encode password with SHA256
            password = AuthenticationUtils.encodeSHA256(password);
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return null;
        }

        Employee e = getByEmailAndPassword(email, password);

        if (e == null)
            return null;

        mailService.send("+31642112663", "Two Factor Authentication code: 000000");

        // create JWT token
        return jwtService.createJWT(e);
    }

    public Employee getById(Long id) {
        return employeeService.getById(id);
    }

    public Employee getByEmail(String email) {
        return employeeService.getByEmail(email);
    }

    public Employee getByEmailAndPassword(String email, String password) {
        return employeeService.getByEmailAndPassword(email, password);
    }
}
