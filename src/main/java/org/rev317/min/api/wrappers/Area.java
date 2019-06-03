package org.rev317.min.api.wrappers;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 * @author Matt, JKetelaar
 */
public class Area {
    private Polygon p;

    /**
     * Initializes a PolygonArea with the tiles given
     *
     * @param tiles tiles to use in the area
     */
    public Area(Tile... tiles) {
        this.p = new Polygon();
        for (Tile tile : tiles) {
            p.addPoint(tile.getX(), tile.getY());
        }
    }

    /**
     * Adds a tile to the area
     *
     * @param t The tile to add the area
     */
    public void addTile(Tile t) {
        p.addPoint(t.getX(), t.getY());
    }

    /**
     * Gets all the tiles that are points in the area
     *
     * @return the tiles.
     */
    public Tile[] getPoints() {
        Tile[] tiles = new Tile[p.npoints];
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new Tile(p.xpoints[i], p.ypoints[i]);
        }

        return tiles;
    }

    /**
     * Gets all the tiles that are contained within the points.
     *
     * @return the tiles.
     */
    public Tile[] getTiles() {
        int             lowestX  = -1;
        int             lowestY  = -1;
        int             highestX = -1;
        int             highestY = -1;
        ArrayList<Tile> t        = new ArrayList<>();
        for (int i : p.xpoints) {
            if (i < lowestX || lowestX == -1) {
                lowestX = i;
            }
            if (i > highestX || highestX == -1) {
                highestX = i;
            }
        }
        for (int i : p.ypoints) {
            if (i < lowestY || lowestY == -1) {
                lowestY = i;
            }
            if (i > highestY || highestY == -1) {
                highestY = i;
            }
        }
        for (int x = lowestX - 1; x < highestX + 1; x++) {
            for (int y = lowestY - 1; y < highestY + 1; y++) {
                if (this.contains(x, y)) {
                    t.add(new Tile(x, y));
                }
            }
        }

        return t.toArray(new Tile[t.size()]);
    }

    /**
     * Checks if a tile is in the area
     *
     * @param tile The tile to check
     *
     * @return <b>true</b> if area does contain the tile, otherwise <b>false</b>
     */
    public boolean contains(Tile tile) {
        return this.contains(tile.getX(), tile.getY());
    }

    /**
     * Checks if a tile is in the area
     *
     * @param x The x-axis from the tile
     * @param y The y-axis from the tile
     *
     * @return True if the area does contain the tile, otherwise false
     */
    public boolean contains(int x, int y) {
        int     i;
        int     j;
        boolean result = false;
        for (i = 0, j = p.npoints - 1; i < p.npoints; j = i++) {
            if ((p.ypoints[i] > y - 1) != (p.ypoints[j] > y - 1)
                    && (x <= (p.xpoints[j] - p.xpoints[i]) * (y - p.ypoints[i])
                    / (p.ypoints[j] - p.ypoints[i]) + p.xpoints[i])) {
                result = !result;
            }
        }

        return result;
    }

    /**
     * Returns a random tile from the area
     *
     * @return The Tile
     */
    public Tile getRandomTile()
    {
        if (p.npoints < 2)
            return null;
        
        ArrayList<Polygon> polyTriangles = new ArrayList<>();
        ArrayList<Double> polyAreas = new ArrayList<>();
        double totalPolyArea = 0;
        //https://en.wikipedia.org/wiki/Polygon_triangulation
        for (int i = 1; i < p.npoints - 1; i++)
        {
            polyTriangles.add(new Polygon(new int[]{p.xpoints[0], p.xpoints[i], p.xpoints[i + 1]}, new int[]{p.ypoints[0], p.ypoints[i], p.ypoints[i + 1]}, 3));
            polyAreas.add((double) ((p.xpoints[0] * p.ypoints[1] + p.xpoints[1] * p.ypoints[2] + p.xpoints[2] * p.ypoints[0] -
                    p.xpoints[0] * p.ypoints[2] - p.xpoints[1] * p.ypoints[0]) - p.xpoints[2] * p.ypoints[1]) /2);
            totalPolyArea += polyAreas.get(i-1);
        }
        Random rand = new Random();
        double weightedAverage = rand.nextDouble();
        int i = -1;
        while (weightedAverage > 0)
        {
            i++;
            weightedAverage -= polyAreas.get(i)/totalPolyArea;
        }
        double r1 = rand.nextDouble();
        double r2 = rand.nextDouble();
        //https://www.cs.princeton.edu/~funk/tog02.pdf section 4.2
        int x = (int) Math.round((1 - Math.sqrt(r1)) * polyTriangles.get(i).xpoints[0] +
                Math.sqrt(r1) * (1 - r2) * polyTriangles.get(i).xpoints[1] +
                Math.sqrt(r1) * r2 * polyTriangles.get(i).xpoints[2]);
        int y = (int) Math.round((1 - Math.sqrt(r1)) * polyTriangles.get(i).ypoints[0] +
                Math.sqrt(r1) * (1 - r2) * polyTriangles.get(i).ypoints[1] +
                Math.sqrt(r1) * r2 * polyTriangles.get(i).ypoints[2]);

        return new Tile(x, y);
    }
}
