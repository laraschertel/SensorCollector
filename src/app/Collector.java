package app;

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




}
