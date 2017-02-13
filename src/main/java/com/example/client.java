package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by simon blank on 2016-12-15.
 */
public class client {


    public static void main(String[] args) {

        gui gui = new gui();
        gui.loadGui();

        Socket socket;
        BufferedReader in;
        PrintWriter out;


        try{
            socket = new Socket("127.0.0.1",5000);
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
///////////////////////////////////////send thread////////////////////////////////
            Thread send = new Thread(new Runnable() {


                String msg;
                @Override
                public void run() {
                    while (true) {

                        msg = gui.getMessagetemp();
                        if (msg != "") {
                            gui.deleteMessagetemp();
                            out.println(gui.getUser() + ": " + msg);
                            out.flush();
                            gui.addToChatArea("you wrote: " + msg);
                    }

                }
                }
            });
            send.start();
////////////////////////////////////////////////////////////////////////////




/////////////////////////////////fånga input//////////////////////////////////
            Thread recieve = new Thread(new Runnable() {
                String msg;


                @Override
                public void run() {
                    while (true){
                        try {
                            msg = in.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println(msg);
                        gui.addToChatArea(msg);
                    }

                }
            });
            recieve.start();
////////////////////////////////////////////////////////////////////////////////////////


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
