/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSVReader;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicola
 */
public class CSVReader {

    private String[] tableNames;
    private List<List<String>> valuesContainer = new ArrayList<>();
    private String filename;
    private int numberOfTables = 0;
    private File inputCsv;

    public CSVReader(String filename) {
        try {
            this.filename = filename;
            this.inputCsv = new File(filename);
            if (!inputCsv.exists()) {
                throw new FileNotFoundException(filename);
            }
        } catch (IOException ex) {
            Logger.getLogger(CSVReader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public CSVReader(File csvFile) {
        this.inputCsv = csvFile;
    }

    public void destroy() {
        tableNames = null;
        filename = null;
        inputCsv = null;
        valuesContainer.clear();
        valuesContainer = null;
        System.gc();
    }

    public String[] getTableNames() {
        return tableNames;
    }

    public List<List<String>> getValues() {
        return valuesContainer;
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public void readFile() {
        try {
            ArrayList<String> tempStrings;
            String tempLine = "";
            Scanner fileReader = new Scanner(this.inputCsv);
            int wordCount = 0;
            int linesNumber = 0;
            String temp;
            while (fileReader.hasNext()) {
                tempLine = fileReader.nextLine();
                linesNumber++;
                if (tempLine.endsWith(",")) {
                    tempLine += "null";
                }
                tempStrings = new ArrayList<>();
                ParseString st;
                if (inputCsv.getName().equals("stop_times.txt")) {
                    st = new ParseString(tempLine, ",", inputCsv.getName());
                } else {
                    st = new ParseString(tempLine, ",");
                }
                while (st.hasMoreTokens()) {
                    temp = st.nextToken();
                    tempStrings.add(temp);
                    if (linesNumber == 1) {
                        wordCount++;
                    }
                }
                 st=null;
                 temp=null;
                if (linesNumber == 1) {
                    this.tableNames = tempStrings.toArray(new String[tempStrings.size()]);
                } else if (!tempStrings.isEmpty()) {
                    this.valuesContainer.add(tempStrings);//mi salvo tutte le liste di valori, con size so quante liste ho
                }
                 tempStrings=null;   
            }
            this.numberOfTables = wordCount;
            fileReader.close();
            fileReader = null;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CSVReader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
