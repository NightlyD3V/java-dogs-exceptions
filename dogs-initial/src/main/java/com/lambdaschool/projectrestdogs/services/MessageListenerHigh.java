//package com.lambdaschool.projectrestdogs.services;
//
//import com.lambdaschool.projectrestdogs.ProjectrestdogsApplication;
//import com.lambdaschool.projectrestdogs.model.MessageDetail;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MessageListenerHigh
//{
//    public static final Logger logger = LoggerFactory.getLogger(MessageListenerHigh.class);
//
//    @RabbitListener(queues = ProjectrestdogsApplication.PRIORITY_QUE_HIGH)
//    public void receiveMessage(MessageDetail msg)
//    {
//        logger.info("We just received a message: " + msg.toString());
//    }
//}
