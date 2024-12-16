package advanced.libs;

import java.util.List;
import java.util.Map;

import com.framework.utils.ReadDB;

public class ReadDBData {

	public static void main(String[] args) {

		String query = "SELECT TITLE, CATEGORY.NAME AS CATEGORY , LANGUAGE.NAME AS LANGUAGE FROM CATEGORY\r\n"
				+ "JOIN\r\n"
				+ "FILM_CATEGORY\r\n"
				+ "ON\r\n"
				+ "CATEGORY.CATEGORY_ID = FILM_CATEGORY.CATEGORY_ID\r\n"
				+ "JOIN\r\n"
				+ "FILM\r\n"
				+ "ON\r\n"
				+ "FILM_CATEGORY.FILM_ID = FILM.FILM_ID\r\n"
				+ "JOIN\r\n"
				+ "LANGUAGE\r\n"
				+ "ON FILM.LANGUAGE_ID = LANGUAGE.LANGUAGE_ID\r\n"
				+ "WHERE CATEGORY.NAME = 'Horror'\r\n"
				+ "AND LANGUAGE.NAME='English'\r\n"
				+ "ORDER BY TITLE ASC\r\n"
				+ "LIMIT 10;";

		List<Map<String, String>> data = ReadDB.readData(query);
		System.out.println(data);
		
		System.out.println(data.get(2).get("title"));

	}

}
