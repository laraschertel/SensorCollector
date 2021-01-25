package app;

import Exceptions.FileException;
import Exceptions.SensorException;
import org.junit.Assert;
import org.junit.Test;
import sensordata.*;

import javax.print.attribute.standard.NumberUp;
import javax.xml.crypto.Data;
import java.io.*;
import java.net.ConnectException;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class SensorDataExchangeTests {
    static final String FILENAME = "SensorData5.txt";
    private OutputStream os = new ByteArrayOutputStream();
    private InputStream is;


    SensorData s1 = new SensorDataImpl(2984394, 0.6f, "sensor1");
    SensorData s2 = new SensorDataImpl(2984374, 0.2f, "sensor2");
    SensorData s3 = new SensorDataImpl(3984394, 0.4f, "sensor1");
    SensorData s4 = new SensorDataImpl(2984394, 0.6f, "sensor1");
    SensorData s5 = new SensorDataImpl(2984374, 0.8f, "sensor2");
    SensorData s6 = new SensorDataImpl(3984394, 0.4f, "sensor1");


    @Test
    public void goodSaveLocalDataInFile() throws IOException, InterruptedException, FileException, SensorException {


        Sensor sensor = new SensorImpl();

        sensor.addSensorDataToList(s1);
        sensor.addSensorDataToList(s2);
        sensor.addSensorDataToList(s3);
        sensor.addSensorDataToList(s4);
        sensor.addSensorDataToList(s5);
        sensor.addSensorDataToList(s6);

        // sensor saves local Sensordata in a file
        sensor.saveInAFile(sensor.getSensorDataList(), FILENAME);

        // reads the data fom this file and adds it to a List of sensordata
        //sensor.readFromFile(FILENAME);
        LinkedList<SensorData> list = sensor.readFromFile(FILENAME);
        Assert.assertEquals(sensor.getSensorDataList().size(), list.size());

        for (int i = 0; i < sensor.getSensorDataList().size(); i++) {
            Assert.assertEquals(sensor.getSensorDataList().get(i).getSensorName(), list.get(i).getSensorName());
            Assert.assertEquals(sensor.getSensorDataList().get(i).getTimeStamp(), list.get(i).getTimeStamp());
            Assert.assertEquals(sensor.getSensorDataList().get(i).getValue(), list.get(i).getValue(), 0.001f);
        }

    }

    @Test (expected = FileException.class)
    public void badTryToReadNonExistingFile() throws IOException, FileException{


        Sensor sensor = new SensorImpl();

        sensor.readFromFile("notAFile.txt");

    }

    @Test (expected = NullPointerException.class)
    public void badTryToReadFile() throws IOException, FileException {

        Sensor sensor = new SensorImpl();

        // if file name is null throws a nullpointer exception
        LinkedList<SensorData> list = sensor.readFromFile(null);

    }


    @Test(expected = FileException.class)
    public void badSaveLocalDataInFile1() throws IOException, InterruptedException, FileException {

        Sensor sensor = new SensorImpl();

        sensor.addSensorDataToList(s1);
        sensor.addSensorDataToList(s2);
        sensor.addSensorDataToList(s3);
        sensor.addSensorDataToList(s4);
        sensor.addSensorDataToList(s5);
        sensor.addSensorDataToList(s6);

        // filename cannot contain only blank spaces
        sensor.saveInAFile(sensor.getSensorDataList(), " ");

    }

    @Test(expected = NullPointerException.class)
    public void badSaveLocalDataInFile() throws IOException,  FileException {

        Sensor sensor = new SensorImpl();

        sensor.addSensorDataToList(s1);
        sensor.addSensorDataToList(s2);
        sensor.addSensorDataToList(s3);
        sensor.addSensorDataToList(s4);
        sensor.addSensorDataToList(s5);
        sensor.addSensorDataToList(s6);

        // filename cannot be null -> a NullPointerException is expected
        sensor.saveInAFile(sensor.getSensorDataList(), null);

    }

    @Test(expected = NullPointerException.class)
    public void badSaveLocalDataInFile2() throws IOException, InterruptedException, FileException {

        Sensor sensor = new SensorImpl();

        // sensorlist cannot be null -> a NullPointerException is expected
        sensor.saveInAFile(null, FILENAME);

    }

}
