package ru.powergleb.task.tracker.frontend.tasklogic.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.powergleb.task.tracker.frontend.tasklogic.model.dto.TaskDTO;
import ru.powergleb.task.tracker.frontend.tasklogic.model.dto.UserDTO;
import ru.powergleb.task.tracker.frontend.tasklogic.model.Task;
import ru.powergleb.task.tracker.frontend.security.model.User;

@Component
public class MapperUtil {

    private final ModelMapper modelMapper;

    @Autowired
    public MapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public TaskDTO convertToTaskDTO(Task task) {
        return modelMapper.map(task, TaskDTO.class);
    }

    public Task convertToTask(TaskDTO taskDTO) {
        return modelMapper.map(taskDTO, Task.class);
    }
}
