package filepersistence;

import java.io.*;
import java.util.Arrays;
import java.util.Date;

public class WriteAndReadDataSet {

    public static void main(String[] args) {
        // three example data sets
        String sensorName = "MyGoodOldSensor"; // does not change

        long timeStamps[] = new long[3];
        timeStamps[0] = System.currentTimeMillis();
        timeStamps[1] = timeStamps[0] + 1; // milli sec later
        timeStamps[2] = timeStamps[1] + 1000; // second later

        float[][] values = new float[3][];
        // 1st measure .. just one value
        float[] valueSet = new float[1];
        values[0] = valueSet;
        valueSet[0] = (float) 1.5; // example value 1.5 degrees

        // 2nd measure .. just three values
        valueSet = new float[3];
        values[1] = valueSet;
        valueSet[0] = (float) 0.7;
        valueSet[1] = (float) 1.2;
        valueSet[2] = (float) 2.1;

        // 3rd measure .. two values
        valueSet = new float[2];
        values[2] = valueSet;
        valueSet[0] = (float) 0.7;
        valueSet[1] = (float) 1.2;

        // write three data set into a file
        // TODO: your job. use DataOutputStream / FileOutputStream

        String filename = "valuesets.txt";
        OutputStream os = null;
        InputStream is = null;
        int valuesize=values.length;


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(sensorName).append("\n\n");



        for (int i=0;i<valuesize; i++) {
            Date date = new Date(timeStamps[i]);

            stringBuilder.
                    append(date).
                    append("\n").
                    append(Arrays.toString(values[i])).append("\n\n");
        }

        String filecontent = stringBuilder.toString();
        byte[] filecontentBytes = filecontent.getBytes();
        int filelength = filecontentBytes.length;

        try {
            os = new FileOutputStream(filename);
            PrintStream ps = new PrintStream(os);
            ps.println(filecontent);

        } catch (IOException e) {
            e.printStackTrace();
        }



        // read data from file and print to System.out
        // TODO: your job use DataInputStream / FileInputStream


        try {
            is = new FileInputStream(filename);
            byte[] inputBytes = new byte[filelength];
            is.read(inputBytes);
            String inputStream = new String(inputBytes);

            
            System.out.println(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
