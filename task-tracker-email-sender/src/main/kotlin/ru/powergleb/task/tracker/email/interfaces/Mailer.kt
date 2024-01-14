package ru.powergleb.task.tracker.email.interfaces

interface Mailer {
    fun send(recipientAddress: String, title: String, text: String)
}