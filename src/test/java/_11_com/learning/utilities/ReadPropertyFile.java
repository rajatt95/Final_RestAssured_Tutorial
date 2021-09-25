package _11_com.learning.utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {

	static public String readConfigFile(String key) {

		String configFilePath = System.getProperty("user.dir") + "/src/test/resources/config/config.properties";
		Properties property = null;
		try {
			FileReader reader = new FileReader(configFilePath);
			property = new Properties();
			property.load(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return property.getProperty(key);
	}
}
