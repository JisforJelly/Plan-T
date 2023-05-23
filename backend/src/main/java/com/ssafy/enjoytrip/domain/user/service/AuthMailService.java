package com.ssafy.enjoytrip.domain.user.service;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.domain.user.entity.User;
import com.ssafy.enjoytrip.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthMailService {
	
	private final JavaMailSender javaMailSender;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	private static final String MAIL_SUBJECT = "[PLAN-T] 아이디/비밀번호 찾기 안내드립니다.";
	private static final String MAIL_GREETING = "<h2> 안녕하세요. 여행 가이드 PLAN-T 입니다.</h2> <br>";
	private static final String FIND_MAIL_HEADER = "아이디 찾기 결과 입니다.";
	private static final String FIND_PASSWORD_HEADER = "임시 비밀번호 입니다.";
	
	public boolean sendLoginId(String email) {
		String loginId = findLoginIdByEmail(email);
		boolean isExistUser = (loginId!=null);
		
		if(isExistUser) {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			try {
				MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
				mailHelper.setSubject(MAIL_SUBJECT);
				mailHelper.setTo(email);
				mailHelper.setText(this.makeMailBody(FIND_MAIL_HEADER, loginId), true);
				javaMailSender.send(mimeMessage);
			} catch(Exception e) {
				log.info(e.getMessage());
				throw new RuntimeException("메일 전송에 실패했습니다.");
			}
		}
		return isExistUser;
	}

	public boolean sendFindPasswordUrl(String loginId, String email) {
		String tmpPassword = RandomStringUtils.random(16, true, true);
		User user = userRepository.findByEmail(email);
		boolean isExistUser = (user!=null);

		if(isExistUser && user.getLoginId().equals(loginId)) {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			try {
				user.updatePassword(passwordEncoder.encode(tmpPassword));
				userRepository.save(user);

				MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
				mailHelper.setSubject(MAIL_SUBJECT);
				mailHelper.setTo(email);
				mailHelper.setText(this.makeMailBody(FIND_PASSWORD_HEADER, tmpPassword), true);
				javaMailSender.send(mimeMessage);
				return true;
			} catch(Exception e) {
				log.info(e.getMessage());
				throw new RuntimeException("메일 전송에 실패했습니다.");
			}
		}
		return false;
	}
	
	private String makeBoxContent(String headerText, String content) {
		StringBuilder sb = new StringBuilder();
		sb.append("<div align=\"center\" style=\"border:1px solid black; font-family:verdana;\">");
		sb.append("<h3 style=\"color:blue\">");
		sb.append(headerText).append("</h3>");
		sb.append(" <div style=\"font-size:130%\">");
		sb.append(content).append("</div>");
		sb.append("</div><br/>");
		return sb.toString();
	}
	
	private String makeMailBody(String header, String content) {
		StringBuilder sb = new StringBuilder();
		sb.append("<body>");
		sb.append("<div style='margin:100px;'>");
		sb.append(MAIL_GREETING);
		sb.append(this.makeBoxContent(header, content));
		sb.append("</div>");
		sb.append("</body>");
		return sb.toString();
	}

	private String findLoginIdByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user == null ? null : user.getLoginId();
	}
}
