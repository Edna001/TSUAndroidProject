package com.ednadev.inews.utils;

import java.io.Serializable;

public enum NewsCategoryEnum implements Serializable {

    BUSINESS("business"),
    ENTERTAINMENT("entertainment"),
    GENERAL("general"),
    HEALTH("health"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology");


    private String value;

    NewsCategoryEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
