
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicola
 */
public class CSVThread extends Thread {
    
FilePrinter sqlNav;
FilePrinter sqlBus;
Integer result;

    public CSVThread() {
        super("CSV Thread");
        this.result=result;
    try {
        sqlNav = new FilePrinter("nav.sql");
        sqlBus = new FilePrinter("bus.sql");
    } catch (FileNotFoundException ex) {
        Logger.getLogger(CSVThread.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    @Override
    public void run() {
        int navCount = 0;
        String barra = System.getProperty("file.separator");
        barra += barra;
        final String NAV = "actv" + barra + "navigazione" + barra;
        final String AUTOBUS = "actv" + barra + "automobilistico" + barra;
        System.out.println("Conversione in query dei csv");
        try {
            
            sqlNav = new FilePrinter("nav.sql");
            sqlBus = new FilePrinter("bus.sql");

            File dirNav = new File(System.getProperty("user.dir") + barra + NAV);
            File dirAutobus = new File(System.getProperty("user.dir") + barra + AUTOBUS);
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
            CSVReader csvrNAV[] = new CSVReader[filesName.length];
            for (int i = 0; i < filesName.length; i++) {
                csvrNAV[i] = new CSVReader(NAV + filesName[i]);
                csvrNAV[i].readFile();
                sqlNav.writeQuery(filesName[i], csvrNAV[i]);
                csvrNAV[i].destroy();
                csvrNAV[i] = null;
            }
            sqlNav.close();
            CSVReader csvrBUS[] = new CSVReader[busFilesName.length];
            for (int i = 0; i < busFilesName.length; i++) {
                csvrBUS[i] = new CSVReader(AUTOBUS + busFilesName[i]);
                csvrBUS[i].readFile();
                sqlBus.writeQuery(busFilesName[i], csvrBUS[i]);
                csvrBUS[i].destroy();
                csvrBUS[i] = null;
            }
            sqlBus.close();
        } catch (FileNotFoundException ex) {
            this.result=-1;
            //Logger.getLogger(CSVReader.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore nell'esecuzione");
            return;
        }
        System.out.println("fine dell'esecuzione");
    }
}