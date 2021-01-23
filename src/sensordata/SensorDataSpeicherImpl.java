package sensordata;


import Exceptions.SensorException;

import java.util.LinkedList;

public class SensorDataSpeicherImpl implements SensorDataSpeicher {

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

    @Override
    public int getAmountOfMeasuresOneSensor(LinkedList<SensorData> sensorValuesList, String sensorName) throws SensorException {
        int amountOfMeasures = 0;
        for (int i = 0; i < sensorValuesList.size(); i++) {
            if (sensorValuesList.get(i).getSensorName() == sensorName) {
                amountOfMeasures++;
            }
        }
        if (amountOfMeasures == 0) {
            throw new SensorException("This sensor is not on the list");
        } else {

            return amountOfMeasures;
        }
    }
}


