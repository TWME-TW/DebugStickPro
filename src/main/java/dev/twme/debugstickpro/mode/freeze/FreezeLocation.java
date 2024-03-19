// package dev.twme.debugstickpro.mode.freeze;
//
// import org.bukkit.Location;
//
// public class FreezeLocation {
//     private final int x;
//     private final int y;
//     private final int z;
//     private final Location location;
//
//
//     public FreezeLocation(Location location) {
//         this.location = location;
//         this.x = (int) location.getX();
//         this.y = (int) location.getY();
//         this.z = (int) location.getZ();
//     }
//     public Location getLocation() {
//         return location;
//     }
//
//     @Override
//     public boolean equals(Object o) {
//         if (this == o) return true;
//         if (!(o instanceof FreezeLocation thisData)) return false;
//
//         return thisData.x == this.x && thisData.y == this.y && thisData.z == this.z;
//     }
//
//     @Override
//     public int hashCode() {
//         int result =  x;
//         result = 31 * result +  y;
//         result = 31 * result +  z;
//         return result;
//     }
// }
