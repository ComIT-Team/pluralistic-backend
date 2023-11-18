package org.comit.pluralisticsecurity.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.comit.pluralisticsecurity.dto.SellerRequest;
import org.comit.pluralisticsecurity.entity.Role;
import org.comit.pluralisticsecurity.entity.RoleEnum;
import org.comit.pluralisticsecurity.entity.Seller;
import org.comit.pluralisticsecurity.entity.User;
import org.comit.pluralisticsecurity.entity.UserRole;
import org.comit.pluralisticsecurity.repository.RoleRepository;
import org.comit.pluralisticsecurity.repository.SellerRepository;
import org.comit.pluralisticsecurity.repository.UserRepository;
import org.comit.pluralisticsecurity.repository.UserRoleRepository;
import org.comit.pluralisticsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SellerRepository sellerRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private RoleRepository roleRepository;



	public User findCurrentUserID(String currentPrincipalName) {

		return this.userRepository.findCurrentUserID(currentPrincipalName);
	}

	public void saveSellerDetails(SellerRequest sellerRequest, User user) {

		User currentUser_Seller = new User(user.getIdUser());
		Seller seller = new Seller(currentUser_Seller, sellerRequest.getSellerName(), sellerRequest.getInteracId());

		sellerRepository.save(seller);

	}

	public void changeRole(User user) {
		Role role = new Role(Integer.valueOf(RoleEnum.SELLER.ordinal()));
		userRoleRepository.changeRole(role.getIdRole(), user.getIdUser());

	}

	@Override
	public void deleteUser(Integer idUser) {

		userRoleRepository.deleteUser(idUser);

		userRepository.deleteById(idUser);

	}

	@Override
	public void deleteSeller(Integer idUser) {

		sellerRepository.deleteSeller(idUser);
		deleteUser(idUser);

	}

	@Override
	public List<User> getAllUsers() {

		List<User> usersList = new ArrayList<>();

		List<User> users = userRepository.findAll();

		for (User temp : users) {

			UserRole userRole = userRoleRepository.findRole(temp.getIdUser());

			Role role = roleRepository.findNameRole(userRole.getRole().getIdRole());

			if (role.getNameRole().equals("USER")) {

				usersList.add(temp);

			}
		}

		return usersList;
	}

	@Override
	public List<User> getAllSellers() {

		List<User> usersList = new ArrayList<>();

		List<User> users = userRepository.findAll();

		for (User temp : users) {

			UserRole userRole = userRoleRepository.findRole(temp.getIdUser());

			Role role = roleRepository.findNameRole(userRole.getRole().getIdRole());

			if (role.getNameRole().equals("SELLER")) {

				usersList.add(temp);

			}
		}

		return usersList;
	}
}
