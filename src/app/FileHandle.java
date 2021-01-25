package app;

import Exceptions.FileException;
import sensordata.SensorData;

import java.io.IOException;
import java.util.LinkedList;

public interface FileHandle {

    /**
     *
     * @param sensorValuesList saves sensordata from a list in a file
     * @param filename name of the file
     * @throws IOException
     */
    void saveInAFile(LinkedList<SensorData> sensorValuesList, String filename) throws IOException, FileException;

    /**
     *
     * @param filename name of the file
     * @return a list with the data that was in the file
     * @throws IOException
     */
    LinkedList<SensorData> readFromFile(String filename) throws IOException;

}
