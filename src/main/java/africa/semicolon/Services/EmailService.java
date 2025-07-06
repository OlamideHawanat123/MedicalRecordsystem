package africa.semicolon.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendVerificationEmail(String toEmail, String code){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your email verification code");
        message.setText("Your email verification code is: " + code);
        message.setFrom("Medical Record System");

    }

    private String generateVerificationCode(){
        return String.valueOf(1000000 + new Random().nextInt(9000000));
    }
}
