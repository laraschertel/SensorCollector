package app;

import org.junit.Test;
import sensordata.SensorData;
import sensordata.SensorDataImpl;

import javax.print.attribute.standard.NumberUp;
import java.io.IOException;
import java.net.ConnectException;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class SensorDataExchangeTests {
    static final int PORT = 555;
    static final String HOSTNAME = "localhost";
    static final String FILENAME = "SensorData.txt";


    private LinkedList<SensorData> getSensorList() {
        LinkedList<SensorData> sensorDataLL = new LinkedList<>();
        SensorData s1 = new SensorDataImpl(2984394, 0.6f, "sensor1");
        SensorData s2 = new SensorDataImpl(2984374, 0.2f, "sensor2");
        SensorData s3 = new SensorDataImpl(3984394, 0.4f, "sensor1");
        SensorData s4 = new SensorDataImpl(2984394, 0.6f, "sensor1");
        SensorData s5 = new SensorDataImpl(2984374, 0.8f, "sensor2");
        SensorData s6 = new SensorDataImpl(3984394, 0.4f, "sensor1");
        sensorDataLL.add(s1);
        sensorDataLL.add(s2);
        sensorDataLL.add(s3);
        sensorDataLL.add(s4);
        sensorDataLL.add(s5);
        sensorDataLL.add(s6);

        return sensorDataLL;
    }

    @Test
    public void gutSaveLocalDataInFile() throws IOException, InterruptedException {

        Sensor sensor = new SensorImpl();
        // send sensor data

        // sensor saves local Sensordata in a file
        sensor.saveInAFile(getSensorList(), FILENAME);

        // reads the data fom this file and adds it to a List of sensordata
        sensor.readFromFile(FILENAME);

    }

    @Test (expected = NullPointerException.class)
    public void badSaveLocalDataInFile() throws IOException, InterruptedException {

        Sensor sensor = new SensorImpl();
        // send sensor data

        // filename cannot be null -> a NullPointerException is expected
        sensor.saveInAFile(getSensorList(), null);

    }

    @Test (expected = NullPointerException.class)
    public void badSaveLocalDataInFile2() throws IOException, InterruptedException {

        Sensor sensor = new SensorImpl();
        // send sensor data

        // sensorlist cannot be null -> a NullPointerException is expected
        sensor.saveInAFile(null, FILENAME);

    }

 /*   @Test
    public void gutDataExchangeTest() throws IOException, InterruptedException {

            Collector collector = new CollectorImpl();

            // collector is listening on the port, waiting for the Sensor to send data
            collector.receiveSensorData(PORT);

            Sensor sensor = new SensorImpl();

            // sensor can connect as TCP-Client and send the collected Data to the collector
            sensor.sendSensorData(getSensorList(), HOSTNAME, PORT);

            // collector can save the received data in a file
             collector.saveInAFile(collector.getSensorDataList(), "filename.txt");

             // reads data from the file and adds it to a list of sensor data
            collector.readFromFile("filename.txt");
    }

  */

    @Test (expected = ConnectException.class)
    public void badDataExchangeTest() throws IOException, InterruptedException {


        Sensor sensor = new SensorImpl();

        // sensor tries to connect before port is open -> connectException is expected
        sensor.sendSensorData(getSensorList(), HOSTNAME, PORT);

        Collector collector = new CollectorImpl();

        // collector is listening on the port, waiting for the Sensor to send data
        collector.receiveSensorData(PORT);


    }






}
