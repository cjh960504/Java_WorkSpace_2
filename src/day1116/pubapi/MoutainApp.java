package day1116.pubapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MoutainApp {
	public static void main(String[] args) throws IOException {
		String apiKey = "60D4xttztUtyyB5E22qBL2BI7G%2Fm71JUCI4cPDnzkT8x6RVUlfGjiDMNrfM8J%2FbXSzDEY7NUc12Vo69h7MnQBw%3D%3D";

		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.forest.go.kr/openapi/service/trailInfoService/getforeststoryservice"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + apiKey); /* Service Key */
		urlBuilder.append("&" + URLEncoder.encode("mntnNm", "UTF-8") + "=" + URLEncoder.encode("지리산", "UTF-8")); /**/
		urlBuilder.append("&" + URLEncoder.encode("mntnHght", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /**/
		urlBuilder.append("&" + URLEncoder.encode("mntnAdd", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /**/
		urlBuilder
				.append("&" + URLEncoder.encode("mntnInfoAraCd", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /**/
		urlBuilder
				.append("&" + URLEncoder.encode("mntnInfoSsnCd", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /**/
		urlBuilder
				.append("&" + URLEncoder.encode("mntnInfoThmCd", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /**/
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
	}
}
