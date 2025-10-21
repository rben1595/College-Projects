package com.example.fypblackjacksecurity.security;


import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class logging {

    public Boolean CheckIfFileCreated(){

        // Create a File object for the folder
        File folder = new File("/Users/benryan/Desktop/FYP-Project/Logs");
        // Get the list of files in the folder
        File[] files = folder.listFiles();

        int latestFile = files.length - 1;

        File myObj = new File("/Users/benryan/Desktop/FYP-Project/Logs/FYP-Blackjack-Security-Log-" + latestFile + ".txt");
        if (myObj.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public String CreateFile(HttpSession session){

        String path = "";

        try {
            //add number to file to tell log files apart
            int exists;
            int fileNum = 0;

            do {
                path = "src/main/resources/static/logs/FYP-Blackjack-Security-Log-" + fileNum + ".txt";
                File myObj = new File(path);
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                    String text = "File created: " + myObj.getName();
                    List<String> fileText = new ArrayList<>();
                    fileText.add(text);
                    WriteToFile(fileText, path);
                    session.setAttribute("fileCreated", "created");
                    session.setAttribute("newLogFileName", myObj.getName());
                    exists = 0;
                } else {
                    System.out.println("File already exists.");
                    exists = 1;
                    fileNum++;
                }
            }while (exists == 1);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return path;
    }

    public List<File> getAllLogFiles(){
        // Create a File object for the folder
        File folder = new File("src/main/resources/static/logs");
        // Get & return the list of files in the folder
        return List.of(folder.listFiles());
    }

    public List<String> readLogFile(String fileName){

        //create list to capture all log file lines
        List<String> logFileData = new ArrayList<>();

        try {
            File myObj = new File("src/main/resources/static/logs/" + fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                logFileData.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return logFileData;
    }

    public void WriteToFile(List<String> text, String path) throws IOException {
        FileWriter myWriter = new FileWriter(path);
        for (String line : text){
            myWriter.write(line);
            myWriter.append('\n');
        }
        myWriter.close();
    }
}
