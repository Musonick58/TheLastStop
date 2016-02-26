/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver.database;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ThreadAggiornaDelayOrario extends Thread {
    
    static boolean aggiornabool=false;
    static Date date = new Date();
    static Date currentDate=date;

    DBAsk aggiorna = new DBAsk();
        
    private static synchronized void setAggiornaBool(boolean value){
        aggiornabool=value;
    }
    
    public static synchronized boolean getAggiornaBool(boolean value){
        return aggiornabool;
    }
    
        @Override
       public void run(){ 
        while(true){
             try {
            if( date!=currentDate ){
               
                    this.sleep(86400000);
                    currentDate=date;
                    DBConnector temp=DBConnector.getIstance();
                    temp.executeSetDelay( aggiorna.dbSetDefaultDelay() );
                    setAggiornaBool(true);
               
            }
            else{
                this.sleep(86400000);
                setAggiornaBool(false);
            }
             } catch (InterruptedException ex) {
                    Logger.getLogger(DBAsk.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
       
    }
         
}