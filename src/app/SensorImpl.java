package app;

import sensordata.SensorData;
import sensordata.SensorDataExchanger;
import sensordata.SensorDataImpl;
import sensordata.SensorDataSender;
import tcp.Client;
import tcp.Connection;
import tcp.TCPConnector;

import java.io.*;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class SensorImpl extends FileHandleImpl implements Sensor {

    @Override
    public void sendSensorData(LinkedList<SensorData> sensorDataList, String hostname, int port) throws IOException, InterruptedException {

        Client client = new TCPConnector();
        Connection connection = client.connect(hostname, port);

        SensorDataSender sensorDataSender = new SensorDataExchanger();
        sensorDataSender.sendSensorData(sensorDataList, connection.getOutputStream());

    }
}
