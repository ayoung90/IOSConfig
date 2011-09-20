/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package isoconfig;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author G73
 */
public class ReportCreator {
    
 
    public void ReportCreator(){

    }
    
    public static void create(String[][] errorArray, String outputFile, String inputFile){
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter=  new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
        String dateNow = formatter.format(currentDate.getTime());
        SimpleDateFormat formatter2=  new SimpleDateFormat("ddMMMyy_HHmmss");
        String dateNow2 = formatter2.format(currentDate.getTime());
        Map errorList = new HashMap();
        if (outputFile.length()==0){
            outputFile="H:\\ISOConfig\\"+dateNow2+"_iosconfig.txt";
        }
        errorList.put("A","Weak Password");
        errorList.put("B","Weak Username");
        errorList.put("C","Unsafe Protocol");
        try{
          // Create file 
          FileWriter fstream = new FileWriter(outputFile);
          BufferedWriter out = new BufferedWriter(fstream);
          out.write("Cisco IOS Configuration Tool - Scan Results\n");
          out.write(""+inputFile+" - ");
          out.write(dateNow+"\n\n");
          if(errorArray.length==0) {
            out.write("No vulnerabilities present - Line 0 \n");
          } else {
              for(int i = 0;i<errorArray.length;i++) {
                out.write("Violation:'"+errorArray[i][0]+"' \t\t "+errorList.get(errorArray[i][1])+"\t\t (Line "+errorArray[i][2]+")");
                out.write("\n");
              }
          }
          //Close the output stream
          out.close();
          }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
          }
        }        
    }
