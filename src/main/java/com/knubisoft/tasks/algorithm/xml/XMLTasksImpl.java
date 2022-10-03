package com.knubisoft.tasks.algorithm.xml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.knubisoft.tasks.algorithm.ModelRoot;
import lombok.SneakyThrows;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class XMLTasksImpl implements XMLTasks {

    @Override
    @SneakyThrows
    public ModelRoot parseXML(String xml) {
        if (xml == null || xml.isEmpty()) {
            throw new NullPointerException();
        }
        XmlMapper mapper = new XmlMapper();
        mapper.findAndRegisterModules();
        return mapper.readValue(xml, ModelRoot.class);
    }

    @SneakyThrows
    @Override
    public List<Integer> getAllIds(String xml) {
        if (xml == null || xml.isEmpty()) {
            throw new IllegalArgumentException();
        }
        List<Integer> idList = new ArrayList<>();
        XmlMapper mapper = new XmlMapper();

        mapper.findAndRegisterModules();

        ModelRoot modelRoot = mapper.readValue(xml, ModelRoot.class);

        for (ModelRoot.Item i : modelRoot.getItems()) {
            idList.add(i.id);
        }

        return idList;
    }

    @SneakyThrows
    @Override
    public List<String> getNameWithIdMoreThan1(String xml) {
        if (xml == null || xml.isEmpty()) {
            throw new NullPointerException();
        }
        List<String> nameList = new ArrayList<>();
        XmlMapper mapper = new XmlMapper();

        ModelRoot modelRoot = mapper.readValue(xml, ModelRoot.class);

        for (ModelRoot.Item i : modelRoot.getItems()) {
            if (i.id > 1) {
                nameList.add(i.name);
            }
        }

        return nameList;
    }

    @SneakyThrows
    private Object xpath(String xml, String expression, QName q) {
        return null;
    }

    private <T> List<T> apply(NodeList nodeList, Function<Node, T> tFunction) {
        return null;
    }
}