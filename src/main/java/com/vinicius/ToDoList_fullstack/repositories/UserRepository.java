package com.vinicius.ToDoList_fullstack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinicius.ToDoList_fullstack.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
