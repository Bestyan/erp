package fachlich;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fachlich.BapiFactory.BapiType;

public class Test {
	public static void main(String [] args)
	{
		SapModel model = new SapModel("915", "GBI-0-18", "123456");
		Map<String, Object> parameters = new HashMap<>();
		List<Map<String, String>> matnrSelection = new ArrayList<>();
		Map<String, String> row = new HashMap<>();
		row.put("SIGN", "I");
		row.put("OPTION", "CP");
		row.put("MATNR_LOW", "ERPKÖ*");
		matnrSelection.add(row);
		parameters.put("MATNRSELECTION", matnrSelection);
		Map<String, Object> result = model.executeBapi(BapiType.GETLIST, parameters);
		int i = 0;
	}
}
