package app;

import sensordata.SensorData;
import tcp.Client;

import java.io.IOException;
import java.util.LinkedList;

public interface Sensor extends FileHandle{

    /**
     *
     *  sends the data from a sensordata list to a server
     * @param hostname name of the server
     * @param port
     * @throws IOException if there is any Input/Output problems
     */
    void sendSensorData(String hostname, int port) throws IOException;

    /**
     *
     * @param sensorData is added to a list of sensordata
     */
    void addSensorDataToList(SensorData sensorData);

    /**
     *
     * @return a list with the saved data
     */
    LinkedList<SensorData> getSensorDataList();
}
