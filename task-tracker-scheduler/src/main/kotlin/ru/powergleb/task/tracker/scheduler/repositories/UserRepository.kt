package ru.powergleb.task.tracker.scheduler.repositories

import ru.powergleb.task.tracker.scheduler.model.User
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = ["tasks"])
    override fun findAll(): MutableList<User>
}