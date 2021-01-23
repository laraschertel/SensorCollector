package sensordata;

import java.io.*;
import java.util.LinkedList;

public class SensorDataExchanger implements SensorDataSender, SensorDataReceiver{
    @Override
    public void sendSensorData(LinkedList<SensorData> sensorDataList, OutputStream os) throws IOException {
        DataOutputStream dos = new DataOutputStream(os);

        dos.writeInt(sensorDataList.size());

        for(int i = 0; i < sensorDataList.size(); i++) {
            dos.writeLong(sensorDataList.get(i).getTimeStamp());
            dos.writeFloat(sensorDataList.get(i).getValue());
            dos.writeUTF(sensorDataList.get(i).getSensorName());
        }

        os.close();

    }

    @Override
    public LinkedList<SensorData> receiveSensorData(InputStream is) throws IOException {

        LinkedList<SensorData> sensorDataList = new LinkedList<>();
        DataInputStream dis = new DataInputStream(is);

       int length = dis.readInt();

       for(int i = 0; i < length; i++) {
           long timeStamp = dis.readLong();
           float value = dis.readFloat();
           String sensorName = dis.readUTF();
          SensorData data = new SensorDataImpl(timeStamp, value, sensorName);
          sensorDataList.add(data);
       }

       return sensorDataList;
    }


}
