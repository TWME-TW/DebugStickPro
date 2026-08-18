package dev.twme.debugstickpro.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class YamlDefaultMergerTest {

    @Test
    public void addsMissingNestedValuesWithoutOverwritingExistingValues() throws Exception {
        YamlConfiguration defaults = yaml("""
                ConfigVersion: 8
                Feature:
                  Enabled: true
                  NewValue: default
                  Limits:
                    Maximum: 10
                Messages:
                  - one
                  - two
                """);
        YamlConfiguration target = yaml("""
                ConfigVersion: 7
                Feature:
                  Enabled: false
                  Limits:
                    Maximum: 0
                Messages:
                  - custom
                """);

        assertTrue(YamlDefaultMerger.addMissingValues(target, defaults));
        assertEquals(7, target.getInt("ConfigVersion"));
        assertFalse(target.getBoolean("Feature.Enabled"));
        assertEquals("default", target.getString("Feature.NewValue"));
        assertEquals(0, target.getInt("Feature.Limits.Maximum"));
        assertEquals(List.of("custom"), target.getStringList("Messages"));
    }

    @Test
    public void doesNotChangeCompleteConfiguration() throws Exception {
        YamlConfiguration defaults = yaml("""
                Feature:
                  Enabled: true
                """);
        YamlConfiguration target = yaml("""
                Feature:
                  Enabled: false
                """);

        assertFalse(YamlDefaultMerger.addMissingValues(target, defaults));
        assertFalse(target.getBoolean("Feature.Enabled"));
    }

    private static YamlConfiguration yaml(String value) throws Exception {
        YamlConfiguration configuration = new YamlConfiguration();
        configuration.loadFromString(value);
        return configuration;
    }
}
