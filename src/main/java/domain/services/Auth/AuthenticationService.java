package domain.services.Auth;

import domain.models.Auth.Code;
import domain.models.Auth.Employee;
import domain.models.Auth.LoginResponse;
import domain.repositories.AuthRepository;
import domain.services.EmployeeService;
import domain.utils.AuthenticationUtils;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.Date;
import java.util.Random;
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

    @EJB
    AuthRepository repository;

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

    public LoginResponse login (Code code) {
        // get emp from db
        code.setEmployee(authenticateEmloyee(code.getEmployee()));

        if (code.getEmployee() == null)
            return new LoginResponse(false);

        Code registered = repository.getCodeVerified(code);

        // new device
        if (registered == null) {
            // generate code on successful login
            code = new Code(code.getEmployee(), getRandomCode(), code.getFingerPrint());

            // persist
            repository.createCode(code);

            // send mail with saved code
            boolean success = mailService.send(code.getEmployee().getEmail(), "Two factor authentication", "Code: <b>" + code.getCode() + "</b>");
            if (!success)
                return new LoginResponse(false);

            return new LoginResponse(true);
        }

        // existing device
        return new LoginResponse(true, jwtService.createJWT(registered.getEmployee()));

    }

    public String verify (Code code) {
        if (authenticateEmloyee(code.getEmployee()) == null)
            return null;

        // check if code exists in db
        code = repository.getCodeUnverified(code);

        if (code == null)
            return null;

        // check expired
        if (code.getExpireDate().getTime() < new Date().getTime())
            return null;

        // code verified
        code.setVerified(true);
        code.setVerifiedAt(new Date());

        // createRole JWT token
        return jwtService.createJWT(code.getEmployee());
    }

    private String getRandomCode () {
        int code = (int)(Math.random()*9000)+1000;
        return String.valueOf(code);
    }

    private Employee authenticateEmloyee (Employee employee) {
        if (employee.getEmail().isEmpty() || employee.getPassword().isEmpty())
            return null;

        try {
            // encode password with SHA256
            employee.setPassword(AuthenticationUtils.encodeSHA256(employee.getPassword()));
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return null;
        }

        return getByEmailAndPassword(employee.getEmail(), employee.getPassword());
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
