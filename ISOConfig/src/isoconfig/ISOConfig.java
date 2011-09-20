/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package isoconfig;

/**
 *
 * @author G73
 */
public class ISOConfig {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if((args.length<1)||(args.length>2)) {
            System.out.println("Incorrect Arguments: <InputFile> Optional:<OutputFile>");
            System.exit(0);
        }
        String outputFile;
        if(args.length==1){
            outputFile="";
        } else {
            outputFile = args[1]; 
        }
        String inputFile = args[0];
        
        /* Passing to ConfigReader */
        int lines = ConfigReader.checkNumberOfLines(inputFile);
        String[] fileArray = ConfigReader.parseToArray(inputFile,lines);
        /* Passing to LineChecker */
        
        /* TEMPORARY */
        /* Array contents: Violation, ViolationType, Line*/
        String[][] errorArray = new String[3][3];
        errorArray[0][0]="Password: Password";
        errorArray[0][1]="A";
        errorArray[0][2]="2";
        errorArray[1][0]="Username: Username";
        errorArray[1][1]="B";
        errorArray[1][2]="45";
        errorArray[2][0]="Protocol: Any";
        errorArray[2][1]="C";
        errorArray[2][2]="47";
        
        /*Passing to ReportCreator */
        ReportCreator.create(errorArray, outputFile, inputFile);
    }
}