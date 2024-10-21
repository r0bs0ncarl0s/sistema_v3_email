package br.com.teste.projeto.security;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue}")
    String queuePrincipal;
    
    @Value("${rabbitmq.queue.log}")
    String queueLog;
    
    @Value("${rabbitmq.queue.email}")
    String queueEmail;

    @Value("${rabbitmq.exchange}")
    String exchangePrincipal;

    @Value("${rabbitmq.routingkey}")
    private String routingkeyPrincipal;

    @Value("${rabbitmq.routingkey.log}")
    private String routingkeyLog;
    
    @Value("${rabbitmq.routingkey.email}")
    private String routingkeyEmail;
    
    @Bean
    Queue queuePrincipal() {
        return new Queue(queuePrincipal, false);
    }

    @Bean
    DirectExchange exchangePrincipal() {
        return new DirectExchange(exchangePrincipal);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingkeyPrincipal);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}