package sensordata;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;

public interface SensorDataSender {

    /**
     * sends the sensor data that were saved in a list
     * @param sensorDataList is the list with the accumulated sensordata
     * @param os stream to recipient
     * @throws IOException if there is any I/O problem
     */
    void sendSensorData(LinkedList<SensorData> sensorDataList, OutputStream os) throws IOException;


}
