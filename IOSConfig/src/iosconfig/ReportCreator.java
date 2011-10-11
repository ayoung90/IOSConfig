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
        errorList.put("A", "Unsafe Password Enabling (Password isnt hidden in Cisco Files) - Cisco recommends using 'Enable Secret' instead of 'Enable'");
        errorList.put("B", "Weak Encrypted Password (Password is at risk of being found) - Cisco recommends a password rating of '5' (Using MD5)");
        errorList.put("C", "Unrestricted Access to files (Breach of confidentiality, integrity and availability) - Should not use 'Any', and should explicitly specify allowable traffic");
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
        out.write(dateNow + "\nScanning for weak password encryption and unsafe traffic in the RFC1918 Subnet\n\n");

       
        if (List.size() == 0) {
            out.write("No vulnerabilities present - Line 0 \n"+inputFile+" is considered safe\n");
        } else {
            for (Vulnerability vuln : List) {
                out.write("(Line: "
                        + vuln.getLineNumber() + ") \t\t Violation: '"
                        + vuln.getVulnerability() + "'\n\t\t\t Analysis: "
                        + this.errorList.get(vuln.getCode()) + "");
                out.write("\n");
            }
        }
        if (List.size() != 0) {
            out.write("\n"+List.size()+" Vulnerabilities Found\n");
        }
        //Close the output stream
        out.close();
        return outputFile;
    }
}
