package com.ssafy.enjoytrip.global.code.meta;

public enum ContentTypeCode implements EnumHelper {
    TOUR(12, "관광지"),
    CULTURAL(14, "문화시설"),
    FESTIVAL(15, "축제공연행사"),
    COURSE(25, "여행코스"),
    LEISURE(28, "레포츠"),
    ACCOMMODATION(32, "숙박"),
    SHOPPING(38, "쇼핑"),
    RESTAURANT(39, "음식점");

    ContentTypeCode(int code, String name) {
        this.code = code;
        this.name = name;
    }

    private int code;
    private String name;

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public int getMappingCode() {
        return -1;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
