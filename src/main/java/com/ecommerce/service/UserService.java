package com.ecommerce.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.RoleRepository;
import com.ecommerce.dao.UserRepository;
import com.ecommerce.model.Role;
import com.ecommerce.model.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	public void initRolesAndUsers() {
		Role adminRole = new Role();
		adminRole.setRoleName("Admin");
		adminRole.setRoleDescription("Admin for an application");
		roleRepository.save(adminRole);
		
		Role userRole = new Role();
		userRole.setRoleName("User");
		userRole.setRoleDescription("Default role for new record");
		roleRepository.save(userRole);
		
		User user1 = new User();
		user1.setUserName("Ankita_G");
		user1.setUserFirstname("Ankita");
		user1.setUserLastName("Gurbhele");
		user1.setPassword(getEncodedPassword("anku123"));
		Set<Role> user1Role = new HashSet<Role>();
		user1Role.add(adminRole);
		user1.setRole(user1Role);
		userRepository.save(user1);
		
		User user2 = new User();
		user2.setUserName("Monika_R");
		user2.setUserFirstname("Monika");
		user2.setUserLastName("Ramteke");
		user2.setPassword(getEncodedPassword("mon123"));
		Set<Role> user2Role = new HashSet<Role>();
		user2Role.add(userRole);
		user2.setRole(user2Role);
		userRepository.save(user2);
		
	}
	
	public User registerNewUser(User user) {
		Role role = roleRepository.findById("User").get();
		Set<Role> roleSet = new HashSet<Role>();
		roleSet.add(role);
		user.setRole(roleSet);
		user.setPassword(getEncodedPassword(user.getPassword()));
		return userRepository.save(user);
	}
	
	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}
	
//	public User registerNewUser(User user) {
//		Role role =  roleRepository.findById("User").get();
//		Set<Role> userRoles =  new HashSet<Roles>();
//		userRoles.add(role);
//		user.setRole(userRoles);
//		user.setPassword(getEncodedPassword(user.getPassword()));
//		return userRepository.save(user);
//	}
//	
//	public String getEncodedPassword(String password) {
//		return passwordEncoder.encode(password);
//	}

}
