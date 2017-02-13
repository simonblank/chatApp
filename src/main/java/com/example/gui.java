package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by simon blank on 2016-12-15.
 */
public class gui extends Thread{




    JFrame frame = new JFrame();
    JTextArea nametextArea = new JTextArea();
    JTextArea chattextArea = new JTextArea();
    JTextArea posttextArea = new JTextArea();
    JPanel panel = new JPanel();
    String user="Anomynus" , messagetemp="";   /// namnet på usern  messagetemp se getmessagetemp


  public gui() {} //// constructor

    public void loadGui(){
        panelProperties();
        nametextarea();
        setNameButton();
        chattextarea();
        postButton();
        posttextarea();
        frameProperties();

    }   // ladda alla komponenter gui

    public void panelProperties(){

        panel.setPreferredSize(new Dimension(200,200));
        frame.getContentPane().add(panel);

    }  // olika properties för olika komponenter

    public void frameProperties(){
        frame.setSize(300,480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void setNameButton(){
        JButton button = new JButton(" set name");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user = nametextArea.getText();
                nametextArea.setEditable(false);
            }
        });
        panel.add(button);
    }

    public void postButton(){
        JButton button = new JButton("post");
        button.setPreferredSize(new Dimension(200,20));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messagetemp=posttextArea.getText();
                posttextArea.setText("");
            }
        });
        panel.add(button);

    }

    public void nametextarea(){
        nametextArea.setPreferredSize(new Dimension(100,20));
        panel.add(nametextArea);

    }

    public void chattextarea(){
        chattextArea.setPreferredSize(new Dimension(200,900));
        chattextArea.setLineWrap(true);
        chattextArea.setEditable(false);
        panel.add(chattextArea);


        JScrollPane scrollPane = new JScrollPane(chattextArea);// scroll bar till text arean
        scrollPane.setPreferredSize(new Dimension(220,250));
        panel.add(scrollPane);

    }

    public void posttextarea(){
        posttextArea.setPreferredSize(new Dimension(220,100));
        posttextArea.setLineWrap(true);
        panel.add(posttextArea);

    }

    public void addToChatArea(String postinfo){
        chattextArea.append(postinfo+"\n\n");
    }  /// lägga till text i chat arean

    public String getUser() {
        return user;
    } /// hämta namnet på usern som skriver

    public String getMessagetemp() {
        return messagetemp;
    }
    ///// det du skriver i post text rutan sparas som en string här i gui som client/server sen hämtar upp och skickar iväg

    public void deleteMessagetemp() {
        messagetemp="";
    }
    //// deleta message efter den hämtats upp


}
