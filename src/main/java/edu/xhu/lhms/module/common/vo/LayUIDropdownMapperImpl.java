package edu.xhu.lhms.module.common.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LayUIDropdownMapperImpl {
    public static List<Map<String, Object>> stringListToDropdownData(List<String> inputList) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        for (int i = 0; i < inputList.size(); i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("title", inputList.get(i));
            item.put("id", i);
            resultList.add(item);
        }

        return resultList;
    }
}





