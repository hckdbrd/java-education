package com.knubisoft.base.reflection;

import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public class ReflectionTasksImpl implements ReflectionTasks {

    private String testValue = null;

    @Override
    public Object createNewInstanceForClass(Class<?> cls) {
        if (cls == null) throw new NoSuchElementException();
        try {
            return cls.getDeclaredConstructor(String.class).newInstance("");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> Class<? extends T> findImplementationForInterface(Class<T> cls) {

        if (cls == null) throw new NoSuchElementException();
        Set<Class<? extends T>> implementations = new Reflections(cls.getName()).getSubTypesOf(cls);

        if (implementations.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            return implementations.iterator().next();
        }
    }

    @Override
    public Map<String, Object> findAllFieldsForClass(Class<?> cls) {
        if (cls == null) {
            throw new NoSuchElementException();
        }
        List<Field> fields = new ArrayList<>();

        if (cls.getSuperclass() != null) {
            fields = Arrays
                    .stream(cls.getSuperclass().getDeclaredFields())
                    .peek(field -> field.setAccessible(true))
                    .collect(Collectors.toList());
        }

        fields.addAll(Arrays
                .stream(cls.getDeclaredFields())
                .peek(field -> field.setAccessible(true))
                .collect(Collectors.toList()));


        return fields.stream().collect(Collectors.toMap(Field::getName, field -> {
            try {
                return Optional.ofNullable(field.get(createNewInstanceForClass(cls)));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }));
    }

    @Override
    public int countPrivateMethodsInClass(Class<?> cls) {
        if (cls == null) throw new NoSuchElementException();
        return (int) Arrays
                .stream(cls.getDeclaredFields())
                .filter(x -> x.getModifiers() == Modifier.PRIVATE)
                .count();
    }

    @Override
    public boolean isMethodHasAnnotation(Method method, Class<?> annotationUnderMethod) {
        if (method == null || annotationUnderMethod == null) throw new IllegalArgumentException();
        if (Arrays
                .stream(method.getAnnotations())
                .findAny()
                .isPresent()) {
            return true;
        }
        return Arrays.stream(method.getAnnotations()).findAny().isPresent();
    }

    @Override
    public Object evaluateMethodByName(Class<?> cls, String name) {
        if (cls == null || name == null) throw new IllegalArgumentException();
        try {
            Method method = cls.getDeclaredMethod(name);
            method.setAccessible(true);
            return method.invoke(cls.getDeclaredConstructor(String.class).newInstance(""));
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object evaluateMethodWithArgsByName(Object obj, String name, Object... args) {
        if (obj == null || name == null || args == null) throw new IllegalArgumentException();

        try {
            Class<?> clazz = obj.getClass();
            Class<?>[] clazzes = Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
            Method method = clazz.getMethod(name, clazzes);
            return method.invoke(obj, args);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object changePrivateFieldValue(Object instance, String name, Object newValue) {
        if (instance == null || name == null || newValue == null || name.isBlank()) throw new IllegalArgumentException();
        try {
            Class<?> newInstance = instance.getClass();
            Field field = newInstance.getDeclaredField(name);
            field.setAccessible(true);
            field.set(instance, newValue);
            return newInstance;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
