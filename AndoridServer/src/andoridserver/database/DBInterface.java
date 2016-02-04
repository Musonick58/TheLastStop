/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver.database;

import andoridserver.androidData.AndroidDataInterface;

/**
 *
 * @author Nicola
 */
public interface DBInterface { 
    
    
    /**connessione al database BASE
     * @param dbTipe db: mysql, postgress, oracle, microsoft
     * @param dbAdress indirizzo ip del db
     * @param dbPort porta a cui accedere al db
     * @return true se connessione effettuata, false altrimenti
     */
    boolean connect(String dbType, String dbAdress, int dbPort, String resources);

    /**
     *
     * @param dbTipe
     * @param dbAdress
     * @param dbPort
     * @param otherParams array di stringhe con altri parametri
     * @return true if it was connected, false otherwise
     */
    boolean connect(String dbTipe,String dbAdress,int dbPort, String[] otherParams);
    
    /**
     * @return nothing but close db connection
     */
    void disconnect();
      
    /**
     *
     * @param select array di stringhe con i campi da selezionare nella query
     * @param from array di stringhe contente le tabelle
     * @param where array di stringhe di clausole del where
     * @param groupBy array di stringhe del groupby
     * @param optionalParam array di stringhe opzionali
     * @return una stringa compilata per eseguire una query
     */
    String compileQuery(String[] select,String[] from,String[] where,String[] optionalParam);
    
    /**
     *
     * @param query stringa da controllare per effettuare la query
     * @return la stringa se rispetta la sintassi, null altrimenti
     */
    String compileQuery(String query);
    
    /**
     *
     * @param compiledQuery query compilata con il metodo compile query ho a mano
     * @return un oggetto popolato di tipo ADI con il result della query
     */
    AndroidDataInterface executeQuery(String compiledQuery);
    
    
    
    
        
    
    
    
    
    
    
    
}
