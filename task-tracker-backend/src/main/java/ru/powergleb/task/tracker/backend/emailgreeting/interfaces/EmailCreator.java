package ru.powergleb.task.tracker.backend.emailgreeting.interfaces;

import ru.powergleb.task.tracker.backend.security.model.User;

public interface EmailCreator {

    String createEmailAddress(User user);

    String createEmailTitle(User user);

    String createEmailText(User user);
}
