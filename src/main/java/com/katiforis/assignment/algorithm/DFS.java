package com.katiforis.assignment.algorithm;

import com.katiforis.assignment.entity.Point;
import com.katiforis.assignment.entity.Route;
import com.katiforis.assignment.entity.Maze;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Depth-first search
 */
public class DFS implements MazeSolver {
    private static final Logger logger = Logger.getLogger(DFS.class.getName());
    private static int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    private Point getNextCoordinate(int row, int col, int i, int j) {
        return new Point(row + i, col + j);
    }

   public Route solve(Maze maze){
       logger.debug("Start DFS.solve");
        if(maze == null){
            return new Route();
        }
       List<Point> path = new ArrayList<>();
       if (findRoute(maze, maze.getStartPoint().getX(), maze.getStartPoint().getY(), path)) {
           Route route = new Route(path);
           logger.debug("Route found: " + route.toString());
           return route;
       }
       logger.debug("End DFS.solve");
       return new Route();
    }

    private boolean findRoute(Maze maze, int row, int col, List<Point> route) {
        logger.debug("Start DFS.findRoute, current route:" + route);
        if (!maze.canGo(row, col)) {
            return false;
        }
        maze.setVisited(row, col, true);
        route.add(maze.getPoint(row, col));

        if (maze.isExit(row, col)) {
            return true;
        }
        for (int[] direction : DIRECTIONS) {
            Point coordinate = getNextCoordinate(row, col, direction[0], direction[1]);
            if (findRoute(maze, coordinate.getX(), coordinate.getY(), route)) {
                return true;
            }
        }
        route.remove(route.size() - 1);
        logger.debug("End DFS.findRoute");
        return false;
    }
}
