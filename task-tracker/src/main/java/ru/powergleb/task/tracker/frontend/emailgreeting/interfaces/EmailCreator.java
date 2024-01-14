package ru.powergleb.task.tracker.frontend.emailgreeting.interfaces;

import ru.powergleb.task.tracker.frontend.security.model.User;

public interface EmailCreator {

    String createEmailAddress(User user);

    String createEmailTitle(User user);

    String createEmailText(User user);
}
