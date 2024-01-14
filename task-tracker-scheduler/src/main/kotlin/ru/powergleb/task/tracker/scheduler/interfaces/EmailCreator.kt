package ru.powergleb.task.tracker.scheduler.interfaces

import ru.powergleb.task.tracker.scheduler.model.User

interface EmailCreator {
    fun createEmailAddress(user: User): String
    fun createEmailTitle(user: User): String
    fun createEmailText(user: User): String
}