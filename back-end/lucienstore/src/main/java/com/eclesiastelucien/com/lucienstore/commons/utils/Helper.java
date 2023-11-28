package com.eclesiastelucien.com.lucienstore.commons.utils;

import java.util.UUID;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Helper {

    public static <T> T parseJson(String json, Class<T> classT) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, classT);
    }

    public static UUID getUUID() {
        return UUID.randomUUID();
    }

    public static String readJsonFile(String filePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(filePath);
        InputStream inputStream = resource.getInputStream();
        byte[] jsonBytes = FileCopyUtils.copyToByteArray(inputStream);
        return new String(jsonBytes, StandardCharsets.UTF_8);
    }

    public static Double formatDecimal(Double value) {
        BigDecimal bd = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }
}
