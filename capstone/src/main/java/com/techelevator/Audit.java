package com.techelevator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Audit {

    public void auditFile(String toPrint) {
        boolean newFile = false;
        try (PrintWriter pw = new PrintWriter(new FileOutputStream("audit.txt", newFile))){
            newFile = true;
            pw.println(toPrint);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
