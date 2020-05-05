# Maze Challenge

## Manage maze

The MazeManager object initializes, loads, solves, caches and validates Maze instances. 

## Read maze from file

MazeManager uses the MazeFileReader interface in order to accept different reading implementations. 
MazeTextFileReader it’s a implementation  that  constructs maze objects from txt files. 


## Cache maze instances

MazeManager uses the generic purpose CacheManager in order to serialize maze instances to disk.

## Solve maze
Strategy pattern applied to separate the  implementations of maze solver algorithms:

1. Brute force
2. Depth-first 

    

