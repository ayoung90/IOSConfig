/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iosconfig;

import java.util.LinkedList;

/**
 *
 * @author G73
 */
public class IOSConfig {

    public LinkedList<Vulnerability> VulnList = new LinkedList<Vulnerability>();
    /**
     * Default filepath constant
     */
    public void generateReport(String outputFile, String inputFile) {
        ReportCreator report = new ReportCreator();
        String result;
        try {
            result = report.create(this.VulnList, outputFile, inputFile);
            System.out.println("File: Scan results written to "+ result);
        } catch (Exception e) {
            // Could not write to the file. Write to default file path.
            System.out.println("Error: Could not write to output File");
            System.out.println(e.getMessage());
            
        }
    }

    /**
     * @param args the command line arguments
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

        /* TEMPORARY */
        /* Array contents: Violation, ViolationType, Line*/

        ios.VulnList.add(new Vulnerability(2, "Password: Password", "A"));
        ios.VulnList.add(new Vulnerability(45, "Username: Username", "B"));
        ios.VulnList.add(new Vulnerability(47, "Password: Password", "C"));
        ios.VulnList.add(new Vulnerability(2, "Protocol: Any", "A"));

        /*Passing to ReportCreator */

        ios.generateReport(outputFile, inputFile);

        //ReportCreator.create(VulnList, outputFile, inputFile);
    }
}