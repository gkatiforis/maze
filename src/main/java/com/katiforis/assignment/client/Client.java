package com.katiforis.assignment.client;

import com.katiforis.assignment.MazeManager;
import com.katiforis.assignment.algorithm.DFS;
import com.katiforis.assignment.algorithm.BruteForce;
import com.katiforis.assignment.entity.Route;
import com.katiforis.assignment.exception.MazeException;
import com.katiforis.assignment.entity.Maze;
import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * Client implementation through CMD
 */
public class Client {

    private static final Logger logger = Logger.getLogger(Client.class.getName());
    private static final String DEFAULT_FILE_PATH = Client.class.getClassLoader().getResource("maze.txt").getPath();
    private MazeManager mazeManager = new MazeManager(true);

    /**
     * Starts the Maze Challenge
     */
    public void start() {
        logger.debug("Start MazeChallenge.start");
        Maze maze = readMazeFromUser();

        System.out.println("\nBruteForce: ");
        Route routeBF = mazeManager.solveMaze(maze, new BruteForce());
        System.out.println(routeBF);

        System.out.println("\nDFS: ");
        Route routeDFS = mazeManager.solveMaze(maze, new DFS());
        System.out.println(routeDFS);

        logger.debug("End MazeChallenge.start");
    }


    /**
     * Loads maze based on user's input
     * @return Maze object
     */
    private Maze readMazeFromUser()  {
        logger.debug("Start MazeChallenge.readMazeFromUser");
        Maze  maze;

        int input;
        do {
            System.out.println("Please select input method: ");
            System.out.println("From file: " + InputMethod.FROM_FILE.getCode());
            System.out.println("Load the last maze used from cache: " + InputMethod.FROM_CACHE.getCode());
            Scanner scanner = new Scanner(System.in);

            if (!scanner.hasNextInt() || !InputMethod.isValid( input = scanner.nextInt()))
                continue;

            try{
                if (InputMethod.FROM_FILE.getCode() == input) {
                    System.out.println("Give file path or press d to read from default file: ");
                    scanner = new Scanner(System.in);
                    String filePath = scanner.next();
                    if(filePath.equalsIgnoreCase("d")){
                        filePath = DEFAULT_FILE_PATH;
                    }
                    maze = mazeManager.readFromFile(filePath);
                    break;
                } else if (InputMethod.FROM_CACHE.getCode() == input) {
                    maze = mazeManager.loadFromCache();
                    break;
                }
            }catch (MazeException e){
                logger.error("Error: " + e);
                System.out.println("Error: " + e.getMessage());
            }
        }while (true);
        logger.debug("End MazeChallenge.readMazeFromUser");
        return maze;
    }
}
