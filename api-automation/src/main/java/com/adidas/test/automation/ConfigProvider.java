package com.adidas.test.automation;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigProvider {
	
	 InputStream inputStream;
	
	 Map<String, String> map = new HashMap();
	 
	public Map<String, String> getPropValues() {
 
		try {
			Properties prop = new Properties();
			String propFileName = "APIDetail.properties";
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			inputStream = loader.getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
				
			}
			map.put("getURI", prop.getProperty("get_FindByStatus"));
			map.put("postURI", prop.getProperty("post_NewPetToStore"));
			map.put("putURI", prop.getProperty("put_UpdateAddedPet"));
			map.put("deleteURI", prop.getProperty("delete_deleteUpdatedPet"));
			
			
		}
			 catch (Exception e) {
					System.out.println("Exception: " + e);
				} finally {
					try {
						inputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		return map;
	}			

}
