package dev.twme.debugstickpro.utils;

import dev.twme.debugstickpro.DebugStickPro;
import org.bukkit.NamespacedKey;

public final class PersistentKeys {
    public static final NamespacedKey DEBUG_STICK_ITEM = new NamespacedKey(DebugStickPro.getInstance(), "DebugStick");
    public static final NamespacedKey FREEZE_BLOCK_DISPLAY = new NamespacedKey(DebugStickPro.getInstance(), "FreezeBlock");
}
