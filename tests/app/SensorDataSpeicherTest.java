package app;

import Exceptions.SensorException;
import app.Collector;
import app.CollectorImpl;
import org.junit.Assert;
import org.junit.Test;
import sensordata.SensorData;
import sensordata.SensorDataImpl;

import java.util.LinkedList;

public class SensorDataSpeicherTest {


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
        SensorData s7 = new SensorDataImpl(2984374, Float.MIN_VALUE, "sensor2");
        SensorData s8 = new SensorDataImpl(2984374, Float.MIN_VALUE, "sensor2");
        sensorDataLL.add(s1);
        sensorDataLL.add(s2);
        sensorDataLL.add(s3);
        sensorDataLL.add(s4);
        sensorDataLL.add(s5);
        sensorDataLL.add(s6);
        sensorDataLL.add(s7);
        sensorDataLL.add(s8);

        return sensorDataLL;
    }

    Collector collector = new CollectorImpl();


         @Test
        public void goodTestAverageOneSensor() throws Exception {
            float average = collector.getAverageOneSensor(getSensorList(), "sensor1");
            Assert.assertEquals(0.5f, average, 0.001f);
        }


        @Test
        public void goodTestAverageAllSensors() throws Exception {
            float average = collector.getAverageAllSensors(getSensorList());
            Assert.assertEquals(0.5f, average, 0.001f);

        }


        @Test(expected=SensorException.class)
        public void badTestAverageOneSensor() throws SensorException {
             collector.getAverageOneSensor(getSensorList(), "sensor3");

        }

        @Test(expected= NullPointerException.class)
        public void badTestAverageAllSensors() throws SensorException {

             // list cannot be null -> throws a NullPointerException
             collector.getAverageAllSensors(null);

        }

        @Test
        public void badRandTest() throws SensorException {
             float average = collector.getAverageOneSensor(getMaxValuesSensorList(), "sensor1");

             // if float MAX_VALUE is added to float MAX_VALUE then the result value is "infinity"
             Assert.assertNotEquals(Float.MAX_VALUE, average, 0.001f);

         }

         @Test
         public void goodRandTest2() throws SensorException {

             float average = collector.getAverageOneSensor(getMaxValuesSensorList(), "sensor2");

             Assert.assertEquals(Float.MIN_VALUE, average, 0.001f);

    }

        @Test
        public void goodOnlyValueIsZero() throws SensorException {
                 LinkedList<SensorData> list = new LinkedList<>();
                 list.add( new SensorDataImpl(8492845, 0, "sensor4"));

                 float average = collector.getAverageOneSensor(list, "sensor4");

                 Assert.assertEquals(0, average, 0.001f);

        }

        @Test (expected = SensorException.class)
        public void badListIsEmpty() throws SensorException {
            LinkedList<SensorData> list = new LinkedList<>();

            // list cannot be empty
            collector.getAverageAllSensors(list);

        }


    }



