package com.mit.algorithm;

import static org.junit.Assert.assertEquals;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import com.mit.dto.User;

import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;

public class Token {
	static Signer signer = HMACSigner.newSHA256Signer("mit team");
	static Signer ema = HMACSigner.newSHA256Signer("m");

	public String getToken(User user) {
		// Useremail로 토큰을 만든다.
		// plusMinutes 는 토큰을 등록하는 시간임 지금은 1분
		JWT jwt = new JWT().setIssuer(user.getEmail()).setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
				.setSubject("f1e33ab3-027f-47c5-bb07-8dd8ab37a2d3")
				.setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(100));
		// Sign and encode the JWT to a JSON string representation
		String token = JWT.getEncoder().encode(jwt, signer);

		return token;
	}

	public String getToken(String data) {
		// Useremail로 토큰을 만든다.
		// plusMinutes 는 토큰을 등록하는 시간임 지금은 1분
		JWT jwt = new JWT().setIssuer(data).setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
				.setSubject("f1e33ab3-027f-47c5-bb07-8dd8ab37a2d3")
				.setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(100));
		// Sign and encode the JWT to a JSON string representation
		String token = JWT.getEncoder().encode(jwt, signer);

		return token;
	}

	// 복호화 하는 방법 : 내이름 넣음
	// 토큰이 필요한 API 정보에 대해서 유효성을 체크해주면된다
	static Verifier verifier = HMACVerifier.newVerifier("mit team");
	static Verifier emafier = HMACVerifier.newVerifier("m");

	// Token이 유효하면 True 유효하지 않으면 False를 반환한다.
	// API를 받을때 유효한 토큰인지 함께 검사한다.
	public boolean cmpToekn(String token) {
		try {
			System.out.println(token);
			// Build an HMC verifier using the same secret that was used to sign the JWT
			JWT jwt = JWT.getDecoder().decode(token, verifier);

			assertEquals(jwt.subject, "f1e33ab3-027f-47c5-bb07-8dd8ab37a2d3");
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public String getEmail(String token) {

		JWT jwt = JWT.getDecoder().decode(token, verifier);
		return jwt.issuer;

	}

	// email 인증키 확인
	public boolean checkEmail(String code) {
		try {
			// Build an HMC verifier using the same secret that was used to sign the JWT
			JWT jwt = JWT.getDecoder().decode(code, emafier);

			assertEquals(jwt.subject, "f");
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// email 인증키 배포
	public String getEmailCode(String code) {
		JWT jwt = new JWT().setIssuer(code).setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC)).setSubject("f")
				.setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(3));
		// Sign and encode the JWT to a JSON string representation
		code = JWT.getEncoder().encode(jwt, ema);
		System.out.println("code = "+code);
		return code;
	}

}
