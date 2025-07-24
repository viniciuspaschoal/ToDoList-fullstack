package com.vinicius.ToDoList_fullstack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinicius.ToDoList_fullstack.models.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser_Id(Long id);

    //Mistura com SQL
    //@Query(value = "SELECT t FROM Task t WHERE t.user.id = :id")
    //List<Task> findByUser_Id(@Param("id") Long id);

    //Usando SQL puro
    //@Query(value = "SELECT * FROM task t WHERE t.user_id = :id", nativeQuery = true)
    //List<Task> findByUser_Id(@Param("id") Long id);
}
