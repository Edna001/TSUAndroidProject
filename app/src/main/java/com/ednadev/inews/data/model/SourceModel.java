package com.ednadev.inews.data.model;

public class SourceModel {
    private String id;
    private String name;

    public SourceModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
