package ru.powergleb.task.tracker.frontend.tasklogic.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ru.powergleb.task.tracker.frontend.tasklogic.TaskService;
import ru.powergleb.task.tracker.frontend.tasklogic.model.dto.TaskDTO;
import ru.powergleb.task.tracker.frontend.tasklogic.model.Task;
import ru.powergleb.task.tracker.frontend.security.model.User;
import ru.powergleb.task.tracker.frontend.security.securityDetails.TaskTrackerUserDetails;
import ru.powergleb.task.tracker.frontend.security.services.UserService;

@RequiredArgsConstructor
public class TaskControllerValidate {

    protected final TaskService taskService;
    protected final UserService userService;

    protected void validateAccess(TaskDTO taskDTO, User user) {
        Task foundedTask = taskService.findById(taskDTO.getId());

        if (foundedTask.getOwner().getId() != user.getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }
    }

    protected void checkAuthorization(TaskTrackerUserDetails taskTrackerUserDetails) {
        if (taskTrackerUserDetails == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}

