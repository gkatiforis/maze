package com.katiforis.assignment;


import com.katiforis.assignment.exception.MazeException;
import com.katiforis.assignment.entity.Maze;
import com.katiforis.assignment.entity.MazePoint;
import com.katiforis.assignment.io.MazeTextFileReader;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(JMockit.class)
public class MazeTextFileReaderTest {
    @Tested
    private MazeTextFileReader mazeTextFileReader;

    private final String validMazeFile = MazeTextFileReaderTest.class.getClassLoader().getResource("valid_maze.txt").getPath();

    private Maze validMaze;

    @Before
    public void init(){
        validMaze = initValidMaze();
    }

    private Maze initValidMaze(){
        MazePoint[][] scene =
                {{new MazePoint(0, 0, '_')
                        ,new MazePoint(0, 1, '_')
                        ,new MazePoint(0, 2, 'G')
                        ,new MazePoint(0, 3, '_')
                        ,new MazePoint(0, 4, '_')
                        ,new MazePoint(0, 5, 'X')},

                        {new MazePoint(1, 0, '_')
                        ,new MazePoint(1, 1, '_')
                        ,new MazePoint(1, 2, 'S')
                        ,new MazePoint(1, 3, '_')
                        ,new MazePoint(1, 4, '_')
                        ,new MazePoint(1, 5, 'X')}};

        return new Maze(scene);

    }

    @Test
    public void readTest() throws IOException {
        Maze mazeFromFile =  mazeTextFileReader.read(validMazeFile);
        assertEquals(validMaze, mazeFromFile);
    }

    @Test(expected = IOException.class)
    public void readFileNotFountTest() throws IOException, MazeException {
        Maze mazeFromFile =  mazeTextFileReader.read("invalid/path");
        assertEquals(validMaze, mazeFromFile);
    }
}
