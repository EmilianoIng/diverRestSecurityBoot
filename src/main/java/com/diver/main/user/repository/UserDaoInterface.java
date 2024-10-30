package com.diver.main.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.diver.main.security.model.User;

//@Transactional
public interface UserDaoInterface /*extends JpaRepository<User, Integer>*/ {
	
public void addUser(User u);
public User findByUsername(String email);
public User update(User u);
public void deleteUser(User u);

}
