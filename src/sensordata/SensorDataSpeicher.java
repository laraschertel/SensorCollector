package sensordata;

import Exceptions.SensorException;

import java.util.LinkedList;

public interface SensorDataSpeicher {
    /**
     *
     * @return average from one sensor
     */
    float getAverageOneSensor(LinkedList<SensorData> sensorValuesList, String sensorName) throws SensorException;

    /**
     * @return average from all the sensors
     */
    float getAverageAllSensors(LinkedList<SensorData> sensorValuesList) throws SensorException;

    /**
     *
     * @return the amount of measurements from a sensor
     */
    int getAmountOfMeasuresOneSensor(LinkedList<SensorData> sensorValuesList, String sensorName) throws SensorException;





}
