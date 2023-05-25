package com.ssafy.enjoytrip.domain.user.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.domain.user.dto.UserDto;
import com.ssafy.enjoytrip.domain.user.dto.UserDto.UserList;
import com.ssafy.enjoytrip.domain.user.entity.User;
import com.ssafy.enjoytrip.domain.user.entity.UserRole;
import com.ssafy.enjoytrip.domain.user.repository.UserRepository;
import com.ssafy.enjoytrip.global.BusinessException;
import com.ssafy.enjoytrip.global.code.ErrorInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	public void signUp(UserDto.SignUpRequest dto) {
		userRepository.findByLoginId(dto.getLoginId()).ifPresent((s)-> {
			throw new BusinessException(ErrorInfo.EXIST_LOGIN_ID);
			});
		
		User user = User.builder()
				.loginId(dto.getLoginId())
				.name(dto.getName())
				.email(dto.getEmail())
				.wrongCnt(0)
				.role(UserRole.USER)
				.password(passwordEncoder.encode(dto.getPassword()))
				.build();
		
		userRepository.save(user);
	}
	
	
	@Transactional
	public UserDto.AuthInfo signIn(UserDto.SignInRequest dto) {
		User user = userRepository.findByLoginId(dto.getLoginId()).orElseThrow(()-> new BusinessException(ErrorInfo.IS_NOT_VALID_LOGIN));
		
		if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
			throw new BusinessException(ErrorInfo.IS_NOT_VALID_LOGIN);
		}
		
		user.setLastLogin(LocalDateTime.now());
		user.setWrongCng(0);
		return new UserDto.AuthInfo(user.getUserId(), user.getLoginId(), user.getName(), user.getRole().getRole(), user.getProfileImgPath());
	}
	
	@Transactional
	public void updateUserInfo(Integer userId, String imgPath, String name) {
		User user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
		user.updateName(name);
		if(imgPath != null) {
			user.updateImg(imgPath);
		}
		
		userRepository.save(user);
	}
	
	@Transactional(readOnly=true)
	public UserList getUserList(String searchParams) {
		List<User> users = userRepository.findByEmailContains(searchParams);
		users.addAll(userRepository.findByNameContains(searchParams));
		return UserList.ofList(users);
	}
}
