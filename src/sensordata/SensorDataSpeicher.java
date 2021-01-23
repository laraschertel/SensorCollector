package sensordata;

import Exceptions.SensorException;

import java.util.LinkedList;

public interface SensorDataSpeicher {
    /**
     *
     * @param sensorValuesList
     * @param sensorName
     * @return the average from one sensor
     * @throws SensorException if the sensor doesnt exist
     */
    float getAverageOneSensor(LinkedList<SensorData> sensorValuesList, String sensorName) throws SensorException;

    /**
     *
     * @param sensorValuesList
     * @return the average from alls sensors
     * @throws SensorException if the list is empty -> cannot calulate average (0 dividion is not possible)
     */
    float getAverageAllSensors(LinkedList<SensorData> sensorValuesList) throws SensorException;

    /**
     *
     * @param sensorValuesList
     * @param sensorName
     * @return the amount of measures from the deisred sensor
     * @throws SensorException if the sensor doesnt exist
     */
    int getAmountOfMeasuresOneSensor(LinkedList<SensorData> sensorValuesList, String sensorName) throws SensorException;





}
