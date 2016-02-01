/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver.database;

/**
 *
 * @author Nicola
 */
public interface DatabaseTypes {
        
    String getType(int fieldPosition,String tableName);
    
    
}
