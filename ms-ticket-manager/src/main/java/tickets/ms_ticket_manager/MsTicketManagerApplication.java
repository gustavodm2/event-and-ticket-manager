package tickets.ms_ticket_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
@ComponentScan
public class MsTicketManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsTicketManagerApplication.class, args);
	}

}
