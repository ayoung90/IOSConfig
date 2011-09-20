/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iosconfig;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 *
 * @author G73
 */
public class ConfigReader {
    /*public ConfigReader(){
        /*int lines = checkNumberOfLines(inputFile);
        String[] fileArray = parseToArray(inputFile,lines);*/
        /* PASS FILE ARRAY TO LINE CHECKER */
    /*}*/
    public static int  checkNumberOfLines(String inputFile){
        int lines = 0;
        try {
            // Open the file that is the first 
            // command line parameter
            FileInputStream fstream = new FileInputStream(inputFile);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                lines++;
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    return lines ;
    }
    public static String[] parseToArray(String inputFile, int lines){
        String[] fileArray = new String[lines];
        int i = 0;
        try {
            // Open the file that is the first 
            // command line parameter
            FileInputStream fstream = new FileInputStream(inputFile);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                fileArray[i]=strLine;
                i++;
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return fileArray;
    }
    
}
