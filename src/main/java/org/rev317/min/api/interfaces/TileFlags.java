package org.rev317.min.api.interfaces;

public interface TileFlags {

    int WALL_NORTHWEST = 0x1;
    int WALL_NORTH = 0x2;
    int WALL_NORTHEAST = 0x4;
    int WALL_EAST = 0x8;
    int WALL_SOUTHEAST = 0x10;
    int WALL_SOUTH = 0x20;
    int WALL_SOUTHWEST = 0x40;
    int WALL_WEST = 0x80;
    int OBJECT_TILE = 0x100;
    int UNKNOWN = 0x80000;
    int BLOCKED_TILE = 0x200000;
    int UNLOADED_TILE = 0x1000000;

}
