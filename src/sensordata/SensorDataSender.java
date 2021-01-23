package sensordata;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;

public interface SensorDataSender {
    /**
     * send sensor data set
     * @param
     * @param os stream to recipient
     * @throws IOException
     */
    void sendSensorData(LinkedList<SensorData> sensorDataList, OutputStream os) throws IOException;

}
