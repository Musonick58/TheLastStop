/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webpagereader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.select.*;
import org.jsoup.nodes.*;

/**
 *
 * @author nicola
 */
public class PageReader {

    Document doc;
    String site;
    String service;
    String fileName=null;

    /**
     *
     * @param site what url to check if nav or aut
     * @param service type of service nav or aut
     * @throws IOException
     */
    public PageReader(String site, String service) throws IOException {
        this.site = site;
        this.service = service.equals("nav") ? "actv_nav_" : "actv_aut_";
        this.doc = Jsoup.connect(site).get();
    }

    public String parse() throws IOException {
        Element el = doc.body();
        Elements links = el.getElementsByTag("a");
        String last = "";
        for (Element link : links) {
            if (link.attr("href").startsWith(service)) {
                last = link.attr("href");
            }
        }
        // System.out.println(site + "/" + last);
        return (site + "/" + last);
    }
        
    public String getArciveName(){ return this.filename; }
    
    
    /**
     * Downloads a file from a URL
     *
     * @param link HTTP URL of the file to be downloaded
     * @return true if file is saved, false instead
     * @throws IOException
     */
    public boolean download(String link) throws IOException {
        final int BUFFER_SIZE = 4096;
        URL url = new URL(link);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            this.fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    this.fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                this.fileName = link.substring(link.lastIndexOf("/") + 1,
                        link.length());
            }

            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + this.fileName);
            
            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = this.fileName;

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            inputStream.close();
            httpConn.disconnect();
          //  System.out.println("File downloaded");
            return true;
        } else {
            System.err.println("No file to download. Server replied HTTP code: " + responseCode);
            return false;
        }

    }
    
    
    public void updateFiles(){
        if(this.fileName!=null){
            
        
        
        };
    }

    public static void main(String[] args) throws IOException {
        PageReader pr = new PageReader("http://www.actv.it/opendata/navigazione", "nav");
        String link = pr.parse();
        System.out.println(pr.download(link));

    }

}
