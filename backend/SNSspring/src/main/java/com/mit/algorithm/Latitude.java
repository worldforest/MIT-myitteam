package com.mit.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Latitude {

	public XY GetLatitude(String query) {
		XY xy = null;

		try {
			String text = URLEncoder.encode(query, "UTF-8");
			URL url = new URL("https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + text);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "zhffknk4nm"); // key값 설정
			con.setRequestProperty("X-NCP-APIGW-API-KEY", "ncodZz7ld5gFi76f1PifrtneEpvBQDCmHNRZ0hHF"); // key값 설정
			con.setDoOutput(true);
			StringBuilder sb = new StringBuilder();

			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// Stream을 처리해줘야 하는 귀찮음이 있음.
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
				br.close();
				// 위도경로값 쿼리문으로해서 가져오는걸 만든다.

				JSONParser jsonParser = new JSONParser();
				JSONObject obj = (JSONObject) jsonParser.parse(sb.toString());

				List<JSONObject> jsonObjects = (ArrayList<JSONObject>) obj.get("addresses");
				
				xy = new XY();
				xy.setX((String) jsonObjects.get(0).get("x"));
				xy.setY((String) jsonObjects.get(0).get("y"));

			} else {
			}

		} catch (Exception e) {
			System.err.println(e.toString());
		}

		return xy;

	}
}
