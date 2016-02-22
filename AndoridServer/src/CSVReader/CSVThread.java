
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSVReader;

import andoridserver.database.DBConnector;
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

    public CSVThread() {
        super("CSVThread");
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
        System.out.println("[CSVReader]: Conversione in query dei csv");
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
                    if (lowercaseName.endsWith(".txt") && !lowercaseName.equals("shapes.txt")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };
            /*contiene la lista di file csv da caricare*/
            String[] filesName = dirNav.list(textFilter);
            navCount = filesName.length;
            //System.out.println(navCount);
            String[] busFilesName = dirAutobus.list(textFilter);
            System.out.println("[CSVReader]: FILES TO PROCESS -> "+(navCount+busFilesName.length));
            DBConnector.getIstance().connect(DBConnector.DRIVER, DBConnector.ADDRESS, DBConnector.POSTGRESPORT, "battelli");
            if(!DBConnector.getIstance().dropDB())
                throw new IllegalStateException("NON SONO RIUSCITO DROPPARE LE TABELLE");
            if(!DBConnector.getIstance().updateTable())
                throw new IllegalStateException("NON SONO RIUSCITO RICREARE LE TABELLE");
            CSVReader csvrNAV[] = new CSVReader[filesName.length];
             System.out.println("[CSVThread]: NAVIG FILES");
            for (int i = 0; i < filesName.length; i++) {
                System.out.println("[CSVThread]: FILES TO PROCESS -> "+filesName[i]);
                csvrNAV[i] = new CSVReader(NAV + filesName[i]);
                csvrNAV[i].readFile();
                sqlNav.writeQuery(filesName[i], csvrNAV[i]);
                csvrNAV[i].destroy();
                csvrNAV[i] = null;
                //this.sleep(1);
            }
            sqlNav.close();
            sqlNav.destroy();
            
            DBConnector.getIstance().connect(DBConnector.DRIVER, DBConnector.ADDRESS, DBConnector.POSTGRESPORT,"autobus");
            if(!DBConnector.getIstance().dropDB())
                throw new IllegalStateException("NON SONO RIUSCITO DROPPARE LE TABELLE");
            if(!DBConnector.getIstance().updateTable())
                throw new IllegalStateException("NON SONO RIUSCITO RICREARE LE TABELLE");
            CSVReader csvrBUS[] = new CSVReader[busFilesName.length];
             System.out.println("[CSVThread]: BUS FILES");
            for (int i = 0; i < busFilesName.length; i++) {
                System.out.println("[CSVThread]: FILES TO PROCESS -> "+filesName[i]);
                csvrBUS[i] = new CSVReader(AUTOBUS + busFilesName[i]);
                csvrBUS[i].readFile();
                sqlBus.writeQuery(busFilesName[i], csvrBUS[i]);
                csvrBUS[i].destroy();
                csvrBUS[i] = null;
                //this.sleep(1);
            }
            sqlBus.close();
            sqlBus.destroy();
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(CSVReader.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("[CSVReader]: ERRORE NELL'ESECUZIONE");
            return;
        } catch (Exception ex) {
        Logger.getLogger(CSVThread.class.getName()).log(Level.SEVERE, null, ex);
    }
        System.out.println("[CSVThread]: FINE DELL'ESECUZIONE");
    }
}