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
import org.springframework.stereotype.Component;

import com.mit.dto.User;

@Component
public class Latitude {
	String[] access = { "area1", "area2", "area3" };

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
				
				System.out.println(xy);
			} else {
				System.out.println("좌표 얻어오기 실패");
			}

		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}

		return xy;

	}

	public XY getCentterLatitude(List<XY> xys) {
		XY ret = new XY();
		System.out.println(xys.size());
		if (xys.size() == 0) {
			return null;
		}

		if (xys.size() == 1) {
			return xys.get(0);
		}
		
		double x = 0;
		double y = 0;
		double z = 0;

		for (XY xy : xys) {
			double latitude = Double.parseDouble(xy.getY()) * Math.PI / 180;
			double longitude = Double.parseDouble(xy.getX()) * Math.PI / 180;

			x += Math.cos(latitude) * Math.cos(longitude);
			y += Math.cos(latitude) * Math.sin(longitude);
			z += Math.sin(latitude);
		}

		double total = xys.size();

		x = x / total;
		y = y / total;
		z = z / total;

		double centralLongitude = Math.atan2(y, x);
		double centralSquareRoot = Math.sqrt(x * x + y * y);
		double centralLatitude = Math.atan2(z, centralSquareRoot);

//        return new GeoCoordinate(centralLatitude * 180 / Math.PI, centralLongitude * 180 / Math.PI);
		ret.setName("중간 지점");
		ret.setY(String.valueOf(centralLatitude * 180 / Math.PI));
		ret.setX(String.valueOf(centralLongitude * 180 / Math.PI));

		return ret;
	}

	public List<XY> getRecomendSite(XY center) {
		List<XY> list = new ArrayList<XY>();

		String origin = null;

		/*
		 * 여기서 부터는 카카오 API를 사용한다.
		 */
//		Authorization: KakaoAK ${APP_KEY}' 'https://dapi.kakao.com/v2/local/search/category.json?category_group_code=CE7&x=127.4012885&y=36.6194945&sort=accuracy'
		try {
			URL url = new URL("https://dapi.kakao.com/v2/local/search/category.json?category_group_code=CE7&x="
					+ center.x + "&y=" + center.y + "&sort=accuracy");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", "KakaoAK 9e6c4a2f8774add450d5031c03518e98"); // Appkey값 설정
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
				System.out.println(sb.toString());
//				JSONParser jsonParser = new JSONParser();
//				JSONObject obj = (JSONObject) jsonParser.parse(sb.toString());
//
//				List<JSONObject> jsonObjects = (ArrayList<JSONObject>) obj.get("addresses");


			} else {
				System.out.println("else");
			}

		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
		return list;
	}

}
