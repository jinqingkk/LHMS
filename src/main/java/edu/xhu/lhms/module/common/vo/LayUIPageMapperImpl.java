package edu.xhu.lhms.module.common.vo;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class LayUIPageMapperImpl {
    public static <T> ResponseEntity<Map<String, Object>> toLayuiTable(Page<T> page) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", 0);
        resultMap.put("msg", "");
        resultMap.put("count", page.getTotalElements());
        resultMap.put("data", page.getContent());

        return ResponseEntity.ok(resultMap);
    }
}
