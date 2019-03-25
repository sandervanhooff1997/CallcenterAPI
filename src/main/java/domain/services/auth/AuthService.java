package domain.services.auth;

import domain.models.Employee;
import domain.repositories.employee.IEmployeeRepository;
import domain.services.jwt.IJWTService;
import domain.utils.AuthenticationUtils;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.logging.Logger;

@Local
@Stateless
@Default
public class AuthService implements IAuthService {
    protected Logger logger = Logger.getLogger(this.getClass().getName());

    @EJB
    IJWTService jwtService;

    @EJB
    IEmployeeRepository employeeRepository;

    @Override
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

        employeeRepository.save(employee);

        return true;
    }

    @Override
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

        Employee e = employeeRepository.getByEmailAndPassword(email, password);

        if (e == null)
            return null;

        // create JWT token
        return jwtService.createJWT(e);
    }
}
