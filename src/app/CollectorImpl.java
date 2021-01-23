package app;

import sensordata.SensorData;
import sensordata.SensorDataExchanger;
import sensordata.SensorDataReceiver;
import tcp.Connection;
import tcp.Server;
import tcp.TCPConnector;

import java.io.IOException;
import java.util.LinkedList;

public class CollectorImpl extends FileHandleImpl implements Collector {

    public LinkedList<SensorData> sensorDataList = new LinkedList<>();

    @Override
    public void receiveSensorData(int port) throws IOException {

        Server server = new TCPConnector();
        Connection connection = server.acceptConnection(port);


        SensorDataReceiver sensorDataReceiver = new SensorDataExchanger();
        this.sensorDataList = sensorDataReceiver.receiveSensorData(connection.getInputStream());

    }

    @Override
    public LinkedList<SensorData> getSensorDataList() {
        return this.sensorDataList;
    }

}
