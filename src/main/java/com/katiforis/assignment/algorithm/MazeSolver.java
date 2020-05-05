package com.katiforis.assignment.algorithm;

import com.katiforis.assignment.entity.Maze;
import com.katiforis.assignment.entity.Route;

public interface MazeSolver {
    Route solve(Maze maze);
}
