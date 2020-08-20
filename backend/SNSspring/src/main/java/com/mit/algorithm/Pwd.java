package com.mit.algorithm;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/*
 * Pwd 는 랜덤의 6자리의 숫자를 반환해주는 함수입니다.
 * 술한잔 먹었습니다... 랜덤함수가 잘안되도 좋습니다..
 * 함수가 별로 일 수도 있습니다. 밤낮으로 고민하고 작성했습니다.
 * 하지만 그거 하나만 그거 하나만 기억해주십시오. 진심을 다해 전합니다.
 * */

public class Pwd {

	public String getRnadomcode() {
		StringBuilder sb = new StringBuilder();

		for (int seq = 0; seq < 6; seq++) {
			// 0~3의 값
			int pick = (int) (Math.random() * 3);

			switch (pick) {
			case 0:
				// 0~9 숫자
				sb.append((int) (Math.random() * 10));
				break;
			case 1:
				// a~z
				sb.append((char) ((int) (Math.random() * 26 + 65)));
				break;
			case 2:
				// A~z
				sb.append((char) ((int) (Math.random() * 26 + 97)));
				break;

			}

		}
		return sb.toString();
	}

	private static final String FROM_ADDRESS = "myitteam2020@gmail.com";

	public boolean sedEmail(String email, String certification, JavaMailSender mailSender) {
		System.out.println("go");
		System.out.println(certification);

		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(email);
			message.setFrom(FROM_ADDRESS);
			message.setSubject("SNS 비밀번호찾기 안내 메일입니다.");
			message.setText("인증 번호 안내메일 입니다.\n인증번호 [" + certification + "]  입니다 \n\n");
			// System.out.println(pwd+","+address);
			mailSender.send(message);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
			return false;
		}
	}
}
