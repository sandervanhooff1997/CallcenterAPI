package domain.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import domain.models.Employee;
import domain.repositories.EmployeeRepository;
import domain.utils.AuthenticationUtils;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Map;

@Local
@Stateless
public class AuthService extends BaseService {

    @EJB
    JWTService jwtService;

    @EJB
    EmployeeRepository employeeRepository;

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

        employeeRepository.create(employee);

        return true;
    }

    public String login (String email, String password) {
        if (email.isEmpty() || password.isEmpty())
            return null;

        Employee e = getByEmailAndPassword(email, password);

        if (e == null)
            return null;

        // create JWT token
        return jwtService.createJWT(e);
    }

    public Employee getById(Long id) {
        return employeeRepository.getById(id);
    }

    public Employee getByEmail(String email) {
        return employeeRepository.getByEmail(email);
    }

    public Employee getByEmailAndPassword(String email, String password) {
        return employeeRepository.getByEmailAndPassword(email, password);
    }
}
