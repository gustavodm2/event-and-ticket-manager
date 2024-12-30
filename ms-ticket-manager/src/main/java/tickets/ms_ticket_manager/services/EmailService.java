package tickets.ms_ticket_manager.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tickets.ms_ticket_manager.entities.Email;
import tickets.ms_ticket_manager.enums.StatusEmail;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmailService {


    @Autowired
    JavaMailSender emailSender;

    @Transactional
    public Email sendEmail(Email emailModel) {
        emailModel.setSendDateEmail(LocalDateTime.now());

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(emailModel.getEmailTo());
        message.setSubject(emailModel.getSubject());
        message.setText(emailModel.getText());

        emailSender.send(message);

        emailModel.setStatusEmail(StatusEmail.SENT);

        return emailModel;
    }
}
