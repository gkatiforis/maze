package com.katiforis.assignment.io;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Implements a generic serialization functionality to disk
 */
public class CacheManager<T extends Serializable> {
    private static final Logger logger = Logger.getLogger(CacheManager.class.getName());
    private Path CACHE_PATH = Paths.get("src","main", "resources", "cache").toAbsolutePath();

    public CacheManager(String path){
        logger.debug("Start CacheManager");
        CACHE_PATH = Paths.get(path);
        logger.debug("End CacheManager");
    }

    public CacheManager(){}

    public T load(String filename) throws IOException, ClassNotFoundException {
        logger.debug("Start CacheManager.load");
        try (FileInputStream fileInputStream = new FileInputStream(CACHE_PATH.toFile() + "\\" +filename);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            T obj = (T) objectInputStream.readObject();
            logger.debug("End CacheManager.load");
            return obj;
        }
    }

    public void write(String filename, T object) throws IOException {
        logger.debug("Start CacheManager.load");
        try (FileOutputStream fileOutputStream = new FileOutputStream(CACHE_PATH.toFile() + "\\" + filename);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
             objectOutputStream.writeObject(object);
             objectOutputStream.flush();
        }
        logger.debug("End CacheManager.load");
    }
}
