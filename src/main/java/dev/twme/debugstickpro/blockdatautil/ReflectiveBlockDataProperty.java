package dev.twme.debugstickpro.blockdatautil;

import org.bukkit.block.data.BlockData;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Adapts block-data properties added after the 1.19.4 API baseline.
 */
public final class ReflectiveBlockDataProperty extends SubBlockData {
    private final String filterName;
    private final String displayName;
    private final String getterName;
    private final String setterName;
    private final String minimumGetterName;
    private final String maximumGetterName;
    private final String valuesGetterName;
    private final Object[] getterArguments;
    private final Object[] setterPrefixArguments;
    private Object value;
    private List<Object> values;

    private ReflectiveBlockDataProperty(
            BlockData blockData,
            String filterName,
            String displayName,
            String getterName,
            String setterName,
            String minimumGetterName,
            String maximumGetterName,
            String valuesGetterName,
            Object[] getterArguments,
            Object[] setterPrefixArguments
    ) {
        this.blockData = Objects.requireNonNull(blockData, "blockData");
        this.filterName = Objects.requireNonNull(filterName, "filterName");
        this.displayName = Objects.requireNonNull(displayName, "displayName");
        this.getterName = Objects.requireNonNull(getterName, "getterName");
        this.setterName = Objects.requireNonNull(setterName, "setterName");
        this.minimumGetterName = minimumGetterName;
        this.maximumGetterName = maximumGetterName;
        this.valuesGetterName = valuesGetterName;
        this.getterArguments = getterArguments.clone();
        this.setterPrefixArguments = setterPrefixArguments.clone();
        readValueAndChoices();
    }

    public static boolean isType(BlockData blockData, String className) {
        try {
            ClassLoader classLoader = blockData.getClass().getClassLoader();
            return Class.forName(className, false, classLoader).isInstance(blockData);
        } catch (ClassNotFoundException ignored) {
            return false;
        }
    }

    public static ReflectiveBlockDataProperty property(
            BlockData blockData,
            String filterName,
            String displayName,
            String getterName,
            String setterName
    ) {
        return new ReflectiveBlockDataProperty(
                blockData, filterName, displayName, getterName, setterName,
                null, null, null, new Object[0], new Object[0]
        );
    }

    public static ReflectiveBlockDataProperty rangedProperty(
            BlockData blockData,
            String filterName,
            String displayName,
            String getterName,
            String setterName,
            String minimumGetterName,
            String maximumGetterName
    ) {
        return new ReflectiveBlockDataProperty(
                blockData, filterName, displayName, getterName, setterName,
                minimumGetterName, maximumGetterName, null, new Object[0], new Object[0]
        );
    }

    public static ReflectiveBlockDataProperty constrainedProperty(
            BlockData blockData,
            String filterName,
            String displayName,
            String getterName,
            String setterName,
            String valuesGetterName
    ) {
        return new ReflectiveBlockDataProperty(
                blockData, filterName, displayName, getterName, setterName,
                null, null, valuesGetterName, new Object[0], new Object[0]
        );
    }

    public static ReflectiveBlockDataProperty keyedProperty(
            BlockData blockData,
            String filterName,
            String displayName,
            String getterName,
            String setterName,
            Object key
    ) {
        return new ReflectiveBlockDataProperty(
                blockData, filterName, displayName, getterName, setterName,
                null, null, null, new Object[]{key}, new Object[]{key}
        );
    }

    @Override
    public String name() {
        return filterName;
    }

    @Override
    public String dataName() {
        return displayName;
    }

    @Override
    public String getDataAsString() {
        return value instanceof Enum<?> enumValue ? enumValue.name() : String.valueOf(value);
    }

    @Override
    public SubBlockData nextData() {
        return move(1);
    }

    @Override
    public SubBlockData previousData() {
        return move(-1);
    }

    @Override
    public BlockData copyTo(BlockData target) {
        setValue(target, value);
        return target;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new ReflectiveBlockDataProperty(
                blockData, filterName, displayName, getterName, setterName,
                minimumGetterName, maximumGetterName, valuesGetterName,
                getterArguments, setterPrefixArguments
        );
    }

    private SubBlockData move(int offset) {
        int currentIndex = values.indexOf(value);
        if (currentIndex < 0) {
            throw new IllegalStateException("Current value is not allowed for " + filterName + ": " + value);
        }
        value = values.get(Math.floorMod(currentIndex + offset, values.size()));
        setValue(blockData, value);
        return this;
    }

    private void readValueAndChoices() {
        value = invoke(blockData, getterName, (Object[]) getterArguments);
        if (valuesGetterName != null) {
            Object choices = invoke(blockData, valuesGetterName);
            if (!(choices instanceof Collection<?> collection) || collection.isEmpty()) {
                throw new IllegalStateException(valuesGetterName + " did not return any values");
            }
            values = new ArrayList<>(collection);
            return;
        }
        if (maximumGetterName != null) {
            int minimum = minimumGetterName == null
                    ? 0
                    : ((Number) invoke(blockData, minimumGetterName)).intValue();
            int maximum = ((Number) invoke(blockData, maximumGetterName)).intValue();
            values = new ArrayList<>();
            for (int candidate = minimum; candidate <= maximum; candidate++) {
                values.add(candidate);
            }
            return;
        }
        if (value instanceof Boolean) {
            values = List.of(false, true);
            return;
        }
        if (value instanceof Enum<?> enumValue) {
            values = new ArrayList<>(Arrays.asList((Object[]) enumValue.getDeclaringClass().getEnumConstants()));
            return;
        }
        throw new IllegalArgumentException("Unsupported reflective property type: " + value.getClass().getName());
    }

    private void setValue(BlockData target, Object newValue) {
        Object[] arguments = Arrays.copyOf(setterPrefixArguments, setterPrefixArguments.length + 1);
        arguments[arguments.length - 1] = newValue;
        invoke(target, setterName, arguments);
    }

    private static Object invoke(Object target, String methodName, Object... arguments) {
        Method method = findMethod(target.getClass(), methodName, arguments);
        try {
            return method.invoke(target, arguments);
        } catch (IllegalAccessException exception) {
            throw new IllegalStateException("Cannot access " + method, exception);
        } catch (InvocationTargetException exception) {
            Throwable cause = exception.getCause();
            if (cause instanceof RuntimeException runtimeException) {
                throw runtimeException;
            }
            throw new IllegalStateException("Failed to invoke " + method, cause);
        }
    }

    private static Method findMethod(Class<?> type, String methodName, Object[] arguments) {
        for (Method method : type.getMethods()) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (!method.getName().equals(methodName) || parameterTypes.length != arguments.length) {
                continue;
            }
            boolean compatible = true;
            for (int index = 0; index < parameterTypes.length; index++) {
                if (!wrap(parameterTypes[index]).isInstance(arguments[index])) {
                    compatible = false;
                    break;
                }
            }
            if (compatible) {
                return method;
            }
        }
        throw new IllegalStateException("No compatible method " + methodName + " on " + type.getName());
    }

    private static Class<?> wrap(Class<?> type) {
        if (!type.isPrimitive()) {
            return type;
        }
        if (type == boolean.class) return Boolean.class;
        if (type == byte.class) return Byte.class;
        if (type == short.class) return Short.class;
        if (type == int.class) return Integer.class;
        if (type == long.class) return Long.class;
        if (type == float.class) return Float.class;
        if (type == double.class) return Double.class;
        if (type == char.class) return Character.class;
        return type;
    }
}
