package util;

import Exceptions.FileException;
import Exceptions.SensorException;
import app.Collector;
import app.CollectorImpl;
import app.Sensor;
import app.SensorImpl;
import sensordata.SensorData;
import sensordata.SensorDataImpl;

import java.io.IOException;
import java.util.LinkedList;

public class SensorDataExchangerUI {

    public static void main(String[] args) throws IOException, InterruptedException, SensorException, FileException {
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

        String hostname = null;
        int port = -1;
        String portString = null;

        if (args.length == 2) { // variant 1: send
            hostname = args[0];
            portString = args[1];
        } else if (args.length == 1) { // variant 2: receive
            portString = args[0];

        }
        port = Integer.parseInt(portString);

        SensorData data = new SensorDataImpl(System.currentTimeMillis(), 0.2f, "measurementSensor");

        Collector collector = new CollectorImpl();

        Sensor sensor = new SensorImpl();

        if (hostname == null) {
            // receive
            collector.receiveSensorData(port);
        } else {
            // send
            sensor.sendSensorData(sensorDataLL, hostname, port);
        }

        collector.saveInAFile(collector.getSensorDataList(), "filename4.txt");

        //collector.readFromFile("filename4.txt");

    }
}
