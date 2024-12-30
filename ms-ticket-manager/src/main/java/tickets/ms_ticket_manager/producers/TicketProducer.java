package tickets.ms_ticket_manager.producers;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tickets.ms_ticket_manager.entities.DTOs.EmailDTO;
import tickets.ms_ticket_manager.entities.Ticket;

@Component
public class TicketProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;


    public void publishMessageEmail(Ticket ticket){
        EmailDTO emailDTO = new EmailDTO();

        emailDTO.setEmailTo(ticket.getCustomerMail());
        emailDTO.setSubject("Cadastro realizado com sucesso!");
        emailDTO.setText(ticket.getCustomerName() + ", seja bem vindo(a)! \nAgradecemos o seu cadastro, aproveite agora todos os recursos da nossa plataforma!");
        rabbitTemplate.convertAndSend("", "default.email", emailDTO);

    }

}
