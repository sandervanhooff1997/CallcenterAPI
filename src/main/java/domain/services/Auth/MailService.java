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
    public boolean send (String to, String subject, String text) {
        try {
            Email email = EmailBuilder.startingBlank()
                    .from("Callcenter", "info@callcenter.com")
                    .to(to, to)
                    .withSubject(subject)
                    .withHTMLText(text)
                    .buildEmail();

            MailerBuilder
                    .withSMTPServer("smtp.gmail.com", 587, "s.vanhooff1997@gmail.com", "Tuincentrum1997")
                    .buildMailer()
                    .sendMail(email);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
}
