package com.katiforis.assignment;

import com.katiforis.assignment.exception.MazeException;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;


@RunWith(JMockit.class)
public class MazeManagerTest {
    @Tested
    private MazeManager mazeManager;
    private final Path cacheFolder = Paths.get("src","test", "resources", "cache").toAbsolutePath();
    private final String validMazeFile = MazeManagerTest.class.getClassLoader().getResource("valid_maze.txt").getPath();
    private final String invalidCharactersMazeFile = MazeManagerTest.class.getClassLoader().getResource("invalid_characters_maze.txt").getPath();
    private final String manyStartPointsMazeFile = MazeManagerTest.class.getClassLoader().getResource("many_start_points_maze.txt").getPath();
    private final String manyEndPointsMazeFile = MazeManagerTest.class.getClassLoader().getResource("many_end_points_maze.txt").getPath();
    private final String noStartPointsMazeFile = MazeManagerTest.class.getClassLoader().getResource("no_start_points_maze.txt").getPath();
    private final String noEndPointsMazeFile = MazeManagerTest.class.getClassLoader().getResource("no_end_points_maze.txt").getPath();
    private final String invalidDimensionsMazeFile = MazeManagerTest.class.getClassLoader().getResource("invalid_dimensions_maze.txt").getPath();
    private final String rowsWithDifferentLength = MazeManagerTest.class.getClassLoader().getResource("rows_different_length.txt").getPath();

    @Before
    public void init(){
        mazeManager =  new MazeManager(true);
    }

    @Test
    public void readFromFileWithCacheTest() throws MazeException {
         mazeManager.readFromFile(validMazeFile);
         assertEquals(1, cacheFolder.toFile().listFiles().length);
    }

    @Test(expected = MazeException.class)
    public void readFromFileInvalidCharactersTest() throws MazeException {
        mazeManager.readFromFile(invalidCharactersMazeFile);
    }

    @Test(expected = MazeException.class)
    public void readFromFileManyStartPointsMazeFileTest() throws MazeException {
        mazeManager.readFromFile(manyStartPointsMazeFile);
    }

    @Test(expected = MazeException.class)
    public void readFromFileManyEndPointsMazeFileTest() throws MazeException {
        mazeManager.readFromFile(manyEndPointsMazeFile);
    }

    @Test(expected = MazeException.class)
    public void readFromFileNoStartPointsMazeFileTest() throws MazeException {
        mazeManager.readFromFile(noStartPointsMazeFile);
    }

    @Test(expected = MazeException.class)
    public void readFromFileNoEndPointsMazeFileTest() throws MazeException {
        mazeManager.readFromFile(noEndPointsMazeFile);
    }

    @Test(expected = MazeException.class)
    public void readFromFileInvalidDimensionsTest() throws MazeException {
        mazeManager.readFromFile(invalidDimensionsMazeFile);
    }

    @Test(expected = MazeException.class)
    public void readFromFileRowsWithDifferentLengthTest() throws MazeException {
        mazeManager.readFromFile(rowsWithDifferentLength);
    }

    @Test
    public void loadFromCacheNullCacheManagerTest() throws MazeException {
        MazeManager mazeManager =  new MazeManager(false);
       assertNull(mazeManager.loadFromCache());
    }

    @Test
    public void loadFromCacheTest() throws MazeException {
        assertNotNull(mazeManager.loadFromCache());
    }
}
