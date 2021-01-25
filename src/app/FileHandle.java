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
     * @throws IOException if there is an I/O problem
     * @throws FileException if the file name is not valid (only blank spaces)
     */
    void saveInAFile(LinkedList<SensorData> sensorValuesList, String filename) throws IOException, FileException;

    /**
     *
     * @param filename name of the file
     * @return a list with the data that was in the file
     * @throws IOException
     * @throws FileException if tries to read from a file that doesnt exist
     */
    LinkedList<SensorData> readFromFile(String filename) throws IOException, FileException;

}
