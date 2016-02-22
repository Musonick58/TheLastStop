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
public class DatabaseUpdater {
    private static DatabaseUpdater ref=null;
    
    private DatabaseUpdater(){
    
    }
    
    final static String INSERT="INSERT INTO %n (%f) ";
    final static String VALUES="VALUES (%v);";
    
    
    public static DatabaseUpdater getIstance(){
        if(ref==null){
            ref=new DatabaseUpdater();
        }
        return ref;
    
    }
    
    public static String createTable(String[] tableNames){
        String ret=" ";
        
        String calendar=
        "CREATE TABLE calendar(\n" +
        "	service_id TEXT,\n" +
        "	monday INTEGER,\n" +
        "	tuesday INTEGER,\n" +
        "	wednesday INTEGER,\n" +
        "	thursday INTEGER,\n" +
        "	friday INTEGER,\n" +
        "	saturday INTEGER,\n" +
        "	sunday INTEGER,\n" +
        "	start_date TEXT,\n" +
        "	end_date TEXT\n" +
        ");\n";
        
        String agency=
        "CREATE TABLE agency(\n" +
        "	agency_id TEXT,\n" +
        "	agency_name TEXT,\n" +
        "	agency_url TEXT,\n" +
        "	agency_timezone TEXT,\n" +
        "	agency_lang DOUBLE PRECISION,\n" +
        "	agency_phone,\n" +
        "	agency_fare_url TEXT\n" +
        ");";
        ret+=calendar;
        ret+=agency;
        return ret;
    }
    
    public static String updateTables(String tableName,String[] fields,String[] values){
        ChooseDBType cdb = new ChooseDBType();
        
        String query = new String(INSERT);
        String queryvals = new String(VALUES);
        String valuesLine="";
        String fieldsLine="";
        for(String x : fields){
            x=checkDate(x);
            fieldsLine+=x+",";
        }
         for(String x : values){
             if(isInt(x) || isDouble(x)){
                valuesLine+=x+",";
             }else{
                 x=removeDoubleApex(x);
                 x=removeApostrof(x);
                 valuesLine+="\'"+x+"\',";     
             }
         }
        valuesLine=valuesLine.substring(0, valuesLine.length()-1);
        fieldsLine=fieldsLine.substring(0, fieldsLine.length()-1);
        
        query=query.replace("%n", tableName);
        query=query.replace("%f", fieldsLine);
        
        queryvals=queryvals.replace("%v", valuesLine);
       
        return query+queryvals;
    }
    
      public static String updateTables2(String tableName,String[] fields,String[] values){
        ChooseDBType cdb = new ChooseDBType();
        String query = new String(INSERT);
        String queryvals = new String(VALUES);
        String valuesLine="";
        String fieldsLine="";
        for(String x : fields){
            x=checkDate(x);
            fieldsLine+=x+",";
        }
         for(int i=0;i<values.length;i++){
             //System.out.println("i: "+i+" lunghezza totale"+values.length);
             if(tableName.equals("routes") && i==9) break; //patch per il vettore di navigazione routes
	     if(tableName.equals("stops") && i==12) break; //patch per il vettore bus stops
              String type=cdb.getType(i, tableName);
              String x = values[i];
              x = castType(type,x);
                 valuesLine+=x;     
             
         }
        valuesLine=valuesLine.substring(0, valuesLine.length()-1);
        fieldsLine=fieldsLine.substring(0, fieldsLine.length()-1);
        
        query=query.replace("%n", tableName);
        query=query.replace("%f", fieldsLine);
        
        queryvals=queryvals.replace("%v", valuesLine);
       
        return query+queryvals;
    }
    
    public static String castType(String type,String toConvert){
    String ret="";
    switch(type){
        case "null":
            ret="null";
        break;
        case "int":
            if(isInt(toConvert)){
                ret=toConvert+",";
            }else{
                ret=removeApostrof(toConvert)+",";
            }
        break;
        case "double":
            if(isDouble(toConvert)){
                ret=toConvert+",";
            }else{
               ret=removeApostrof(toConvert)+",";
            }
        break;
        case "text":
           String ret2="";
           ret2=removeApostrof(toConvert);
           ret="'"+ret2+"',";
           ret=removeDoubleApex(ret);
        break;
    }
    if(toConvert.equals("") || toConvert.equals(" ") || toConvert.equals("null")){
        ret="null"+",";
    }
    return ret;
    }
      
      
    private static String removeDoubleApex(String s){
        return s.replaceAll("[\"]","");
    }
    //da fare
    private static String checkDate(String s){
        if(s.equalsIgnoreCase("date")){
            return "end_date";
        } else{ 
            return s;
        }
    }
    //if is an acent replace with accent
    private static String removeApostrof(String s){
       s=s.replace("o'","ò");
       s=s.replace("a'","à");
       s=s.replace("A'","À");
       s=s.replace("O'","Ò");
       s=s.replace("D'","D ");
       s=s.replaceAll("[']+"," ");
       return s; 
    }
    
    private static boolean isInt(String n){
       int c;
       boolean ret=true;
       if(n.equals("000000") || n.equals("'000000'"))
           ret=false;
       try{
        c=Integer.parseInt(n);
       }catch(NumberFormatException nfe){   
        ret=false;
       } 
       return ret; 
    }
    
    private static boolean isDouble(String n){
       double c;
       boolean ret=true;
       if(n.equals("000000") || n.equals("'000000'"))
           ret=false;
       try{
        c=Double.parseDouble(n);
       }catch(NumberFormatException nfe){   
        ret=false;
       } 
       return ret; 
    }
    
    public static void main(String[] args){
        DatabaseUpdater cdb=new DatabaseUpdater();
        System.out.println(cdb.isDouble("100000.5s"));
        System.out.println(cdb.removeDoubleApex("\"\"\""));
        System.out.println(cdb.removeApostrof("Toma'"));
        System.out.println(cdb.createTable(null));
    }
    
}
