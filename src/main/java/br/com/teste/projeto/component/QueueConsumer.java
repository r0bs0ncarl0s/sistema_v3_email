package br.com.teste.projeto.component;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.teste.projeto.dto.EmailDTO;

@Component
public class QueueConsumer {

	//@Autowired
	//private JavaMailSender javaMailSender;
    
	@RabbitListener(queues = {"${rabbitmq.queue.email}"})
	public void enviarEmailTexto(@Payload EmailDTO email) throws Exception {		
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom("robson@ig.com");
			simpleMailMessage.setTo(email.getEmailDestino());
			simpleMailMessage.setSubject(email.getTitulo());
			simpleMailMessage.setText(email.getMensagem());
			//javaMailSender.send(simpleMailMessage);
			System.out.println("Email enviado com sucesso para " +  email.getEmailDestino() + " às " + LocalDateTime.now() + " TÍTULO: " + email.getTitulo() + " MENSAGEM: " + email.getMensagem());
		}catch(Exception e) {
			System.out.println("Ocorreu um erro às "  + LocalDateTime.now() + " ao tentar enviar o email para a caixa postal " + email.getEmailDestino());
		}
	}
}