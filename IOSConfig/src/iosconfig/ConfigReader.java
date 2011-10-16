/*
 * Written By Dharshun Sridharan - 41775057, Adam Young - 4200943
 * 
 * This software is provided for evaluation and grading purposes only. If this
 * software is intended to be used in a commercial environment contact the
 * students involved in the assignment to attain a commercial license.
 * 
 * This software is distributed "as is" with no guarantee. Use at your own risk.
 */

package iosconfig;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Basic class to read in a file from input and provide an Array of lines in the
 * file.
 * @author Dharshun Sridharan - 41775057, Adam Young - 4200943
 */
public class ConfigReader {

    /**
     * Returns the amount of lines in a given file.
     * @param inputFile File to gather line count.
     * @return number of lines in given file. Console Error on file processing errors.
     */
    public static int checkNumberOfLines(String inputFile) {
        int lines = 0;
        try {
            // Open the file that is the first 
            // command line parameter
            FileInputStream fstream = new FileInputStream(inputFile);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            //Read File Line By Line
            while (br.readLine() != null) {
                // Print the content on the console
                lines++;
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
            System.exit(0);
        }
        return lines;
    }

    /**
     * Returns an Array of Strings which match the line numbers of the input file.
     * @param inputFile File to gather lines from.
     * @param lines count of all lines in the input file.
     * @return String Array containing each line in the given file in sequence.
     */
    public static String[] parseToArray(String inputFile, int lines) {
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
                fileArray[i] = strLine;
                i++;
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
            System.exit(0);
        }
        return fileArray;
    }
}
