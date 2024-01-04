package com.example.dojo.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.dojo.data.db.entity.TaskEntity
import com.example.dojo.core.Task
import com.example.dojo.data.db.entity.TABLE_NAME_TASK
import java.util.UUID

@Dao
interface TaskDao {

    @Query("SELECT * FROM $TABLE_NAME_TASK")
    fun findAll(): List<TaskEntity>

    @Query("SELECT * FROM $TABLE_NAME_TASK WHERE id = :taskId")
    fun findById(taskId: String): TaskEntity

    @Query("DELETE FROM $TABLE_NAME_TASK WHERE id = :taskId")
    fun delete(taskId: String)

    @Upsert
    fun update(taskEntity: TaskEntity)

    @Query("DELETE FROM $TABLE_NAME_TASK")
    fun deleteAll()

}

fun fixtureItems() = mutableListOf(
    Task(
        UUID.randomUUID().toString(),
        "https://avatars.githubusercontent.com/u/74172618?v=4",
        "Mr. Robot",
        "mrrobot",
        "Elliot is a young programmer who works as a cyber security engineer by day and a vigilante hacker by night."
    ),
    Task(
        UUID.randomUUID().toString(),
        "https://avatars.githubusercontent.com/u/31107814?v=4",
        "Paulo Guilherme",
        "pgsilva",
        "computer science student at USJT full stack developer at @itau, genin since 98’s"
    ),
    Task(
        UUID.randomUUID().toString(),
        "https://avatars.githubusercontent.com/u/89589526?v=4",
        "Miles Morales",
        "milesmorales",
        "Miles Morales is a character present in Marvel Comics, who first appeared in Ultimate Fallout #4."
    ),
    Task(
        UUID.randomUUID().toString(),
        "https://avatars.githubusercontent.com/u/34991755?v=4",
        "Bojack Horseman",
        "bojackhorseman",
        "Aren't you the horse from \"Horsin Around\"?"
    ),
    Task(
        UUID.randomUUID().toString(),
        "https://avatars.githubusercontent.com/u/5826579?v=4",
        "Rick Sanchez",
        "ricksanchez",
        "Rick Sanchez is one of the protagonists of the animated series Rick and Morty. Rick is a mad and amoral scientist who, after disappearing for 20 years, returns to his daughter."
    ),
    Task(
        UUID.randomUUID().toString(),
        "https://avatars.githubusercontent.com/u/30023467?v=4",
        "Morty Smith",
        "mortysmith",
        "Morty is Rick Sanchez's grandson and was often forced to tag along on his various misadventures as a sidekick whilst attending Harry Herpson High School along with his sister."
    )
)