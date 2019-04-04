package domain.services.Auth;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

@Local
@Stateless
@Default
public class MailService {
    public void send (String to, String text) {
        Email email = EmailBuilder.startingBlank()
                .from("Callcenter", "info@callcenter.com")
                .to("Sander van Hooff", "s.vanhooff@hotmail.com")
                .withSubject("Two Factor Authentication")
                .withPlainText("Your Callcenter verification code is: 0000")
                .buildEmail();

        MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, "s.vanhooff1997@gmail.com", "Tuincentrum1997")
                .buildMailer()
                .sendMail(email);
    }
}
