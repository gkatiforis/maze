package com.katiforis.assignment.entity;

import com.katiforis.assignment.exception.MazeException;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Represents the structure of maze, a two-dimensional array of {@link MazePoint}
 */
public class Maze implements Serializable {
    public static final long serialVersionUID = 42L;

    static char START_POINT = 'S';
    static char EXIT_POINT = 'G';
    static char WALL_POINT = 'X';
    static char SPACE_POINT = '_';

    private static Integer MAX_WIDTH = Integer.MAX_VALUE;
    private static Integer MAX_HEIGHT = Integer.MAX_VALUE;
    private static Integer MIN_WIDTH = 6;
    private static Integer MIN_HEIGHT = 2;

    private MazePoint[][] scene;
    private MazePoint startPoint;

    public Maze(MazePoint[][] scene) {
        this.scene = scene;
    }

    public void init(){
        for (MazePoint[] points : scene) {
            for (MazePoint current : points) {
               current.setVisited(false);
            }
        }
    }
    public MazePoint getPoint(int row, int col) {
        return scene[row][col];
    }
    private MazePoint[][] getScene() {
        return scene;
    }

    private Integer getWidth() {
        return scene[0].length;
    }

    private Integer getHeight()
    {
        return scene.length;
    }

    private boolean isVisited(int row, int col) {
        return isValidPoint(row, col) && scene[row][col].isVisited();
    }

    public void setVisited(int row, int col, boolean isVisisted) {
        if(!isValidPoint(row, col))return;
        scene[row][col].setVisited(isVisisted);
    }

    public MazePoint getStartPoint() {
        if(startPoint != null){
            return startPoint;
        }
        for (MazePoint[] points : this.getScene()) {
            for (MazePoint current : points) {
                if (current.getValue() == START_POINT) {
                    return  startPoint = current;
                }
            }
        }
        return null;
    }


    public boolean isExit(int row, int col) {
        return isValidPoint(row, col) && scene[row][col].getValue() == EXIT_POINT;
    }

    private boolean isWall(int row, int col) {
        return isValidPoint(row, col) && scene[row][col].getValue() == WALL_POINT;
    }

    private boolean isValidPoint(int row, int col) {
       return !( row > (this.getHeight() - 1) || row < 0 || col > (this.getWidth() - 1) || col < 0 );
    }

    public boolean canGo(int x, int y) {
        return this.isValidPoint(x , y) &&  !this.isVisited(x, y) && !this.isWall(x, y);
    }

    public void validate() throws MazeException {
        if(this.getHeight() > MAX_HEIGHT || this.getHeight() < MIN_HEIGHT
                || this.getWidth() > MAX_WIDTH || this.getWidth() < MIN_WIDTH){
            throw new MazeException("Invalid dimensions " + this.getWidth() + "x" + this.getHeight());
        }

        int startPointCount = 0;
        int exitPointCount = 0;

        for (MazePoint[] points : this.getScene()) {
            if(points.length != this.getWidth()){
                throw new MazeException("Rows with different length found");
            }
            for (MazePoint current : points) {
                if (current.getValue() == START_POINT) {
                    startPointCount++;
                }
                else if (current.getValue() == EXIT_POINT) {
                    exitPointCount++;
                }else if (current.getValue() != WALL_POINT && current.getValue() != SPACE_POINT) {
                    throw new MazeException("Invalid char found:  " + current.getValue());
                }
            }
        }
        if(startPointCount != 1){
            throw new MazeException("Only one start point allowed");
        }
        if(exitPointCount != 1){
            throw new MazeException("Only one exit point allowed");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Maze)) return false;

        Maze maze = (Maze) o;

        if (!Arrays.deepEquals(getScene(), maze.getScene())) return false;
        return getStartPoint() != null ? getStartPoint().equals(maze.getStartPoint()) : maze.getStartPoint() == null;
    }
}
