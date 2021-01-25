package app;

import Exceptions.FileException;
import sensordata.SensorData;
import sensordata.SensorDataImpl;

import java.io.*;
import java.util.LinkedList;

abstract public class FileHandleImpl implements FileHandle{


    @Override
    public void saveInAFile(LinkedList<SensorData> sensorValuesList, String filename) throws IOException, FileException {

        if(filename.trim().length() == 0){
            throw new FileException("File name is not valid");
        }

        File file = new File(filename);

        try {
            OutputStream os = new FileOutputStream(file);
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
    public LinkedList<SensorData> readFromFile(String filename) throws IOException, FileException, NullPointerException {

        File file = new File(filename);

        if(!file.exists()){
            throw new FileException("File doesnt exist");
        }

        FileInputStream fis = new FileInputStream(filename);
        DataInputStream dis = new DataInputStream(fis);

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
