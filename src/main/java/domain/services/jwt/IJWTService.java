package domain.services.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import domain.models.Employee;

import java.util.Date;

public interface IJWTService {
    String createJWT(Employee e) throws JWTVerificationException;

    void verifyJWT(String token) throws JWTVerificationException;

    Date getExpireDate(int daysToAdd);
}
