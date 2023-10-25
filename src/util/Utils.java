/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import gui.LoginController;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author USER
 */
public class Utils {
    
    
    public static void clearFile(String filePath){
        PrintWriter writer = null;
                try {
                    writer = new PrintWriter(filePath);
                    writer.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    writer.close();
                }
    }

    
    public static void saveEmailAndPassword(String email,String password) {
        
        try{
                            System.out.println("save email");

            PrintWriter out = new PrintWriter("account.txt");
            out.append(email);
            out.append("\n");
            out.append(password);
            out.flush();
            out.close();
        }catch(FileNotFoundException exception){
            System.out.println("error");
        }
    }
    public static boolean isValidEmail(String email) {
        Pattern p = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher m = p.matcher(email);
        return m.find() && m.group().equals(email);
    }
    public static String encryptString(String originalString){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
            return(bytesToHex(encodedhash));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return("");
    }
    private static String bytesToHex(byte[] hash) {
    StringBuilder hexString = new StringBuilder(2 * hash.length);
    for (int i = 0; i < hash.length; i++) {
        String hex = Integer.toHexString(0xff & hash[i]);
        if(hex.length() == 1) {
            hexString.append('0');
        }
        hexString.append(hex);
    }
    return hexString.toString();
}
}
