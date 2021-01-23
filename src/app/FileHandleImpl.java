package app;

import sensordata.SensorData;
import sensordata.SensorDataImpl;

import java.io.*;
import java.util.LinkedList;

abstract public class FileHandleImpl implements FileHandle{


    @Override
    public void saveInAFile(LinkedList<SensorData> sensorValuesList, String filename) throws IOException {
        try {
            OutputStream os = new FileOutputStream(filename);
            DataOutputStream dos = new DataOutputStream(os);
            try {
                dos.writeInt(sensorValuesList.size());
                for(int i = 0; i < sensorValuesList.size(); i++) {
                    dos.writeLong(sensorValuesList.get(i).getTimeStamp());
                    dos.writeFloat(sensorValuesList.get(i).getValue());
                    dos.writeUTF(sensorValuesList.get(i).getSensorName());
                }
            } catch (IOException ex) {
                System.err.println("couldn’t write data - fatal");
                System.exit(0);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Could not open file - fatal");
            System.exit(0);
        }
    }

    @Override
    public LinkedList<SensorData> readFromFile(String filename) throws IOException {
        InputStream is = new FileInputStream(filename);
        DataInputStream dis = new DataInputStream(is);

        LinkedList<SensorData> list = new LinkedList<>();

        int lenght = dis.readInt();
        for (int i = 0; i < lenght ; i++) {
            long timestamp = dis.readLong();
            float value = dis.readFloat();
            String sensorname = dis.readUTF();
            list.add(new SensorDataImpl(timestamp, value, sensorname));
        }

        return list;
    }
}
