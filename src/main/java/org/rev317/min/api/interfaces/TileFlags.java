package org.rev317.min.api.interfaces;

public interface TileFlags {

    public int WALL_NORTHWEST = 0x1;
    public int WALL_NORTH = 0x2;
    public int WALL_NORTHEAST = 0x4;
    public int WALL_EAST = 0x8;
    public int WALL_SOUTHEAST = 0x10;
    public int WALL_SOUTH = 0x20;
    public int WALL_SOUTHWEST = 0x40;
    public int WALL_WEST = 0x80;
    public int OBJECT_TILE = 0x100;
    public int UNKNOWN = 0x80000;
    public int BLOCKED_TILE = 0x200000;
    public int UNLOADED_TILE = 0x1000000;

}
