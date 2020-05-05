package com.katiforis.assignment.io;

import com.katiforis.assignment.entity.Maze;

import java.io.IOException;

/**
 *  Reads Maze instance from file
 */
public interface MazeFileReader{
     Maze read(String path) throws IOException;
}
