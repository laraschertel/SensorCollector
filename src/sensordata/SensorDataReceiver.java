package sensordata;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

public interface SensorDataReceiver {
    /**
     * Recceive data from input and create new sensor data object
     * @param is
     * @return
     */
    LinkedList<SensorData> receiveSensorData(InputStream is) throws IOException;
}
