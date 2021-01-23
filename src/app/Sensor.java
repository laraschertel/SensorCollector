package app;

import sensordata.SensorData;
import tcp.Client;

import java.io.IOException;
import java.util.LinkedList;

public interface Sensor extends FileHandle{

    /**
     *
     * @param sensorDataList sends the data from a sensordata list to a server
     * @param hostname name of the server
     * @param port
     * @throws IOException
     */
    void sendSensorData(LinkedList<SensorData> sensorDataList, String hostname, int port) throws IOException, InterruptedException;

}
