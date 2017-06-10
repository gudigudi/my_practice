package com.gudigudigudi.mybatis.plugin;

import java.lang.reflect.Field;

public class ReflectorHelper {

    public static Field getFieldByFieldName(Object obj, String fieldName) {

        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static Object getValueByFieldName(Object obj, String fieldName) throws IllegalAccessException {
        Object value = null;
        Field field = getFieldByFieldName(obj, fieldName);
        if (field != null) {
            if (field.isAccessible()) {
                value = field.get(obj);
            } else {
                field.setAccessible(true);
                value = field.get(obj);
                field.setAccessible(false);
            }
        }
        return value;
    }

    public static Object setValueByFieldName(Object obj, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {

        Field field = obj.getClass().getDeclaredField(fieldName);
        if (field.isAccessible()) {
            field.set(obj, value);
        } else {
            field.setAccessible(true);
            field.set(obj, value);
            field.setAccessible(false);
        }
        return field;
    }
}
