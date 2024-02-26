package fr.dtn.mlauncher;

import javafx.application.Application;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Show an error window if Javafx cannot be loaded

        try{
            Class.forName("javafx.application.Application");

            // If Javafx is found, launch the application using fr.dtn.mlauncher.Launcher class
            Application.launch(Launcher.class, args);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(
                    null,
                    "Error : " + e.getMessage() + " class is missing\n(Javafx not found)",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}