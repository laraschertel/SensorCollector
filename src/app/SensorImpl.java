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

   private LinkedList<SensorData> sensorDataList = new LinkedList<>();

    @Override
    public void sendSensorData(String hostname, int port) throws IOException, InterruptedException {

        Client client = new TCPConnector();
        Connection connection = client.connect(hostname, port);

        SensorDataSender sensorDataSender = new SensorDataExchanger();
        sensorDataSender.sendSensorData(this.sensorDataList, connection.getOutputStream());

    }

    @Override
    public void addSensorDataToList(SensorData sensorData) {
        this.sensorDataList.add(sensorData);
    }

    @Override
    public LinkedList<SensorData> getSensorDataList() {
        return this.sensorDataList;
    }
}
