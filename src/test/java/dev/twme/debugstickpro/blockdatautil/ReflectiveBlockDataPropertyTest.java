package dev.twme.debugstickpro.blockdatautil;

import org.bukkit.block.data.BlockData;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;

public class ReflectiveBlockDataPropertyTest {
    @Test
    public void cyclesBooleanPropertiesAndCopiesTheirValue() {
        Map<String, Object> sourceState = new ConcurrentHashMap<>();
        sourceState.put("enabled", false);
        BooleanData source = proxy(BooleanData.class, sourceState);

        SubBlockData property = ReflectiveBlockDataProperty.property(
                source, "BooleanData", "Enabled", "isEnabled", "setEnabled"
        );
        assertEquals("false", property.getDataAsString());
        assertEquals("true", property.nextData().getDataAsString());
        assertEquals("false", property.previousData().getDataAsString());

        property.nextData();
        Map<String, Object> targetState = new ConcurrentHashMap<>();
        targetState.put("enabled", false);
        property.copyTo(proxy(BooleanData.class, targetState));
        assertEquals(true, targetState.get("enabled"));
    }

    @Test
    public void wrapsRangedIntegerProperties() {
        Map<String, Object> state = new ConcurrentHashMap<>();
        state.put("level", 3);
        RangedData data = proxy(RangedData.class, state);

        SubBlockData property = ReflectiveBlockDataProperty.rangedProperty(
                data, "RangedData", "Level", "getLevel", "setLevel", "getMinimumLevel", "getMaximumLevel"
        );
        assertEquals("1", property.nextData().getDataAsString());
        assertEquals("3", property.previousData().getDataAsString());
    }

    @SuppressWarnings("unchecked")
    private static <T extends BlockData> T proxy(Class<T> type, Map<String, Object> state) {
        return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class<?>[]{type}, (proxy, method, args) -> {
            return switch (method.getName()) {
                case "isEnabled" -> state.get("enabled");
                case "setEnabled" -> state.put("enabled", args[0]);
                case "getLevel" -> state.get("level");
                case "setLevel" -> state.put("level", args[0]);
                case "getMinimumLevel" -> 1;
                case "getMaximumLevel" -> 3;
                default -> throw new AssertionError("Unexpected BlockData method call: " + method.getName());
            };
        });
    }

    private interface BooleanData extends BlockData {
        boolean isEnabled();

        void setEnabled(boolean enabled);
    }

    private interface RangedData extends BlockData {
        int getLevel();

        void setLevel(int level);

        int getMinimumLevel();

        int getMaximumLevel();
    }
}
