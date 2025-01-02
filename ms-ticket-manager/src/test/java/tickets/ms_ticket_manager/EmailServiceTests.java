package tickets.ms_ticket_manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import tickets.ms_ticket_manager.entities.Email;
import tickets.ms_ticket_manager.enums.StatusEmail;
import tickets.ms_ticket_manager.services.EmailService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmailServiceTests {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender emailSender;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendEmail() {
        Email emailModel = new Email();
        emailModel.setEmailTo("teste@exemplo.com");
        emailModel.setSubject("teste subject");
        emailModel.setText("test text");

        doNothing().when(emailSender).send(any(SimpleMailMessage.class));

        Email result = emailService.sendEmail(emailModel);

        verify(emailSender, times(1)).send(any(SimpleMailMessage.class));
        assertNotNull(result.getSendDateEmail());
        assertEquals(StatusEmail.SENT, result.getStatusEmail());
    }
}

