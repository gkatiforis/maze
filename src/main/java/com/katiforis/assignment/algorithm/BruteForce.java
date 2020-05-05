package com.katiforis.assignment.algorithm;

import com.katiforis.assignment.entity.Maze;
import com.katiforis.assignment.entity.MazePoint;
import com.katiforis.assignment.entity.Point;
import com.katiforis.assignment.entity.Route;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * Brute force implementation
 */
public class BruteForce implements MazeSolver {
    private static final Logger logger = Logger.getLogger(BruteForce.class.getName());

   public Route solve(Maze maze){
       logger.debug("Start BruteForce.solve");
        if(maze == null){
            return new Route();
        }
       List<Point> deadlocks = new LinkedList<>();
       MazePoint startPoint =  maze.getStartPoint();
       List<Point> path = new LinkedList<>();
       path.add(startPoint);
       int x = startPoint.getX();
       int y = startPoint.getY();

        while(true){
            logger.debug("Current route:" + path);
            if(maze.isExit(x, y)){
                Route route = new Route(path);
                logger.debug("Route found: " + route.toString());
                return route;
            }
            else if (maze.canGo(x, y + 1)) {//go right
                path.add(maze.getPoint(x, y + 1));
                maze.setVisited(x, ++y, true);
            }else if (maze.canGo(x, y - 1)) {//go left
                path.add(maze.getPoint(x, y - 1));
                maze.setVisited(x, --y, true);
            }
            else if (maze.canGo(x + 1, y )) {//go down
                path.add(maze.getPoint(x + 1, y));
                maze.setVisited(++x, y, true);
            } else if (maze.canGo(x - 1, y)) {//go up
                path.add(maze.getPoint(x - 1, y));
                maze.setVisited(--x, y, true);
            }
            else{
                //mark point as deadlock and then
                // go back and search for non deadlock point
                for(int i = path.size() - 1; i>=0; i--){
                    Point point = path.get(i);
                    if(!deadlocks.contains(point)){
                        deadlocks.add(point);
                        x = point.getX();
                        y = point.getY();
                        maze.setVisited(x, y, false);
                        break;
                    }else{
                        //remove deadlock points from the final route
                        path.remove(i);
                    }
                }
                if(path.isEmpty()){
                    return new Route();
                }
            }
        }
    }
}
