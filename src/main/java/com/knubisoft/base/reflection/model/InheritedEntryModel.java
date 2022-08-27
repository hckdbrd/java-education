package com.knubisoft.base.reflection.model;

public class InheritedEntryModel extends EntryModel {

    private String privateField = "";

    public InheritedEntryModel() {
        super();
    }

    public InheritedEntryModel(String tableName) {
        super(tableName);
    }

    public InheritedEntryModel(String tableName, String schemaName) {
        super(tableName, schemaName);
    }

    public InheritedEntryModel(String tableName, String schemaName, String version) {
        super(tableName, schemaName, version);
    }

    @Override
    public EntryModel builder() {
        return super.builder();
    }

    public String testMethod1() {
        return "test";
    }
    public String returnPrivateField() {
        return privateField;
    }
}

