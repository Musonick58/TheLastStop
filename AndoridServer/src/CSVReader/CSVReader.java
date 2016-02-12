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
    private int numberOfTables=0;
    private File inputCsv;
   
    
    public CSVReader(String filename){
        try {
            this.filename=filename;
            this.inputCsv = new File(filename);
            if(!inputCsv.exists()){
                throw new FileNotFoundException(filename);
            }
        } catch (IOException ex) {
            Logger.getLogger(CSVReader.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
    }
    
    public CSVReader(File csvFile){
        this.inputCsv=csvFile;
    }
    
    public void destroy(){
        tableNames=null;
        filename=null;
        inputCsv=null;
        valuesContainer.clear();
        valuesContainer=null;
        System.gc();
    }
    
    
    public String[] getTableNames(){
        return tableNames;
    }
    
    public List<List<String>> getValues(){
        return valuesContainer;
    }
    
    public int getNumberOfTables(){
        return numberOfTables;
    }
    
    public void readFile(){
        try {
            ArrayList<String> tempStrings;
            String tempLine="";
            Scanner fileReader = new Scanner(this.inputCsv);
            int wordCount=0;
            int linesNumber=0;
            while(fileReader.hasNext()){
                tempLine=fileReader.nextLine();
                linesNumber++;
                if(tempLine.endsWith(","))
                    tempLine+="null";
                tempStrings = new ArrayList<String>();
                ParseString st;
                if(inputCsv.getName().equals("stop_times.txt"))
                    st = new ParseString(tempLine,",",inputCsv.getName());
                else
                    st = new ParseString(tempLine,",");
                while(st.hasMoreTokens()){
                    String temp=st.nextToken();    
                    tempStrings.add(temp);
                    if(linesNumber==1){
                        wordCount++;
                    }
                }
                if(linesNumber==1){
                    this.tableNames=tempStrings.toArray(new String[tempStrings.size()]);
                }else if(!tempStrings.isEmpty()){
                    this.valuesContainer.add(tempStrings);//mi salvo tutte le liste di valori, con size so quante liste ho
                }
               
            }
            this.numberOfTables=wordCount;
            fileReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CSVReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
   
}
 
    public static void main(String[] args) {   
        int navCount=0;
        String barra = System.getProperty("file.separator");
        barra+=barra;
        final String NAV="actv"+barra+"navigazione"+barra;
        final String AUTOBUS="actv"+barra+"automobilistico"+barra;
        System.out.println(NAV);
        System.out.println("Conversione in query dei csv");

        try {
            
            FilePrinter sqlNav = new FilePrinter("nav.sql");
            FilePrinter sqlBus = new FilePrinter("bus.sql");
            
            File dirNav=new File(System.getProperty("user.dir")+barra+NAV);
            File dirAutobus=new File(System.getProperty("user.dir")+barra+AUTOBUS);
            //System.out.println(dirNav.getAbsolutePath());
           // System.out.println(dirAutobus.getAbsolutePath());
            FilenameFilter textFilter = new FilenameFilter() {
                        @Override
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				if (lowercaseName.endsWith(".txt")) {
					return true;
				} else {
					return false;
				}
			}
		};
                /*contiene la lista di file csv da caricare*/
                String[] filesName = dirNav.list(textFilter);
                navCount = filesName.length;
                System.out.println(navCount);
                String[] busFilesName = dirAutobus.list(textFilter);
                //filesName = CSVReader.mergeStringArray(filesName, busFilesName);
                CSVReader csvrNAV[]=new CSVReader[filesName.length];
                for(int i=0;i<filesName.length;i++){
                        csvrNAV[i]=new CSVReader(NAV+filesName[i]);
                        csvrNAV[i].readFile();
                        sqlNav.writeQuery(filesName[i], csvrNAV[i]);
                        csvrNAV[i].destroy();
                        csvrNAV[i]=null;
                }
                sqlNav.close();
                CSVReader csvrBUS[]=new CSVReader[busFilesName.length];
                for(int i=0;i<busFilesName.length;i++){                   
                    csvrBUS[i]=new CSVReader(AUTOBUS+busFilesName[i]);
                    csvrBUS[i].readFile();
                    sqlBus.writeQuery(busFilesName[i], csvrBUS[i]);
                    csvrBUS[i].destroy();
                    csvrBUS[i]=null;
                }
                sqlBus.close();
} catch (FileNotFoundException ex) {
            Logger.getLogger(CSVReader.class.getName()).log(Level.SEVERE, null, ex);
        } 
     System.out.println("fine dell'esecuzione");
    }
    
}