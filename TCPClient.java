package ru.geekbrains.Java_cloud.lesson_01;

import java.io.*;
import java.io.ByteArrayOutputStream;
import java.net.*;

class TCPClient {

    private final static String serverIP = "127.0.0.1";
    private final static int serverPort = 8187;
    private final static String fileOutput = "d:/answer.txt";

    public static void main(String args[]) {
        byte[] b = new byte[1];
        int bytesRead;

        Socket clientSocket = null;
        InputStream is = null;

        try {
            clientSocket = new Socket( serverIP , serverPort );
            is = clientSocket.getInputStream();
        } catch (IOException ex) {
            System.out.println("Error");
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        if (is != null) {
            FileOutputStream fos = null;
            BufferedOutputStream bos = null;
            try {
                fos = new FileOutputStream( fileOutput );
                bos = new BufferedOutputStream(fos);
                bytesRead = is.read(b, 0, b.length);
                do {
                    baos.write(b);
                    bytesRead = is.read(b);
                } while (bytesRead != -1);
                bos.write(baos.toByteArray());
                bos.flush();
                bos.close();
                clientSocket.close();
            } catch (IOException ex) {
                System.out.println("Error");
            }
        }
    }
}