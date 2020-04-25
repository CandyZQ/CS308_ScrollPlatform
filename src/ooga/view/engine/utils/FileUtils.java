package ooga.view.engine.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtils {
	public static String loadAsString(String path, String separator) {
		StringBuilder result = new StringBuilder();
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(FileUtils.class.getResourceAsStream(path)))) {
			String line = "";
			while ((line = reader.readLine()) != null) {
				result.append(line).append(separator);
			}
		} catch (IOException e) {
			System.err.println("Couldn't find the file at " + path);
		}
		
		return result.toString();
	}
}