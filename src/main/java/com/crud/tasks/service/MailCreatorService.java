package com.crud.tasks.service;


import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.trello.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    @Qualifier
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("preview", "This is mail about creating trello card");
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("goodbye", "Have a nice day!");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("info.company.name", adminConfig.getCompanyName());
        context.setVariable("info.company.email", adminConfig.getCompanyEmail());
        context.setVariable("info.company.phone", adminConfig.getCompanyPhone());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildTaskNumberInfoEmail() {
        long taskNumber = taskRepository.count();

        Context context = new Context();
        context.setVariable("preview", "This is mail about number of tasks in Trello");
        context.setVariable("info.company.name", adminConfig.getCompanyName());
        context.setVariable("info.company.email", adminConfig.getCompanyEmail());
        context.setVariable("info.company.phone", adminConfig.getCompanyPhone());
        context.setVariable("admin_config", adminConfig);
        if (taskNumber == 0) {
            context.setVariable("number", "You can rest. You have no tasks");
        } else if (taskNumber == 1) {
            context.setVariable("number", "You have 1 tasks in your database. You'll do it quickly!");
        } else {
            context.setVariable("number", "You have " + taskNumber + " tasks in your database. Have fun at work");
        }
        return templateEngine.process("mail/task-number-mail", context);
    }
}