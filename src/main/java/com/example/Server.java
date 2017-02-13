package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by simon blank on 2016-12-15.
 */
public class Server {
    public static void main(String[] args) {

        gui gui = new gui();
        gui.loadGui();


        ServerSocket serverSocket;
        Socket socketToServer;
        BufferedReader in;
        PrintWriter out;


///*
//  how to use start
//  först starta servern sen starta clienten
//  skriv i nedre textarean sen tryck post för att skicka det du skrivit
//  du kan ändra ditt namn 1 gång genom att skriva ett namn i översta text boxen och klicka på "set name"
// *

        try{
            serverSocket = new ServerSocket(5000);
            socketToServer =serverSocket.accept();
            out = new PrintWriter(socketToServer.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socketToServer.getInputStream()));


/////////////////////////////////send thread
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
///////////////////////////////////////////////////////////////////////




//////////////////////////////////fånga input
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
