/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyp.pasteide;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 *
 * @author cypronmaya
 */
public class pastbinService {
    
    final String pastebinURL="http://pastebin.com/api/api_post.php";
    public String getRawPaste(String pasteKey) throws MalformedURLException, IOException
    {
        String RawDataOutputURL = "http://pastebin.com/raw.php?i="+pasteKey;
        System.out.println("Connecting ->  "+RawDataOutputURL);
        URL url = new URL(RawDataOutputURL);
        URLConnection conn = url.openConnection();
        Scanner sc=new Scanner(conn.getInputStream());
        StringBuilder sb=new StringBuilder();
                while(sc.hasNextLine())
                {
                    sb.append(sc.nextLine()).append("\n");
                }
        return sb.toString();
    }
    
    public String createNewPaste(String api_dev_key,String api_option,String api_paste_code) throws UnsupportedEncodingException, IOException
    {
        String data = URLEncoder.encode("api_dev_key", "UTF-8") + "=" + URLEncoder.encode(api_dev_key, "UTF-8");
                data += "&" + URLEncoder.encode("api_option", "UTF-8") + "=" + URLEncoder.encode(api_option, "UTF-8");
                data += "&" + URLEncoder.encode("api_paste_code", "UTF-8") + "=" + URLEncoder.encode(api_paste_code, "UTF-8");
               
        URL url = new URL(pastebinURL);
        URLConnection conn = url.openConnection();
        
        conn.setDoOutput(true);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();
        
        Scanner sc=new Scanner(conn.getInputStream());
        StringBuilder sb=new StringBuilder();
                while(sc.hasNextLine())
                {
                    sb.append(sc.nextLine()).append("\n");
                }
        wr.close();
        return sb.toString();
    }
   
}
