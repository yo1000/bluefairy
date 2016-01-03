package com.yo1000.bluefairy.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yo1000.bluefairy.model.repository.InfoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by yoichi.kikuchi on 15/03/13.
 */
@Service
public class InfoService {
    @Resource
    private InfoRepository infoRepository;

    public Map<String, Object> getInfo() throws JsonProcessingException {
        return this.makeFlatMap(this.getInfoRepository().getJson());
    }

    protected Map<String, Object> makeFlatMap(Map<String, Object> hierarchicalMap) throws JsonProcessingException {
        Map<String, Object> flatMap = new LinkedHashMap<String, Object>();

        for (Map.Entry<String, Object> entry : hierarchicalMap.entrySet()) {
            Object value = entry.getValue();

            if (!(value instanceof Map)) {
                flatMap.put(entry.getKey(), value);
                continue;
            }

            Map<String, Object> innerFlatMap = this.makeFlatMap((Map<String, Object>) value);

            for (Map.Entry<String, Object> innerEntry : innerFlatMap.entrySet()) {
                flatMap.put(entry.getKey() + "/" + innerEntry.getKey(), innerEntry.getValue());
            }
        }

        return flatMap;
    }

    protected InfoRepository getInfoRepository() {
        return infoRepository;
    }
}
