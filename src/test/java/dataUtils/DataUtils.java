package dataUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataUtils {
	
	
	public static List<Map<String, String>> getData(String fileName)
			throws StreamReadException, DatabindException, IOException {

		String filePath = "src/test/resources/data/" + fileName + ".json";
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, String>> dataList = mapper.readValue(new File(filePath),
				new TypeReference<List<Map<String, String>>>() {
				});
		return dataList;
	}

}
