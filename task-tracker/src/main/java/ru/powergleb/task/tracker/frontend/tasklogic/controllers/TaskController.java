package ru.powergleb.task.tracker.frontend.tasklogic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.powergleb.task.tracker.frontend.tasklogic.TaskService;
import ru.powergleb.task.tracker.frontend.tasklogic.model.dto.TaskDTO;
import ru.powergleb.task.tracker.frontend.tasklogic.model.dto.UserDTO;
import ru.powergleb.task.tracker.frontend.tasklogic.utils.MapperUtil;
import ru.powergleb.task.tracker.frontend.security.securityDetails.TaskTrackerUserDetails;
import ru.powergleb.task.tracker.frontend.security.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:63342/")
@RestController
public class TaskController extends TaskControllerValidate {

    private final MapperUtil mapperUtil;

    @Autowired
    public TaskController(TaskService taskService, UserService userService, MapperUtil mapperUtil) {
        super(taskService, userService);
        this.mapperUtil = mapperUtil;
    }

    @GetMapping("/user")
    public UserDTO getUser(@AuthenticationPrincipal TaskTrackerUserDetails taskTrackerUserDetails) {
        this.checkAuthorization(taskTrackerUserDetails);

        return mapperUtil.convertToUserDTO(taskTrackerUserDetails.getUser());
    }

    @GetMapping("/tasks")
    public List<TaskDTO> getTasks(@AuthenticationPrincipal TaskTrackerUserDetails taskTrackerUserDetails) {
        this.checkAuthorization(taskTrackerUserDetails);

        return taskService.findByOwnerId(taskTrackerUserDetails.getUser().getId()).stream()
                .map(mapperUtil::convertToTaskDTO)
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/tasks", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<HttpStatus> addTask(@AuthenticationPrincipal TaskTrackerUserDetails taskTrackerUserDetails,
                                              @ModelAttribute TaskDTO taskDTO) {
        this.checkAuthorization(taskTrackerUserDetails);

        taskService.save(mapperUtil.convertToTask(taskDTO), userService.findByUserDetails(taskTrackerUserDetails));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping(path = "/tasks", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<HttpStatus> updateTask(@AuthenticationPrincipal TaskTrackerUserDetails taskTrackerUserDetails,
                                                 @ModelAttribute TaskDTO taskDTO) {
        this.checkAuthorization(taskTrackerUserDetails);
        this.validateAccess(taskDTO, taskTrackerUserDetails.getUser());

        taskService.update(mapperUtil.convertToTask(taskDTO), userService.findByUserDetails(taskTrackerUserDetails));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping(path = "/tasks", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<HttpStatus> deleteTask(@AuthenticationPrincipal TaskTrackerUserDetails taskTrackerUserDetails,
                                                 @ModelAttribute TaskDTO taskDTO) {
        this.checkAuthorization(taskTrackerUserDetails);
        this.validateAccess(taskDTO, taskTrackerUserDetails.getUser());

        taskService.delete(taskDTO.getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
