package ru.powergleb.task.tracker.frontend.tasklogic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.powergleb.task.tracker.frontend.tasklogic.model.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByOwnerId(int ownerId);
}
