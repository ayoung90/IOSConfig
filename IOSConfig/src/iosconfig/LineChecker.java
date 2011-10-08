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
public class LineChecker {

    public static LinkedList<Vulnerability> VulnList = new LinkedList<Vulnerability>();

    public static LinkedList<Vulnerability> checkLines(String[] fileArray) {
        IOSConfig ios = new IOSConfig();
        for (int i = 0; i < fileArray.length; i++) {
            if (!fileArray[i].contains("!")) {
                /* Password Checker */
                if (fileArray[i].startsWith("enable")) {
                    if (!fileArray[i].contains("secret")) {
                        VulnList.add(new Vulnerability(i + 1, fileArray[i], "A"));
                    }
                    if (fileArray[i].contains(" 7 ") || fileArray[i].contains(" 0 ")) {
                        VulnList.add(new Vulnerability(i + 1, fileArray[i], "B"));
                    }
                }
                if (fileArray[i].contains("password")) {
                    if (fileArray[i].contains(" 7 ") || fileArray[i].contains(" 0 ")) {
                        VulnList.add(new Vulnerability(i + 1, fileArray[i], "B"));
                    }
                }
                if (fileArray[i].startsWith("access-list")) {
                    if ((fileArray[i].contains(" 10.")) || (fileArray[i].contains(" 192.168")) || (fileArray[i].contains(" 172."))) {
                        if (fileArray[i].contains("any")) {
                            if (!fileArray[i].contains("deny")) {
                                VulnList.add(new Vulnerability(i + 1, fileArray[i], "C"));
                            }
                        }
                    }
                }
                if (!fileArray[i].contains("deny")) {
                    if (fileArray[i].contains("any any")) {
                        VulnList.add(new Vulnerability(i + 1, fileArray[i], "C"));
                    }
                }
            }
        }
        /* ANALYSE */

        return VulnList;
    }
}
