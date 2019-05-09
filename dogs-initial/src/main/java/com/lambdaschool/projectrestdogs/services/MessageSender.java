package com.lambdaschool.projectrestdogs.services;

import com.lambdaschool.projectrestdogs.ProjectrestdogsApplication;
import com.lambdaschool.projectrestdogs.model.Dog;
import com.lambdaschool.projectrestdogs.model.MessageDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MessageSender
{
    private static RabbitTemplate rt;
    private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);

    public MessageSender(RabbitTemplate rt)
    {
        this.rt = rt;
    }

    public static void sendMessage()
    {
        for (Dog d : ProjectrestdogsApplication.ourDogList.dogList)
        {
            // our upper bound is 10 and is not inclusive i.e 0-9
            int priority = new Random().nextInt(10);
            boolean secret = new Random().nextBoolean();
            MessageDetail message = new MessageDetail(d.toString(), priority, secret);
            //priority
            if (priority <= 5)
            {
                logger.info("sent a message to high priority queue");
                rt.convertAndSend(ProjectrestdogsApplication.PRIORITY_QUE_HIGH, message);
            }
            else
            {
                logger.info("sent a message to low priority queue");
                rt.convertAndSend(ProjectrestdogsApplication.PRIORITY_QUE_LOW, message);
            }
        }
    }
}
