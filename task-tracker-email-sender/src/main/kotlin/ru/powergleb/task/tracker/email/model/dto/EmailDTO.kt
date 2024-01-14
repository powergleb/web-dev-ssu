package ru.powergleb.task.tracker.email.model.dto

data class EmailDTO(
        val recipientAddress: String,
        val title: String,
        val text: String
)


