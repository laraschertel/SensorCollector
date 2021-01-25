package sensordata;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

public interface SensorDataReceiver {

    /**
     * Receives sensordata and saves it in a list
     * @param is gets the data through an inputstream
     * @return a list with the received sensor data
     * @throws IOException if there is any I/O problems
     */
    LinkedList<SensorData> receiveSensorData(InputStream is) throws IOException;
}
