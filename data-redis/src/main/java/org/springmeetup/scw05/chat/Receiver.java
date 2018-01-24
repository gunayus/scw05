package org.springmeetup.scw05.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springmeetup.scw05.domain.Student;

@Component
public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    public void receiveMessage(String message) {
        LOGGER.info("Received <" + message + ">");
    }

    public void receiveStudent(Student student) {
        LOGGER.info("Received student : <" + student.getId() + ":" + student.getName() + ">");
    }
}
