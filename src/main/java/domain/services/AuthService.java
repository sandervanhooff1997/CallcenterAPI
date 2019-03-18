package domain.services;

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

        if (getByEmail(employee.getEmail()) != null)
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
        logger.info("loggin in: " + email + " / " + password);

        if (email.isEmpty() || password.isEmpty())
            return null;

        Employee e = getByEmailAndPassword(email, password);
        logger.info("loggin in: " + e);

        if (e == null)
            return null;

        // emp found!
        Map<String, Object> claims = new HashMap<>();
        claims.put("employee", e);

        // create JWT token
        return jwtService.createJWT(claims);
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
