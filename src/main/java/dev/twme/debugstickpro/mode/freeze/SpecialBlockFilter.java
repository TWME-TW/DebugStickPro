package dev.twme.debugstickpro.mode.freeze;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;

import java.util.Random;

public class SpecialBlockFilter {
    private static final Random RANDOM = new Random();

    public static Location filter(Material material, Location location) {

        if (Tag.FLOWERS.isTagged(material)) {
            return calculateLocation(location, 0.25f);
        }

        switch (material) {
            case BAMBOO, BAMBOO_SAPLING, MANGROVE_PROPAGULE, NETHER_SPROUTS, CRIMSON_ROOTS, WARPED_ROOTS -> {
                return calculateLocation(location, 0.25f);
            }
            case POINTED_DRIPSTONE -> {
                return calculateLocation(location, 0.125f);
            }
            case LILY_PAD, SEA_PICKLE -> {
                return clientRelation(location);
            }
            case FERN, LARGE_FERN, TALL_GRASS -> {
                return calculateLocationWithY(location, 0.25f, 0.2F);
            }
            case SMALL_DRIPLEAF -> {
                return calculateLocationWithY(location, 0.25f, 0.1F);
            }
        };

        if (isValidMaterial("GRASS")) {
            if (material == Material.valueOf("GRASS")) {
                return calculateLocationWithY(location, 0.25f,0.2F);
            }
        } else {
            if (material == Material.SHORT_GRASS) {
                return calculateLocationWithY(location, 0.25f, 0.2F);
            }
        }


        return location;
    }

    public static Location calculateLocation(Location location, float offsetNum) {

        long l = hashCode((int) location.getX(), 0, (int) location.getZ());

        double d = clamp(((double) ((float) (l & 0xFL) / 15.0f) - 0.5) * 0.5, (-offsetNum), offsetNum);
        double e = clamp(((double) ((float) (l >> 8 & 0xFL) / 15.0f) - 0.5) * 0.5, (-offsetNum), offsetNum);

        return new Location(location.getWorld(), location.getX() + d, (int) location.getY(), location.getZ() + e);
    }

    public static Location calculateLocationWithY(Location location, float offsetNum, float yOffSetNum) {

        long l = hashCode((int) location.getX(), 0, (int) location.getZ());

        double x = clamp(((double) ((float) (l & 0xFL) / 15.0f) - 0.5) * 0.5, (-offsetNum), offsetNum);
        double y = ((double)((float)(l >> 4 & 0xFL) / 15.0f) - 1.0) * (double)yOffSetNum;
        double z = clamp(((double) ((float) (l >> 8 & 0xFL) / 15.0f) - 0.5) * 0.5, (-offsetNum), offsetNum);

        return new Location(location.getWorld(), location.getX() + x, location.getY() + y, location.getZ() + z);
    }


    public static long hashCode(int x, int y, int z) {
        long l = (x * 3129871L) ^ z * 116129781L ^ y;
        l = l * l * 42317861L + l * 11L;
        return l >> 16;
    }

    public static double clamp(double value, double min, double max) {
        if (value < min) {
            return min;
        }
        return Math.min(value, max);
    }

    public static Location clientRelation(Location location) {
        RANDOM.setSeed(hashCode((int) location.getX(), (int) location.getY(), (int) location.getZ()));
        int i = Math.abs((int) RANDOM.nextLong()) % 4;
        return transformBlockDisplayLocationARoundCenter(location, (float) i * 90.0F + 90F);
    }


    private static Location transformBlockDisplayLocationARoundCenter(Location input, float angle) {
        double radAngle = Math.toRadians(angle); // Angle in radians
        double centerX = input.x() + 0.5;
        // double centerY = input.y() + 0.5;
        double centerZ = input.z() + 0.5;

        double relX = input.x() - centerX;
        double relZ = input.z() - centerZ;

        double rotatedX = relX * Math.cos(radAngle) - relZ * Math.sin(radAngle);
        double rotatedZ = relX * Math.sin(radAngle) + relZ * Math.cos(radAngle);

        double finalX = rotatedX + centerX;
        double finalZ = rotatedZ + centerZ;

        return new Location(input.getWorld(), finalX, input.y(), finalZ, angle, 0);
    }

    private static boolean isValidMaterial(String material) {

        if (material == null) {
            return false;
        } else {
            try {
                Material.valueOf(material);
                return true;
            } catch (IllegalArgumentException var3) {
                return false;
            }
        }
    }
}
