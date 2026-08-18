package dev.twme.debugstickpro.utils;

import org.bukkit.configuration.ConfigurationSection;

/**
 * Adds values that are present in a bundled YAML resource but missing from a
 * user-managed YAML file without replacing any existing values.
 */
public final class YamlDefaultMerger {

    private YamlDefaultMerger() {
    }

    /**
     * Merge missing values from {@code defaults} into {@code target}.
     *
     * @return true when the target was changed
     */
    public static boolean addMissingValues(ConfigurationSection target, ConfigurationSection defaults) {
        boolean changed = false;

        for (String key : defaults.getKeys(false)) {
            if (target.isSet(key)) {
                if (target.isConfigurationSection(key) && defaults.isConfigurationSection(key)) {
                    changed |= addMissingValues(target.getConfigurationSection(key), defaults.getConfigurationSection(key));
                }
                continue;
            }

            ConfigurationSection defaultSection = defaults.getConfigurationSection(key);
            if (defaultSection != null) {
                ConfigurationSection targetSection = target.createSection(key);
                changed |= addMissingValues(targetSection, defaultSection);
            } else {
                target.set(key, defaults.get(key));
            }
            changed = true;
        }

        return changed;
    }
}
