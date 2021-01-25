package app;

import Exceptions.FileException;
import Exceptions.SensorException;
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
    public void receiveSensorData(int port) throws IOException, FileException {

        Server server = new TCPConnector();
        Connection connection = server.acceptConnection(port);

        SensorDataReceiver sensorDataReceiver = new SensorDataExchanger();
        sensorDataList =  sensorDataReceiver.receiveSensorData(connection.getInputStream());

        saveInAFile(sensorDataList, "SensorDataFile.txt");

    }

    @Override
    public LinkedList<SensorData> getSensorDataList() {
        return this.sensorDataList;
    }

    @Override
    public float getAverageOneSensor(LinkedList<SensorData> sensorValuesList, String sensorName) throws SensorException {
        float sumSensorValues = 0;
        int amountOfMeasures = 0;
        for (int i = 0; i < sensorValuesList.size(); i++) {
            if (sensorValuesList.get(i).getSensorName() == sensorName) {
                sumSensorValues += sensorValuesList.get(i).getValue();
                amountOfMeasures++;
            }
        }

        if(amountOfMeasures == 0){
            throw new SensorException("This sensor is not on the list");
        }else {

            return sumSensorValues / (amountOfMeasures);
        }
    }

    @Override
    public float getAverageAllSensors(LinkedList<SensorData> sensorValuesList) throws SensorException {
        float sumSensorValues = 0;
        for (int i = 0; i < sensorValuesList.size(); i++) {
            sumSensorValues += sensorValuesList.get(i).getValue();
        }
        if(sensorValuesList.size() == 0){
            throw new SensorException("The sensor list is empty, cannot calculate average");
        }
        return sumSensorValues / (sensorValuesList.size());
    }

}
