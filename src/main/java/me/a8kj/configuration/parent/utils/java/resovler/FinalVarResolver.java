package me.a8kj.configuration.parent.utils.java.resovler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Map;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * A utility class to resolve and inspect final variables in a given class.
 * 
 * <p>
 * This class allows for the loading of fields from the specified target class
 * and
 * checking if a particular value is associated with a final variable.
 * </p>
 * 
 * @deprecated This class is deprecated and may be removed in future versions.
 */
@Deprecated
@RequiredArgsConstructor
@Getter
public class FinalVarResolver {

    /**
     * The target class whose fields are to be inspected.
     */
    private final Class<?> targetClass;

    /**
     * A map that stores field values and their corresponding field names.
     */
    private Map<Object, String> values = Maps.newHashMap();

    /**
     * Creates a new instance of {@code FinalVarResolver} for the given target
     * class.
     *
     * @param targetClass the class to inspect; must not be {@code null}.
     * @return a new {@code FinalVarResolver} instance.
     */
    public static FinalVarResolver of(@NonNull Class<?> targetClass) {
        return new FinalVarResolver(targetClass);
    }

    /**
     * Loads the fields from the target class and stores their values and names.
     * 
     * <p>
     * Fields are inspected for their values, and those values are stored in a map
     * alongside their field names. Static fields are handled differently from
     * instance fields.
     * </p>
     * 
     * <p>
     * Any exceptions during field access or instance creation are caught and
     * printed.
     * </p>
     */
    public void loadFields() {
        Class<?> clazz = this.targetClass;
        Field[] fields = clazz.getDeclaredFields();

        if (fields.length < 1)
            return;

        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();

            for (Field field : fields) {
                field.setAccessible(true);

                Object fieldValue;
                if (Modifier.isStatic(field.getModifiers())) {
                    fieldValue = field.get(null);
                } else {
                    fieldValue = field.get(instance);
                }
                values.putIfAbsent(fieldValue, field.getName());
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the specified object is associated with a final variable in the
     * target class.
     * 
     * @param object the object whose associated field is to be checked.
     * @return {@code true} if the field associated with the given object is final;
     *         {@code false} otherwise.
     * @throws IllegalArgumentException if the object is not found in the resolved
     *                                  field values.
     */
    public boolean isVarFinal(Object object) {
        if (!values.containsKey(object)) {
            throw new IllegalArgumentException("Cannot find target object!");
        }

        try {
            String fieldName = values.get(object);
            Field field = targetClass.getDeclaredField(fieldName);
            int modifiers = field.getModifiers();
            return Modifier.isFinal(modifiers);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return false;
        }
    }
}
