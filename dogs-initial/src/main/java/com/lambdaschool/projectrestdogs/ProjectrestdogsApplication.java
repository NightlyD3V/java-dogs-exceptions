package com.lambdaschool.projectrestdogs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableScheduling
@SpringBootApplication
public class ProjectrestdogsApplication
{
    public static DogList ourDogList;
    public static final String EXCHANGE_NAME = "Lambda";
    public static final String PRIORITY_QUE_HIGH = "PRIORITY_QUE_HIGH";
    public static final String PRIORITY_QUE_LOW = "PRIORITY_QUE_LOW";
    public static void main(String[] args)
    {
        ourDogList = new DogList();
        SpringApplication.run(ProjectrestdogsApplication.class, args);
    }

    @Bean
    public TopicExchange appExchange()
    {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue appQueHigh()
    {
        return new Queue(PRIORITY_QUE_HIGH);
    }

    @Bean
    public Queue appQueLow()
    {
        return new Queue(PRIORITY_QUE_LOW);
    }

    @Bean
    public Binding declareBindingHigh() {

        return BindingBuilder.bind(appQueLow()).to(appExchange()).with(PRIORITY_QUE_HIGH);

    }

    @Bean
    public Binding declareBindingLow() {

        return BindingBuilder.bind(appQueLow()).to(appExchange()).with(PRIORITY_QUE_LOW);

    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {

        return new Jackson2JsonMessageConverter();

    }
}

