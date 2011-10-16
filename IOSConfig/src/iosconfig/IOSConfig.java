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

import java.util.LinkedList;

/**
 * <p>Contains main method to run application from console.</p>
 * <b>
 * <p>
 * This software is provided for evaluation and grading purposes only. If this
 * software is intended to be used in a commercial environment contact the
 * students involved in the assignment to attain a commercial license.
 * </p>
 * <p>
 * This software is distributed "as is" with no guarantee. Use at your own risk.
 * </p>
 * </b>
 * @author Dharshun Sridharan - 41775057, Adam Young - 4200943
 */
public class IOSConfig {

    public LinkedList<Vulnerability> VulnList = new LinkedList<Vulnerability>();

    /**
     * Calls methods to generate report from input file.
     * @param outputFile File to store Report data.
     * @param inputFile File to read config data.
     */
    public void generateReport(String outputFile, String inputFile) {
        ReportCreator report = new ReportCreator();
        String result;
        try {
            result = report.create(this.VulnList, outputFile, inputFile);
            System.out.println("File: Scan results written to " + result);
        } catch (Exception e) {
            // Could not write to the file. Write to default file path.
            System.out.println("Error: Could not write to output File");
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Main method to run application from console.
     * @param args Arguments: <InputFile> Optional :<OutputFile>"
     */
    public static void main(String[] args) {

        IOSConfig ios = new IOSConfig();

        if ((args.length < 1) || (args.length > 2)) {
            System.out.println("Incorrect Arguments: <InputFile> Optional :<OutputFile>");
            System.exit(0);
        }
        String outputFile;
        if (args.length == 1) {
            outputFile = "";
        } else {
            outputFile = args[1];
        }
        String inputFile = args[0];

        /* Passing to ConfigReader */
        int lines = ConfigReader.checkNumberOfLines(inputFile);
        String[] fileArray = ConfigReader.parseToArray(inputFile, lines);
        /* Passing to LineChecker */

        ios.VulnList = LineChecker.checkLines(fileArray);
        ios.generateReport(outputFile, inputFile);

    }
}