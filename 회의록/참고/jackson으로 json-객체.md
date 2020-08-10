## jackson으로 json<->객체

<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
			<version>2.8.8</version>
		</dependency>

---



// JSON user.email
			// com.fasterxml.jackson.databind.ObjectMapper 인스턴스 생성
			ObjectMapper objectMapper = new ObjectMapper(); // create once, reuse

			// 파일로 저장
			// objectMapper.writeValue(new File("파일명.json"), 객체);
			// String으로 저장
			String userJson = objectMapper.writeValueAsString(user);// 객체를 json으로
			// token, { "email" : "tpfla8859@naver.com","nickname":"rim"}
//			System.out.println("json바꿔보자 : " + userJson);
//			System.out.println("token을 보자 : " + tokenstr);

			// json --> object
			// data 읽어오기 : file, url, string 방식
			User jsonToUser = objectMapper.readValue(userJson, User.class);
			System.out.println(jsonToUser.getEmail()); // serim@naver.com
			String useremail = jsonToUser.getEmail();
			String usernickname = jsonToUser.getNickname();