package com.katiforis.assignment;

import com.katiforis.assignment.algorithm.MazeSolver;
import com.katiforis.assignment.entity.Route;
import com.katiforis.assignment.exception.MazeException;
import com.katiforis.assignment.entity.Maze;
import com.katiforis.assignment.io.CacheManager;
import com.katiforis.assignment.io.MazeFileReader;
import com.katiforis.assignment.io.MazeTextFileReader;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for reading, validating, solving, caching {@link Maze} instances
 */
public class MazeManager {
    private static final Logger logger = Logger.getLogger(MazeManager.class.getName());
    private static final String CACHE_MAZE_FILE_NAME = "cachemaze.ser";
    private CacheManager<Maze> cacheManager;
    private MazeFileReader reader;

    private MazeManager(){}

    public MazeManager(boolean cacheEnabled){
        logger.debug("Start MazeManager.MazeManager");
        this.reader = new MazeTextFileReader();
        if(cacheEnabled){
            this.cacheManager = new CacheManager<>();
        }
        logger.debug("End MazeManager.MazeManager");
    }

    /**
     * Reads Maze instance from file
     * @param path to file
     * @return Maze
     * @throws MazeException
     */
    public Maze readFromFile(String path) throws MazeException {
        logger.debug("Start MazeManager.readFromFile");
        Maze maze;
        try {
            maze = reader.read(path);
        } catch (IOException e) {
            logger.debug(e);
            throw new MazeException("Error upon reading maze: " + e.getMessage());
        }

       maze.validate();

        try {
            if(cacheManager != null) {
                cacheManager.write(CACHE_MAZE_FILE_NAME, maze);
            }
        } catch (IOException e) {
            logger.debug(e);
            throw new MazeException("Error upon writing maze: " + e.getMessage());
        }
        logger.debug("End MazeManager.readFromFile");
        return maze;
    }

    /**
     *  Loads Maze instance
     * @return Maze instance from cache
     * @throws MazeException
     */
    public Maze loadFromCache() throws MazeException {
        logger.debug("Start MazeManager.readFromFile");
        try {
            if(cacheManager != null) {
                return cacheManager.load(CACHE_MAZE_FILE_NAME);
            }
        } catch (Exception e) {
            throw new MazeException("Error during reading maze from cache: " + e.getMessage());
        }
        logger.debug("End MazeManager.readFromFile");
        return null;
    }

    /**
     * Solves maze with the given algorithm
     * @param maze
     * @param algorithm
     */
    public Route solveMaze(final Maze maze, final MazeSolver algorithm){
        logger.debug("Start MazeChallenge.solveMazeDFS");
        maze.init();
        return algorithm.solve(maze);
    }
}
