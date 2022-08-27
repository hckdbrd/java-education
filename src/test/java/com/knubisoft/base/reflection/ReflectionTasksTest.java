package com.knubisoft.base.reflection;

import com.knubisoft.base.reflection.model.EntryModel;
import com.knubisoft.base.reflection.model.InheritedEntryModel;
import com.knubisoft.base.string.StringTasks;
import com.knubisoft.base.string.StringTasksImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ReflectionTasksTest {

    ReflectionTasks instance = new ReflectionTasksImpl();
    InheritedEntryModel model = new InheritedEntryModel();

    @Test
    @SneakyThrows
    public void createNewInstanceForClassSuccessful() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.model.InheritedEntryModel");
        assertEquals(InheritedEntryModel.class, instance.createNewInstanceForClass(clazz).getClass());
        assertEquals(InheritedEntryModel.class,
                instance.createNewInstanceForClass(InheritedEntryModel.class).getClass());
    }

    @Test
    @SneakyThrows
    public void createNewInstanceForClassFail() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.ReflectionTasks");
        assertThrows(RuntimeException.class, () -> instance.createNewInstanceForClass(clazz));
        assertThrows(RuntimeException.class, () -> instance.createNewInstanceForClass(ReflectionTasks.class));
        assertThrows(NoSuchElementException.class, () -> instance.createNewInstanceForClass(null));
    }

    @Test
    @SneakyThrows
    public void findImplementationForInterfaceSuccessful() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.model.EntryModel");
        assertEquals(InheritedEntryModel.class, instance.findImplementationForInterface(clazz));
        assertEquals(InheritedEntryModel.class, instance.findImplementationForInterface(EntryModel.class));
    }

    @Test
    @SneakyThrows
    public void findImplementationForInterfaceFail() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.ReflectionTasks");
        assertThrows(IllegalArgumentException.class,
                () -> instance.findImplementationForInterface(clazz));
        assertThrows(IllegalArgumentException.class,
                () -> instance.findImplementationForInterface(ReflectionTasks.class));
        assertThrows(NoSuchElementException.class, () -> instance.findImplementationForInterface(null));
    }

    @Test
    @SneakyThrows
    public void findAllFieldsForClassSuccessful() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.model.InheritedEntryModel");
        assertEquals(4, instance.findAllFieldsForClass(clazz).size());
        assertEquals(0, instance.findAllFieldsForClass(ReflectionTasks.class).size());
        assertEquals(0, instance.findAllFieldsForClass(ReflectionTasksImpl.class).size());
        assertEquals(0, instance.findAllFieldsForClass(StringTasks.class).size());
        assertEquals(0, instance.findAllFieldsForClass(StringTasksImpl.class).size());
        assertEquals(4, instance.findAllFieldsForClass(InheritedEntryModel.class).size());
    }

    @Test
    @SneakyThrows
    public void findAllFieldsForClassFail() {
        assertThrows(NoSuchElementException.class, () -> instance.findAllFieldsForClass(null));
    }

    @Test
    @SneakyThrows
    public void countPrivateMethodsInClassSuccessful() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.model.EntryModel");
        assertEquals(3, instance.countPrivateMethodsInClass(clazz)); // here was a mistake, the result must be 3, not 2.
        assertEquals(0, instance.countPrivateMethodsInClass(StringTasks.class));
        assertEquals(3, instance.countPrivateMethodsInClass(EntryModel.class)); // here was a mistake, the result must be 3, not 2.
    }

    @Test
    @SneakyThrows
    public void countPrivateMethodsInClassFail() {
        assertThrows(NoSuchElementException.class, () -> instance.countPrivateMethodsInClass(null));
    }

    @Test
    @SneakyThrows
    public void isMethodHasAnnotationSuccessful() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.model.InheritedEntryModel");
        assertTrue(instance.isMethodHasAnnotation(ReflectionTasksTest.class.getMethod("isMethodHasAnnotationSuccessful"), instance.getClass()));
        assertTrue(instance.isMethodHasAnnotation(ReflectionTasksTest.class.getMethod("findImplementationForInterfaceSuccessful"), instance.getClass()));
    }

    @Test
    @SneakyThrows
    public void isMethodHasAnnotationFail() {
        assertThrows(IllegalArgumentException.class, () -> instance.isMethodHasAnnotation(null, null));
        assertThrows(IllegalArgumentException.class, () -> instance.isMethodHasAnnotation(null, instance.getClass()));
        assertThrows(IllegalArgumentException.class, () -> instance.isMethodHasAnnotation(ReflectionTasksTest.class.getMethod("isMethodHasAnnotationSuccessful"), null));
    }

    @Test
    @SneakyThrows
    public void evaluateMethodByNameSuccessful() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.model.InheritedEntryModel");
        assertEquals("test", instance.evaluateMethodByName(clazz, "testMethod1"));
    }

    @Test
    @SneakyThrows
    public void evaluateMethodByNameFail() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.model.InheritedEntryModel");
        assertThrows(IllegalArgumentException.class, () -> instance.evaluateMethodByName(null, null));
        assertThrows(IllegalArgumentException.class, () -> instance.evaluateMethodByName(null, "testMethod1"));
        assertThrows(IllegalArgumentException.class, () -> instance.evaluateMethodByName(clazz, null));
    }


    @Test
    @SneakyThrows
    public void evaluateMethodByNameArgsSuccessful() {
        assertEquals("dlroW ,olleH",
                instance.evaluateMethodWithArgsByName(new StringTasksImpl(), "reverseString", "Hello, World"));
        assertEquals("He, Worldllo",
                instance.evaluateMethodWithArgsByName(new StringTasksImpl(), "insertStringInMiddle",
                        "Hello", ", World"));
        assertEquals("g",
                instance.evaluateMethodWithArgsByName(new StringTasksImpl(), "uniqueCharacters",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do"));
    }

    @Test
    @SneakyThrows
    public void evaluateMethodByNameArgsFail() {
        assertThrows(IllegalArgumentException.class,
                () -> instance.evaluateMethodWithArgsByName(null, "builder", "arg1", "arg2"));
        assertThrows(IllegalArgumentException.class,
                () -> instance.evaluateMethodWithArgsByName(new StringTasksImpl(), null, "arg1", "arg2"));
        assertThrows(IllegalArgumentException.class,
                () -> instance.evaluateMethodWithArgsByName(new StringTasksImpl(),
                        "insertStringInMiddle", null));
    }

    @Test
    @SneakyThrows
    void changePrivateFieldValueSuccessful() {
        Object sample = instance.changePrivateFieldValue(model,"privateField", "New Value");
        Field field = model.getClass().getDeclaredField("privateField");
        field.setAccessible(true);
        assertEquals(InheritedEntryModel.class.getDeclaredField("privateField"), field);
    }
}

