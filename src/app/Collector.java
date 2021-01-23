package app;

import Exceptions.SensorException;
import sensordata.SensorData;
import tcp.Server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public interface Collector extends FileHandle{

    /**
     *
     * @param port listen on port and wait for a connection request from the sensor
     */
    void receiveSensorData(int port) throws IOException;

    /**
     *
     * @return a list with the received data from the sensor
     */
    LinkedList<SensorData> getSensorDataList();
    /**
     *
     * @param sensorValuesList
     * @param sensorName
     * @return the average from one sensor
     * @throws SensorException if the sensor doesnt exist
     */
    float getAverageOneSensor(LinkedList<SensorData> sensorValuesList, String sensorName) throws SensorException;

    /**
     *
     * @param sensorValuesList
     * @return the average from alls sensors
     * @throws SensorException if the list is empty -> cannot calulate average (0 dividion is not possible)
     */
    float getAverageAllSensors(LinkedList<SensorData> sensorValuesList) throws SensorException;






}
