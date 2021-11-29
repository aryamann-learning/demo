package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.User;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	public List<User> listAllUser() {
		List<UserEntity> lst = userRepository.findAll();
		List<User> lstUsers = new ArrayList<>();
		for (UserEntity userEntity : lst) {
			lstUsers.add(userEntity.todto());
		}
		return lstUsers;
	}

	public void saveUser(User user) {
		UserEntity uen = new UserEntity();
		uen.setFirstName(user.getFirstName());
		uen.setLastName(user.getLastName());
		uen.setId(user.getId());
		userRepository.save(uen);
	}

	public void saveUsers(List<User> users) {
		List<UserEntity> lst = new ArrayList<>();
		for (User user : users) {
			UserEntity uen = new UserEntity();
			uen.setFirstName(user.getFirstName());
			uen.setLastName(user.getLastName());
			uen.setId(user.getId());
			lst.add(uen);
		}
		userRepository.saveAll(lst);
	}

	public User getUser(Integer id) {
		return userRepository.findById(id).get().todto();
	}

	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}
}
