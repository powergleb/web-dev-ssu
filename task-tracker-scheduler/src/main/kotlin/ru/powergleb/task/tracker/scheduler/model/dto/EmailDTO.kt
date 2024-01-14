package ru.powergleb.task.tracker.scheduler.model.dto

data class EmailDTO(
    val recipientAddress: String,
    val title: String,
    val text: String
)