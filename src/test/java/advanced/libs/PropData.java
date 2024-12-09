package advanced.libs;

import java.util.Properties;

import com.framework.utils.ReadProp;

public class PropData {

	public static void main(String[] args) {
		Properties prop = ReadProp.readData("Config.properties");		
		System.out.println(prop.getProperty("url"));
	}

}
