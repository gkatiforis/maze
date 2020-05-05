package com.katiforis.assignment.io;

import com.katiforis.assignment.entity.Maze;
import com.katiforis.assignment.entity.MazePoint;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 *  Reads Maze instance from text files
 */
public class MazeTextFileReader implements MazeFileReader {
    private static final Logger logger = Logger.getLogger(MazeTextFileReader.class.getName());

    @Override
    public Maze read(String path) throws IOException {
        logger.debug("Start MazeTextFileReader.read");

            File file = new File(path);
            if(!file.exists()){
                throw new IOException("File not found");
            }

            MazePoint[][] scene;
            AtomicInteger xIndex = new AtomicInteger(0);
            AtomicInteger yIndex = new AtomicInteger(0);

            try(Stream<String> line = Files.lines(file.toPath())){
                scene = line.map(String::toCharArray)
                        .map(chars ->{
                            MazePoint [] points = new MazePoint[chars.length];
                            int rowIndex = xIndex.getAndIncrement();
                            for(char ch : chars){
                                points[yIndex.get()] = new MazePoint(rowIndex, yIndex.getAndIncrement(), ch);
                            }
                            yIndex.set(0);
                            return points;
                        })
                        .toArray(MazePoint[][]::new);
                logger.debug("End MazeTextFileReader.read");
                return new Maze(scene);
            }
    }
}
