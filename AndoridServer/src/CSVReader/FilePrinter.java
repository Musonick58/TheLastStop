/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSVReader;

import andoridserver.database.DatabaseUpdater;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Nicola
 */
public class FilePrinter {
    private String fileName;
    private File file;
    private PrintWriter stampante;
    
    
    public FilePrinter(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.file=new File(fileName);
        this.stampante=new PrintWriter(this.file);
    }
    
    public void destroy(){
        flush();
        close();
        fileName=null;
        file=null;
        stampante=null;
        System.gc();
    }
    
    public void writeQuery(String filesName,CSVReader csvr){
        for(int i=0;i<csvr.getValues().size();i++){
            List<String> stringList = csvr.getValues().get(i);
            String[] s = stringList.toArray(new String[stringList.size()]);
            //DatabaseUpdater dbupdate = DatabaseUpdater.getIstance();
            String toWrite;
            toWrite=(DatabaseUpdater.updateTables2(filesName.substring(0, filesName.length()-4),csvr.getTableNames(), s))+"\n";            
            stampante.write(toWrite); 
        }
        System.out.println("[CSVReader]: END "+filesName);
        
    }
    
    public void flush(){
        this.stampante.flush();
    }
    
    public void write(String s){
        this.stampante.write(s);
    }
    
    
    public void close(){
        this.stampante.flush();
        this.stampante.close();
    }

    
}
