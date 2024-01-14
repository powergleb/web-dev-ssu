package ru.powergleb.task.tracker.backend.tasklogic.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ru.powergleb.task.tracker.backend.tasklogic.TaskService;
import ru.powergleb.task.tracker.backend.tasklogic.model.dto.TaskDTO;
import ru.powergleb.task.tracker.backend.tasklogic.model.Task;
import ru.powergleb.task.tracker.backend.security.model.User;
import ru.powergleb.task.tracker.backend.security.securityDetails.TaskTrackerUserDetails;
import ru.powergleb.task.tracker.backend.security.services.UserService;

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

