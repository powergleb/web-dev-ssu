package ru.powergleb.task.tracker.scheduler

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class TaskTrackerSchedulerApplication

fun main(args: Array<String>) {
    runApplication<TaskTrackerSchedulerApplication>(*args)
}
