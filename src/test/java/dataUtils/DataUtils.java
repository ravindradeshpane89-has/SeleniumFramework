package dataUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import test.CartPageTest;

public class DataUtils {
	
	private static final Logger logger = LogManager.getLogger(DataUtils.class);
	
	
	public static List<Map<String, String>> getData(String fileName)
			throws StreamReadException, DatabindException, IOException {
        logger.info("Reteriving the data from file "+fileName);
		String filePath = "src/test/resources/data/" + fileName + ".json";
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, String>> dataList = mapper.readValue(new File(filePath),
				new TypeReference<List<Map<String, String>>>() {
				});
		return dataList;
	}

}
