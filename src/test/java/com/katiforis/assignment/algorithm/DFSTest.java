package com.katiforis.assignment.algorithm;

import com.katiforis.assignment.io.MazeFileReader;
import com.katiforis.assignment.io.MazeTextFileReader;
import com.katiforis.assignment.entity.Maze;
import com.katiforis.assignment.entity.MazePoint;
import com.katiforis.assignment.entity.Point;
import com.katiforis.assignment.entity.Route;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(JMockit.class)
public class DFSTest {
    @Tested
    private DFS dfs;

    private MazeFileReader mazeFileReader = new MazeTextFileReader();

    private final String validMazeFile = DFSTest.class.getClassLoader().getResource("valid_maze.txt").getPath();

    @Test
    public void solveTest() throws IOException {
        List<Point> mazePoints = Arrays.asList(
                new MazePoint(1, 2, 'S'),
                new MazePoint(1, 3),
                new MazePoint(1, 4),
                new MazePoint(0, 4),
                new MazePoint(0 ,3),
                new MazePoint(0, 2, 'G'));

        Maze maze = mazeFileReader.read(validMazeFile);
        String excepted = new Route(mazePoints).toString();
        String result  = dfs.solve(maze).toString();
        assertEquals(excepted, result);
    }
}
