package domain.services.auth;

import domain.models.Employee;

public interface IAuthService {
    boolean register(Employee employee);
    String login(String email, String password);
}
