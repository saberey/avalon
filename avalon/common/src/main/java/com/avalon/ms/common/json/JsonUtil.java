package com.avalon.ms.common.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author saberey
 * @version 1.0
 * @description
 * @date 2020/11/9 11:06
 */
public class JsonUtil {

    public static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public static String writeObject(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error("jsonUtil#writeObject exception:", e);
        }
        return null;
    }

    public static <T> T readObject(String jsonStr, Class<T> tClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonStr, tClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error("jsonUtil#readObject exception:", e);
        }
        return null;
    }


}
