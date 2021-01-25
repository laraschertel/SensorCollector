package app;

import Exceptions.FileException;
import org.junit.Assert;
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
    static final String FILENAME = "SensorData5.txt";


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
    public void goodtSaveLocalDataInFile() throws IOException, InterruptedException, FileException {

        Sensor sensor = new SensorImpl();
        // send sensor data

        // sensor saves local Sensordata in a file
        sensor.saveInAFile(getSensorList(), FILENAME);

        // reads the data fom this file and adds it to a List of sensordata
        //sensor.readFromFile(FILENAME);

        Assert.assertEquals(getSensorList().size(), sensor.readFromFile(FILENAME).size());

        for (int i = 0; i < getSensorList().size(); i++) {
            Assert.assertEquals(getSensorList().get(i).getSensorName(), sensor.readFromFile(FILENAME).get(i).getSensorName());
            Assert.assertEquals(getSensorList().get(i).getTimeStamp(), sensor.readFromFile(FILENAME).get(i).getTimeStamp());
            Assert.assertEquals(getSensorList().get(i).getValue(), sensor.readFromFile(FILENAME).get(i).getValue(), 0.001f);
        }
    }

        @Test (expected = FileException.class)
        public void badSaveLocalDataInFile1() throws IOException, InterruptedException, FileException {

            Sensor sensor = new SensorImpl();
            // send sensor data

            // filename cannot contain only blank spaces
            sensor.saveInAFile(getSensorList(), " ");

    }

    @Test (expected = NullPointerException.class)
    public void badSaveLocalDataInFile() throws IOException, InterruptedException, FileException {

        Sensor sensor = new SensorImpl();
        // send sensor data

        // filename cannot be null -> a NullPointerException is expected
        sensor.saveInAFile(getSensorList(), null);

    }

    @Test (expected = NullPointerException.class)
    public void badSaveLocalDataInFile2() throws IOException, InterruptedException, FileException {

        Sensor sensor = new SensorImpl();
        // send sensor data

        // sensorlist cannot be null -> a NullPointerException is expected
        sensor.saveInAFile(null, FILENAME);

    }



}
