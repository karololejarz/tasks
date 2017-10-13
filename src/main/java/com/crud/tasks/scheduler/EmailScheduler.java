package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    @Autowired
    private SimpleEmailService emailService;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "Tasks: daily email";

    @Scheduled(cron = "0 0 10 * * *" /*fixedDelay = 10000*/)
    public void sendInformationEmail() {
        long size = taskRepository.count();

        String messageN = "Current number of tasks in database: " + size;
        String message1 = "There is only one task in the database";

        if (size!=1) {
           emailService.send(new Mail(adminConfig.getAdminMail(), adminConfig.getAdminMail(), SUBJECT,
                messageN));
        }
        else {
            emailService.send(new Mail(adminConfig.getAdminMail(), adminConfig.getAdminMail(), SUBJECT,
                    message1));
        }
    }
}
