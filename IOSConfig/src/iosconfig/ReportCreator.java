/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iosconfig;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author G73, Adam
 */
public class ReportCreator {

    public Map errorList = new HashMap();

    public ReportCreator() {
        errorList.put("A", "Weak Password");
        errorList.put("B", "Weak Username");
        errorList.put("C", "Unsafe Protocol");
    }

    public static String timeNow() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(cal.getTime());
    }

    public static String timeNowFilePath() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("ddMMMyy_HHmmss");
        return df.format(cal.getTime());
    }

    public String create(LinkedList<Vulnerability> List, String outputFile, String inputFile) throws Exception {

        String dateNow = ReportCreator.timeNow();
        String dateNow2 = ReportCreator.timeNowFilePath();

        if (outputFile.length() == 0) {
            //Changed to output to program launch directory rather than H: Drive
            outputFile = dateNow2 + "_iosconfig.txt";
        }

        // Create file 
        FileWriter fstream = new FileWriter(outputFile);
        BufferedWriter out = new BufferedWriter(fstream);

        out.write("Cisco IOS Configuration Tool - Scan Results\n");
        out.write("" + inputFile + " - ");
        out.write(dateNow + "\n\n");

        if (List.size() == 0) {
            out.write("No vulnerabilities present - Line 0 \n");
        } else {
            for (Vulnerability vuln : List) {
                out.write("(Line: "
                        + vuln.getLineNumber() + ") \t\t Violation: '"
                        + vuln.getVulnerability() + "' \t\t "
                        + this.errorList.get(vuln.getCode()) + "");
                out.write("\n");
            }
        }
        //Close the output stream
        out.close();
        return outputFile;
    }
}
