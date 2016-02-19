/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webpagereader;

import CSVReader.CSVThread;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicola
 */
public class Checker extends Thread {

    protected String oldStringNav;
    protected String oldStringBus;
    public boolean changed=false;
    public Checker(String name,String oldStringNav,String oldStringBus) throws IOException {
        super(name);

        this.oldStringNav = oldStringNav;//new PageReader("http://www.actv.it/opendata/navigazione", "nav").parse();
        this.oldStringBus = oldStringBus;//new PageReader("http://www.actv.it/opendata/automobilistico", "bus").parse();
    }
    
 
    
    @Override
    public void run() {
        try {
            while (true) {
                PageReader navpr = new PageReader("http://www.actv.it/opendata/navigazione", "nav");
                PageReader buspr = new PageReader("http://www.actv.it/opendata/automobilistico", "bus");

                if (!oldStringNav.equals(navpr.parse())) {
                    navpr.download(navpr.parse()); //cerco l'ultimo zip disponibile e lo scarico
                    navpr.updateFiles(); //chiamo il metodo che estrare lo zip aggiornato nelle cartelle
                    //Dobbiamo far partire il csv reader e da li poi aggiornare il database
                    System.out.println("["+this.getName()+"]: Update dei CSV"+navpr.parse());
                    //Thread t = new CSVThread("nav"); //chiamo il CSVReader per far aggiornare il file di query
                    //t.start();//avvio il thread
                    //while(t.isAlive()){ this.sleep(180000);}//busy waiting
                    //salvo il nome dell'ultimo archivo in modo da poterlo confrontare se cambia 
                    oldStringNav=navpr.parse();
                    changed=true;
                }else{              
                    System.out.println("["+this.getName()+"]: "+"nav not changed");
                }

                if (!oldStringBus.equals(buspr.parse())) {
                    buspr.download(buspr.parse());//cerco l'ultimo zip disponibile e lo scarico
                    buspr.updateFiles();//come sopra
                    //Dobbiamo far partire il csv reader e da li poi aggiornare il database
                    System.out.println("["+this.getName()+"]: Update dei CSV"+buspr.parse());
                    //Thread t = new CSVThread("bus"); //chiamo il CSVReader per far aggiornare il file di query
                    //t.start();//avvio il thread
                    //while(t.isAlive()){ this.sleep(180000);}//busy waiting
                    //salvo il nome dell'ultimo archivo in modo da poterlo confrontare se cambia 
                    oldStringBus=buspr.parse();
                    changed=true;
                }else{
                    System.out.println("["+this.getName()+"]: "+"bus not changed");
                }
                navpr = null;
                buspr = null;
                if(changed==true){
                    changed=false;
                    Thread t = new CSVThread(); //chiamo il CSVReader per far aggiornare il file di query
                    t.start();//avvio il thread
                }
                this.sleep(86400000);//un giorno di attesa prima di cercare aggiornamenti e scaricarli!
            }
        } catch (InterruptedException | IOException ex ) {
            Logger.getLogger(Checker.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("exited");
    }
    
    
    public static void main(String[] args){
        
    
    
    
    }
}
