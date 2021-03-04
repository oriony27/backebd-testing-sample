package com.backend.testing.utils;

import com.google.common.base.Verify;
import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static junit.framework.TestCase.*;


public final class AssertionUtils {
    private AssertionUtils() {
        throw new IllegalStateException("This is utility class!");
    }

    public static <T> void assertObjects(T expectedData, T actualData)  {
        Verify.verifyNotNull(expectedData, "Expected element is null");
        Verify.verifyNotNull(actualData, "Actual element is null");

        List<String> fieldsError = Lists.newArrayList();

        for (Field field : expectedData.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                assertEquals("Field: " + field.getName(), field.get(expectedData), field.get(actualData));
            } catch (AssertionError | IllegalAccessException ex) {
                fieldsError.add(ex.getMessage());
            }
        }

        if (fieldsError.size() > 0) {
            if (fieldsError.size() == 1) {
                throw new AssertionError(fieldsError.get(0));
            } else {
                throw new AssertionError(String.join(", ", fieldsError));
            }
        }
    }

    public static <T, TK> void assertObjectsList(List<T> expectedItems,
                                                List<T> actualItems,
                                                Function<? super T, TK> selector) {
        if (expectedItems.isEmpty()) return;

        assertNotNull("Expected data list is null!", expectedItems);
        assertNotNull("Actual data list is null!", actualItems);
        assertFalse("Expected data list is empty!", expectedItems.isEmpty());
        assertFalse("Actual data list is empty!", actualItems.isEmpty());

        Map<TK, T> expectedDataMap = new HashMap();

        for (T item : expectedItems) {
            assertNotNull("Expected data list contains null item", item);
            TK key = selector.apply(item);
            assertNotNull(String.format("Item in expected data list doesn't have key. Item: %s", GsonUtils.toJson(item)), key);
            expectedDataMap.put(key, item);
        }

        Map<TK, T> actualDataMap = new HashMap();

        for (T item : actualItems) {
            assertNotNull("Actual data list contains null item", item);
            TK key = selector.apply(item);
            assertNotNull(String.format("Item in actual data list doesn't have key. Item: %s", GsonUtils.toJson(item)), key);
            actualDataMap.put(key, item);
        }

        List<TK> keySetWithMissingElements = expectedDataMap.keySet()
                                                            .stream()
                                                            .filter(x -> !actualDataMap.containsKey(x))
                                                            .collect(Collectors.toList());

        if (keySetWithMissingElements.size() > 0) {
            throw new AssertionError(String.format("In actual data list missing items with keys: %s",
                    String.join(", ", keySetWithMissingElements.toString())));
        }

        List<TK> keyListWithExcessElements = actualDataMap.keySet()
                                                            .stream()
                                                            .filter(x -> !expectedDataMap.containsKey(x))
                                                            .collect(Collectors.toList());

        if (keyListWithExcessElements.size() > 0) {
            throw new AssertionError(String.format("In actual data list excess items with keys: %s", String.join(", ", keyListWithExcessElements.toString())));
        }

        List<String> elementsError = new ArrayList<>();

        for (T expectedData : expectedItems) {
            T actualData = actualDataMap.get(selector.apply(expectedData));

            try {
                List<String> fieldsError = new ArrayList<>();

                for (Field field : expectedData.getClass().getDeclaredFields()) {
                    try {
                        field.setAccessible(true);

                        assertEquals("Field: " + field.getName(), field.get(expectedData), field.get(actualData));
                    } catch (AssertionError ex) {
                        fieldsError.add(ex.getMessage());
                    }
                }

                if (fieldsError.size() > 0) {
                    elementsError.add(String.format("Element id: %s. %s", selector.apply(expectedData), String.join(", ", fieldsError)));
                }

            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        }

        if (elementsError.size() > 0) {
            throw new AssertionError(String.join("\n", elementsError)
                    .replace("expected:<", "expected: <")
                    .replace("> but was:<", "> but was: <"));
        }
    }
}
