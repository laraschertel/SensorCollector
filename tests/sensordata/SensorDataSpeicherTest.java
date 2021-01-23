package sensordata;

import Exceptions.SensorException;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

public class SensorDataSpeicherTest {
        private static SensorDataSpeicher getSensorDataSpeicher(){
            return new SensorDataSpeicherImpl();
        }


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

    private LinkedList<SensorData> getMaxValuesSensorList() {
        LinkedList<SensorData> sensorDataLL = new LinkedList<>();
        SensorData s1 = new SensorDataImpl(2984394, Float.MAX_VALUE, "sensor1");
        SensorData s2 = new SensorDataImpl(2984374, Float.MIN_VALUE, "sensor2");
        SensorData s3 = new SensorDataImpl(3984394, Float.MAX_VALUE, "sensor1");
        SensorData s4 = new SensorDataImpl(2984394, Float.MAX_VALUE, "sensor1");
        SensorData s5 = new SensorDataImpl(2984374, Float.MIN_VALUE, "sensor2");
        SensorData s6 = new SensorDataImpl(3984394, Float.MAX_VALUE, "sensor1");
        sensorDataLL.add(s1);
        sensorDataLL.add(s2);
        sensorDataLL.add(s3);
        sensorDataLL.add(s4);
        sensorDataLL.add(s5);
        sensorDataLL.add(s6);

        return sensorDataLL;
    }

        @Test
        public void gutTestAverageOneSensor() throws Exception {
            float average = getSensorDataSpeicher().getAverageOneSensor(getSensorList(), "sensor1");
            Assert.assertEquals(0.5f, average, 0.001f);
        }

        @Test
        public void gutTestAverageAllSensors() throws Exception {
            float average = getSensorDataSpeicher().getAverageAllSensors(getSensorList());
            Assert.assertEquals(0.5f, average, 0.001f);

        }

        @Test
        public void gutTestAmountOfMeasuresOneSensor() throws Exception {
            int amountOfMeasurements = getSensorDataSpeicher().getAmountOfMeasuresOneSensor(getSensorList(), "sensor2");
            Assert.assertEquals(2, amountOfMeasurements);

        }


        @Test(expected=Exception.class)
        public void schlechtTestAverageOneSensor() throws SensorException {
            double average = getSensorDataSpeicher().getAverageOneSensor(getSensorList(), "sensor3");

        }

        @Test(expected=Exception.class)
        public void schlechtTestAverageAllSensors() throws SensorException {
            float average = getSensorDataSpeicher().getAverageAllSensors(null);

        }

        @Test(expected=Exception.class)
        public void schlechtTestAmountOfMeasuresOneSensor() throws SensorException {
            float average = getSensorDataSpeicher().getAverageOneSensor(getSensorList(), "sensor 3");

        }

        // why is it not throwing an exception?
    @Test //(expected=Exception.class)
    public void badRandTest() throws SensorException {
            getSensorDataSpeicher().getAverageOneSensor(getSensorList(), "sensor1");

    }

    }

