package dev.twme.debugstickpro.localization;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerLanguageManagerTest {
    @Test
    public void normalizesPaperLanguageTag() {
        assertEquals("zh_TW", PlayerLanguageManager.normalizeLocale("zh-TW"));
    }

    @Test
    public void normalizesSpigotLocale() {
        assertEquals("en_US", PlayerLanguageManager.normalizeLocale("EN_us"));
    }

    @Test
    public void preservesLocaleVariant() {
        assertEquals("zh_HANT_TW", PlayerLanguageManager.normalizeLocale("zh-Hant-TW"));
    }
}
