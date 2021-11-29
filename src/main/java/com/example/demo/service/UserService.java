package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.User;

public interface UserService {

	public List<User> listAllUser();

	public void saveUser(User user);

	public void saveUsers(List<User> users);

	public User getUser(Integer id);

	public void deleteUser(Integer id);
}
