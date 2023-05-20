package com.ssafy.enjoytrip.global.code.meta;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MetaDataFactory {

    private Map<String, List<MetaData>> factory = new LinkedHashMap<>();

    public MetaDataFactory() {}

    public void put(String key, Class<? extends EnumHelper> enumType) {
        factory.put(key, fromEnum(enumType));
    }

    private List<MetaData> fromEnum(Class<? extends EnumHelper> enumType) {
        return Arrays.stream(enumType.getEnumConstants())
                .map(MetaData::new)
                .collect(Collectors.toList());
    }

    public List<MetaData> get(String key) {
        return factory.get(key);
    }
}
