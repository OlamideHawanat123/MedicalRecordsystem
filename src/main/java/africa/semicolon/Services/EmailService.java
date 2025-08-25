package africa.semicolon.Services;

import africa.semicolon.data.models.Doctors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private JavaMailSenderImpl mailSender;

    public void sendVerificationEmail(String toEmail, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your email verification code\n");
        message.setText("Your email verification code is: " + code + """
                 Please, go back to the website and enter the code you received\s
                \s""");
        message.setFrom("raheemhawanat@gmail.com");
        mailSender.send(message);

    }

    public void sendVerificationToDoctor(Doctors doctors) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(doctors.getEmail());
        message.setSubject("Verification Complete");
        message.setText("Dear Dr. " + doctors.getFirstName() + ",\n\n" +
                "Congratulations! Your medical license has been successfully verified" + "\n\n" +
                "You can now access all the features available to verified doctors.\n\n" +
                "Best regards, \n" +
                "The Medical Records Team");
        mailSender.send(message);
    }

    public void sendRejectionEmailToDoctor(Doctors doctors) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(doctors.getEmail());
        message.setSubject("Verification Complete");
        message.setText("Dear Dr. " + doctors.getFirstName() + ",\n\n" +
                "We're sad to announce that you failed the verification check" + "\n\n" +
                "If you still want to join us, you should register again with a valid license.\n\n" +
                "Best regards, \n" +
                "The Medical Records Team");
        mailSender.send(message);
    }

    public void sendDeletionEmailToAdmin(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Deletion of Account");
        message.setText(
                "Your account has been removed" + "\n\n" +
                        "You no longer have access to the platform\n\n" +
                        "Best regards, \n" +
                        "The Medical Records Team");
        mailSender.send(message);
    }

    public void sendAccountDeletionToDoctor(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Deletion of Account");
        message.setText(
                "Your account has been removed" + "\n\n" +
                        "You no longer have access to the platform\n\n" +
                        "Best regards, \n" +
                        "The Medical Records Team");
        mailSender.send(message);    }


    public String generateVerificationCode() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

}
