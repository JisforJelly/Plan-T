package com.ssafy.enjoytrip.global.code.meta;

public class MetaData {

    private int code;
    private int mappingCode;
    private String name;

    public MetaData(EnumHelper enumHelper) {
        this.code = enumHelper.getCode();
        this.mappingCode = enumHelper.getMappingCode();
        this.name = enumHelper.getName();
    }

    public int getCode() {
        return this.code;
    }

    public int getMappingCode() {
        return this.mappingCode;
    }

    public String getName() {
        return this.name;
    }
}
