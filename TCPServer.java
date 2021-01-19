package ru.geekbrains.Java_cloud.lesson_01;

import java.io.*;
import java.net.*;

class TCPServer {

    private final static String fileToSend = "D:\test1.txt";

    public static void main(String args[]) {

        while (true) {
            ServerSocket welcomeSocket = null;
            Socket connectionSocket = null;
            BufferedOutputStream outToClient = null;
            try {
                welcomeSocket = new ServerSocket(8187);
                connectionSocket = welcomeSocket.accept();
                outToClient = new BufferedOutputStream(connectionSocket.getOutputStream());
            } catch (IOException ex) {
                System.out.println("Error");
            }

            if (outToClient != null) {
                File myFile = new File( fileToSend );
                byte[] arr = new byte[(int) myFile.length()];

                FileInputStream fis = null;

                try {
                    fis = new FileInputStream(myFile);
                } catch (FileNotFoundException ex) {
                    System.out.println("Error");
                }
                BufferedInputStream bis = new BufferedInputStream(fis);

                try {
                    bis.read(arr, 0, arr.length);
                    System.out.println(arr);
                    outToClient.write(arr, 0, arr.length);
                    outToClient.flush();
                    outToClient.close();
                    connectionSocket.close();

                    // File sent, exit the main method
                    return;
                } catch (IOException ex) {
                    System.out.println("Error");
                }
            }
        }
    }
}
