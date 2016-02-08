/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webpagereader;

/**
 *
 * @author nichi
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzip
{
    private List<String> fileList;
    
    private String INPUT_ZIP_FILE;
    private String OUTPUT_FOLDER;

    public Unzip(String archiveName, String outputDir){
        INPUT_ZIP_FILE = archiveName;
        OUTPUT_FOLDER = outputDir;
    }
    /**
     * Unzip it
     * @param zipFile input zip file
     * @param output zip file output folder
     */
    public void unZipIt(){

     byte[] buffer = new byte[1024];
    	
     try{
    		
    	//create output directory is not exists
    	File folder = new File(OUTPUT_FOLDER);
    	if(!folder.exists()){
    		folder.mkdir();
    	}
    		
    	//get the zip file content
    	ZipInputStream zis = new ZipInputStream(new FileInputStream(INPUT_ZIP_FILE));
    	//get the zipped file list entry
    	ZipEntry ze = zis.getNextEntry();	
    	while(ze!=null){			
    	   String fileName = ze.getName();
           File newFile = new File(OUTPUT_FOLDER + File.separator + fileName);  
           System.out.println("file unzip : "+ newFile.getAbsoluteFile());
            //create all non exists folders
            //else you will hit FileNotFoundException for compressed folder
            new File(newFile.getParent()).mkdirs();
            FileOutputStream fos = new FileOutputStream(newFile);             
            int len;
            while ((len = zis.read(buffer)) > 0) {
       		fos.write(buffer, 0, len);
            }	
            fos.close();   
            ze = zis.getNextEntry();
    	}
        zis.closeEntry();
    	zis.close();	
    	System.out.println("Done"); 		
    }catch(IOException ex){
       ex.printStackTrace(); 
    }
   }    
    
  /*  public static void main( String[] args ){
    	Unzip unZip = new Unzip();
    	unZip.unZipIt();
    }*/
    
    
    
}