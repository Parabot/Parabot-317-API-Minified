package org.rev317.min.api.methods;

import org.parabot.environment.api.utils.Timer;
import org.rev317.min.api.wrappers.Tile;

/**
 * @author Everel
 */
public class Calculations {

    /**
     * Calculates distance between local player and given tile
     *
     * @param tile
     * @return distance between local player and given tile
     */
    public static final double distanceTo(Tile tile) {
        return distanceBetween(tile, Players.getMyPlayer().getLocation());
    }

    /**
     * Calculates distance between two given tiles
     *
     * @param a
     * @param b
     * @return distance between a and b
     */
    public static final double distanceBetween(Tile a, Tile b) {
        int x = b.getX() - a.getX();
        int y = b.getY() - a.getY();

        return Math.sqrt((x * x) + (y * y));
    }


    /**
     * @param startX       the startX (0 < startX < 104)
     * @param startY       the startY (0 < startY < 104)
     * @param destX        the destX (0 < destX < 104)
     * @param destY        the destY (0 < destY < 104)
     * @param findAdjacent if it's an object, it will find path which touches it.
     * @return The distance of the shortest path to the destination; or -1 if no valid path to the destination was
     * found.
     */
    public static int dijkstraDist(final int startX, final int startY, final int destX, final int destY, final boolean findAdjacent) {
        try {
            final int[][] prev = new int[104][104];
            final int[][] dist = new int[104][104];
            final int[] path_x = new int[4000];
            final int[] path_y = new int[4000];
            for (int xx = 0; xx < 104; xx++) {
                for (int yy = 0; yy < 104; yy++) {
                    prev[xx][yy] = 0;
                    dist[xx][yy] = 99999999;
                }
            }
            int curr_x = startX;
            int curr_y = startY;
            prev[startX][startY] = 99;
            dist[startX][startY] = 0;
            int path_ptr = 0;
            int step_ptr = 0;
            path_x[path_ptr] = startX;
            path_y[path_ptr++] = startY;
            final int blocks[][] = Game.getCollisionFlags();
            final int pathLength = path_x.length;
            boolean foundPath = false;
            while (step_ptr != path_ptr) {
                curr_x = path_x[step_ptr];
                curr_y = path_y[step_ptr];
                if (Math.abs(curr_x - destX) + Math.abs(curr_y - destY) == (findAdjacent ? 1 : 0)) {
                    foundPath = true;
                    break;
                }
                step_ptr = (step_ptr + 1) % pathLength;
                final int cost = dist[curr_x][curr_y] + 1;
                // south
                if (curr_y > 0 && prev[curr_x][curr_y - 1] == 0 && (blocks[curr_x][curr_y - 1] & 0x1280102) == 0) {
                    path_x[path_ptr] = curr_x;
                    path_y[path_ptr] = curr_y - 1;
                    path_ptr = (path_ptr + 1) % pathLength;
                    prev[curr_x][curr_y - 1] = 1;
                    dist[curr_x][curr_y - 1] = cost;
                }
                // west
                if (curr_x > 0 && prev[curr_x - 1][curr_y] == 0 && (blocks[curr_x - 1][curr_y] & 0x1280108) == 0) {
                    path_x[path_ptr] = curr_x - 1;
                    path_y[path_ptr] = curr_y;
                    path_ptr = (path_ptr + 1) % pathLength;
                    prev[curr_x - 1][curr_y] = 2;
                    dist[curr_x - 1][curr_y] = cost;
                }
                // north
                if (curr_y < 104 - 1 && prev[curr_x][curr_y + 1] == 0 && (blocks[curr_x][curr_y + 1] &
                        0x1280120) == 0) {
                    path_x[path_ptr] = curr_x;
                    path_y[path_ptr] = curr_y + 1;
                    path_ptr = (path_ptr + 1) % pathLength;
                    prev[curr_x][curr_y + 1] = 4;
                    dist[curr_x][curr_y + 1] = cost;
                }
                // east
                if (curr_x < 104 - 1 && prev[curr_x + 1][curr_y] == 0 && (blocks[curr_x + 1][curr_y] &
                        0x1280180) == 0) {
                    path_x[path_ptr] = curr_x + 1;
                    path_y[path_ptr] = curr_y;
                    path_ptr = (path_ptr + 1) % pathLength;
                    prev[curr_x + 1][curr_y] = 8;
                    dist[curr_x + 1][curr_y] = cost;
                }
                // south west
                if (curr_x > 0 && curr_y > 0 && prev[curr_x - 1][curr_y - 1] == 0 && (blocks[curr_x - 1][curr_y - 1] &
                        0x128010E) == 0 && (blocks[curr_x - 1][curr_y] & 0x1280108) == 0 && (blocks[curr_x
                        ][curr_y - 1] & 0x1280102) == 0) {
                    path_x[path_ptr] = curr_x - 1;
                    path_y[path_ptr] = curr_y - 1;
                    path_ptr = (path_ptr + 1) % pathLength;
                    prev[curr_x - 1][curr_y - 1] = 3;
                    dist[curr_x - 1][curr_y - 1] = cost;
                }
                // north west
                if (curr_x > 0 && curr_y < 104 - 1 && prev[curr_x - 1][curr_y + 1] == 0 && (blocks[curr_x - 1][curr_y + 1] & 0x1280138) == 0 && (blocks[curr_x - 1][curr_y] & 0x1280108) ==
                        0 && (blocks[curr_x][curr_y + 1] & 0x1280120) == 0) {
                    path_x[path_ptr] = curr_x - 1;
                    path_y[path_ptr] = curr_y + 1;
                    path_ptr = (path_ptr + 1) % pathLength;
                    prev[curr_x - 1][curr_y + 1] = 6;
                    dist[curr_x - 1][curr_y + 1] = cost;
                }
                // south east
                if (curr_x < 104 - 1 && curr_y > 0 && prev[curr_x + 1][curr_y - 1] == 0 && (blocks[curr_x +
                        1][curr_y - 1] & 0x1280183) == 0 && (blocks[curr_x + 1][curr_y] & 0x1280180) == 0 && (blocks[curr_x][curr_y - 1] & 0x1280102) == 0) {
                    path_x[path_ptr] = curr_x + 1;
                    path_y[path_ptr] = curr_y - 1;
                    path_ptr = (path_ptr + 1) % pathLength;
                    prev[curr_x + 1][curr_y - 1] = 9;
                    dist[curr_x + 1][curr_y - 1] = cost;
                }
                // north east
                if (curr_x < 104 - 1 && curr_y < 104 - 1 && prev[curr_x + 1][curr_y + 1] == 0 && (blocks[curr_x
                        + 1][curr_y + 1] & 0x12801E0) == 0 && (blocks[curr_x + 1][curr_y] & 0x1280180) == 0 && (blocks[curr_x][curr_y + 1] & 0x1280120) == 0) {
                    path_x[path_ptr] = curr_x + 1;
                    path_y[path_ptr] = curr_y + 1;
                    path_ptr = (path_ptr + 1) % pathLength;
                    prev[curr_x + 1][curr_y + 1] = 12;
                    dist[curr_x + 1][curr_y + 1] = cost;
                }
            }

            return foundPath ? dist[curr_x][curr_y] : -1;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Uses Dijkstra path finding algorithm to find shortest path returns false if path isn't found.
     *
     * @param from Start Tile.
     * @param to   Destination Tile.
     * @return True if Path is found.
     */
    public static boolean foundPath(Tile from, Tile to) {
        return dijkstraDist(from.getX(), from.getY(), to.getX(), to.getY(), false) != -1;
    }

    /**
     * Uses Dijkstra path finding algorithm to find shortest path returns false if path isn't found.
     *
     * @param from     Start Tile.
     * @param to       Destination Tile.
     * @param isObject Is destination an  Object.
     * @return True if Path is found.
     */
    public static boolean foundPath(Tile from, Tile to, boolean isObject) {
        return dijkstraDist(from.getX(), from.getY(), to.getX(), to.getY(), isObject) != -1;
    }

    /**
     * Gets the Shortest Path distance between two tiles.
     *
     * @param from Start Tile.
     * @param to   Destination Tile.
     * @return Shortest Path distance between two tiles using Dijkstra algorithm, returns -1 if path isn't found.
     */
    public static int pathDistanceBetween(Tile from, Tile to) {
        if (foundPath(from, to)) {
            return dijkstraDist(from.getX(), from.getY(), to.getX(), to.getY(), false);
        }

        return -1;
    }

    /**
     * Gets the Shortest Path distance between two tiles.
     *
     * @param from     Start Tile.
     * @param to       Destination Tile.
     * @param isObject Is destination an Object.
     * @return Shortest Path distance between two tiles using Dijkstra algorithm, returns -1 if path isn't found.
     */
    public static int pathDistanceBetween(Tile from, Tile to, boolean isObject) {
        if (foundPath(from, to)) {
            return dijkstraDist(from.getX(), from.getY(), to.getX(), to.getY(), isObject);
        }

        return -1;
    }

    /**
     * Gets the shortest distance to a tile.
     *
     * @param tile Destination Tile.
     * @return Shortest distance to tile.
     */
    public static int pathDistanceTo(Tile tile) {
        return pathDistanceBetween(Players.getMyPlayer().getLocation(), tile);
    }

    /**
     * Gets the shortest distance to a tile.
     *
     * @param tile     Destination Tile.
     * @param isObject Is destination an Object.
     * @return Shortest distance to tile.
     */
    public static int pathDistanceTo(Tile tile, boolean isObject) {
        return pathDistanceBetween(Players.getMyPlayer().getLocation(), tile, isObject);
    }

    /**
     * Compares Coordinate data from the first and second tile data.
     *
     * @param first  First tile.
     * @param second Second tile.
     * @return True if Coordinate data from first and second tiles match.
     */
    public static boolean isSameTile(Tile first, Tile second) {
        return distanceBetween(first, second) == 0 && first.getPlane() == second.getPlane();
    }

    /**
     * Checks if player is at the destination tile.
     *
     * @param destination destination tile.
     * @return true if players location equals destination tile.
     */
    public static boolean atTile(Tile destination) {
        return isSameTile(Players.getMyPlayer().getLocation(), destination);
    }

    /**
     * Get's the difference between start and current.
     *
     * @param start   Starting value.
     * @param current Current value.
     * @return difference between start and current.
     */
    public static int gained(int start, int current) {
        return start - current;
    }

    /**
     * Get's the hourly difference between start and current.
     *
     * @param runtime Timer used for calculating hourly difference.
     * @param start   Starting value.
     * @param current Current value.
     * @return Hourly difference between start and current.
     */
    public static int gainedPerHour(Timer runtime, int start, int current) {
        return runtime.getPerHour(gained(start, current));
    }
}
