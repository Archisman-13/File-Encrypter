/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package filemanipulation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class FileManipulation {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
        Scanner ob = new Scanner(System.in);
        
        String file_path = "";
        System.out.println("Enter the File Name:");
        String file = ob.next();
        System.out.println("Enter the Path of the File:");
        String path = ob.next();
        path = path.trim();
        char ch;
        
               
        for(int i=0;i<path.length();i++){
            ch = path.charAt(i);
            file_path = file_path + ch;
            if(ch=='\\')
                file_path = file_path + '\\';
        }
        
        file_path = file_path + "\\\\" + file;
        
        System.out.println("Enter a 16 byte Key:");
        var key = ob.next();
        
        
        System.out.println("Press 1 Encrypt!");
        System.out.println("Press 2 Decrypt!");
        System.out.println("Press 0 to End!");
        
        int choice = ob.nextInt();
        switch(choice){
            case 1:
                encryptedFile(key, file_path, file_path);
                break;
            case 2:
                decryptedFile(key, file_path, file_path);
                break;
            default:
                System.out.println("INVALID CHOICE!");
        }
           
        System.out.println("File input: " + file_path);                             // "D:\\file_data\\text.txt");//        D:\file_data    DEMO_KEY = Cyb0rG_LegIoN@13
        
        //encryptedFile(key, file_path, file_path);                                   //  text.enc                            "D:\\file_data\\text.txt"
        
        
    }

    public static void encryptedFile(String secretKey, String fileInputPath, String fileOutPath) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        var key = new SecretKeySpec(secretKey.getBytes(), "AES");
        var cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        var fileInput = new File(fileInputPath);
        var inputStream = new FileInputStream(fileInput);
        var inputBytes = new byte[(int) fileInput.length()];
        inputStream.read(inputBytes);

        var outputBytes = cipher.doFinal(inputBytes);

        var fileEncryptOut = new File(fileOutPath);
        var outputStream = new FileOutputStream(fileEncryptOut);
        outputStream.write(outputBytes);

        inputStream.close();
        outputStream.close();

        System.out.println("File successfully encrypted!");
        System.out.println("New File: " + fileOutPath);
    }

 public static void decryptedFile(String secretKey, String fileInputPath, String fileOutPath)throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        var key = new SecretKeySpec(secretKey.getBytes(), "AES");
        var cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        var fileInput = new File(fileInputPath);
        var inputStream = new FileInputStream(fileInput);
        var inputBytes = new byte[(int) fileInput.length()];
        inputStream.read(inputBytes);

        byte[] outputBytes = cipher.doFinal(inputBytes);

        var fileEncryptOut = new File(fileOutPath);
        var outputStream = new FileOutputStream(fileEncryptOut);
        outputStream.write(outputBytes);

        inputStream.close();
        outputStream.close();

        System.out.println("File successfully decrypted!");
        System.out.println("New File: " + fileOutPath);
    }
}