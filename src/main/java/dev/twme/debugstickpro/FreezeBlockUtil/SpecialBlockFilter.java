package dev.twme.debugstickpro.FreezeBlockUtil;

import dev.twme.debugstickpro.util.Log;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;

import java.util.Random;

public class SpecialBlockFilter {
    private static final Random RANDOM = new Random();

    public static Location filter(Material material, Location location) {

        if (material == Material.BAMBOO) {
            return caculateLocation(location,0.25f);
        }

        if (material == Material.BAMBOO_SAPLING) {
            return caculateLocation(location,0.25f);
        }

        if (material == Material.MANGROVE_PROPAGULE) {
            return caculateLocation(location,0.25f);
        }

        if ((Tag.FLOWERS.isTagged(material))) {
            return caculateLocation(location,0.25f);
        }

        if (material == Material.POINTED_DRIPSTONE) {
            return caculateLocation(location,0.125f);
        }

        if (material == Material.LILY_PAD || material == Material.SEA_PICKLE) {
            return clientRelation(location);
        }

        return location;
    }

    public static Location caculateLocation(Location location,float offsetNum) {

        long l = hashCode((int) location.getX(), 0, (int) location.getZ());

        double d = clamp(((double)((float)(l & 0xFL) / 15.0f) - 0.5) * 0.5, (-offsetNum), offsetNum);
        double e = clamp(((double)((float)(l >> 8 & 0xFL) / 15.0f) - 0.5) * 0.5, (-offsetNum), offsetNum);

        return new Location(location.getWorld(),location.getX() + d,(int)location.getY(),location.getZ() + e);
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
}
