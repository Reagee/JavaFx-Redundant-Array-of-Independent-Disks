package sample.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Raid {

    private String damagedBits = "";

    private char xor(int a, int b){
        if(a==b)
            return '0';
        else
            return '1';
    }

    public String changeRandomBit(String input){
        String output = "";
        char[] arr = input.toCharArray();

        int val = (int)Math.random()%arr.length;
        if(arr[val] == '1')
            arr[val] = '0';
        else
            arr[val] = '1';

        for(int i = 0;i < arr.length; i++)
            output += arr[i];

        return output;
    }

    public boolean check(String output, String bits){

        boolean val = true;
        char[] arr = output.toCharArray();
        char[] bitArr = bits.toCharArray();

        damagedBits = "Uszkodzone bity na pozycjach: ";

        for(int i = 0;i < arr.length; i++){
            if(xor(arr[i*2], arr[i*2+1]) != bitArr[i]){
                val = false;
                damagedBits += (i+1)+" ";
            }
        }
        if(arr.length%2 != 0){
            if((int)arr[arr.length-1] != bitArr[bitArr.length-1]){
                val = false;
                damagedBits += arr.length-1;
            }
        }
        return val;
    }

    public String rotateArr(String input){
        String output = "";
        char[] arr = input.toCharArray();

        int val = (int)Math.random()%2;

        for(int i = 0;i < arr.length; i++){
            if(val == 1){
                output += arr[i*2];
                if(arr[i*2+val] == '0')
                    output += '1';
                else
                    output += '0';
            }
            else{
                if(arr[i*2] == '0')
                    output += '1';
                else
                    output += '0';
            }
        }
        return  output;
    }

    public void generateResult(String input, String discOne, String discTwo, String bits, String errors){

        String user = System.getProperty("user.name");
        String absPath = "C:/Users/"+user+"/Desktop/RAID";
        String filePath = absPath+"/raport.txt";

        if(!Files.exists(Paths.get(absPath))){
            try {
                Files.createDirectory(Paths.get(absPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(!Files.exists(Paths.get(filePath))){
            try {
                Files.createFile(Paths.get(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filePath));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
