package com.knubisoft.tasks.algorithm.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knubisoft.tasks.algorithm.ModelRoot;
import com.knubisoft.tasks.algorithm.ModelRoot.Item;
import lombok.SneakyThrows;

import java.util.*;
import java.util.stream.Collectors;

public class JsonTasksImpl implements JsonTasks {

    @Override
    @SneakyThrows
    public ModelRoot parseJson(String json) {
        if (json == null || json.isEmpty()) {
            throw new IllegalArgumentException();
        }
        ObjectMapper om = new ObjectMapper();
        om.findAndRegisterModules();
        return om.readValue(json, ModelRoot.class);

    }

    @SneakyThrows
    @Override
    public List<Integer> getAllIds(String json) {
        if (json == null || json.isEmpty()) {
            throw new IllegalArgumentException();
        }
        List<Integer> idList = new ArrayList<>();
        ObjectMapper om = new ObjectMapper();

        om.findAndRegisterModules();

        ModelRoot modelRoot = om.readValue(json, ModelRoot.class);

        for (Item i : modelRoot.getItems()) {
            idList.add(i.id);
        }

        return idList;
    }

    @SneakyThrows
    @Override
    public List<String> getNameWithIdMoreThan1(String json) {
        if (json == null || json.isEmpty()) {
            throw new IllegalArgumentException();
        }
        List<String> nameList = new ArrayList<>();
        ObjectMapper om = new ObjectMapper();

        ModelRoot modelRoot = om.readValue(json, ModelRoot.class);

        for (Item i : modelRoot.getItems()) {
            if (i.id > 1) {
                nameList.add(i.name);
            }
        }

        return nameList;
    }

    @SneakyThrows
    @Override
    public List<Map<String, String>> getAllItems(String json) {
        if (json == null || json.isEmpty()) {
            throw new NullPointerException();
        }
        ObjectMapper om = new ObjectMapper();
        ModelRoot modelRoot = om.readValue(json, ModelRoot.class);
        List<Item> itemList = modelRoot.getItems();
        return itemList.stream().map(item -> Map.of(item.getName(), item.toString())).collect(Collectors.toList());
    }

}