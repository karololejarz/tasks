package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
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
    private AdminConfig adminConfig;

    private static final String SUBJECT = "Tasks: daily email";

    @Scheduled(/*cron = "0 0 10 * * *"*/ fixedDelay = 10000)
    public void sendInformationEmail() {
        emailService.sendDailyTaskAmount(new Mail(adminConfig.getAdminMail(), adminConfig.getAdminMail(),SUBJECT, "Amount of daily tasks:"));
    }
}
