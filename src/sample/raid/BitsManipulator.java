package sample.raid;

import java.util.Random;

public class BitsManipulator {

    public String damagedBit = "Uszkodzone bity na pozycjach: ";
    private Random gen = new Random();

    private char xor(int a, int b) {
        if (a == b)
            return '0';
        else
            return '1';
    }

    public String changeRandomBit(String input) {
        String output = "";
        char[] arr = input.toCharArray();
        System.out.println(input);
        int val = gen.nextInt(arr.length);
        if (arr[val] == '1')
            arr[val] = '0';
        else
            arr[val] = '1';

        for (int i = 0; i < arr.length; i++)
            output += arr[i];
        return output;
    }

    public String rotateArr(String input) {
        String output = "";
        char[] arr = input.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '1')
                output += '0';
            else if (arr[i] == '0')
                output += '1';
        }
        return output;
    }

    public boolean check(String output, String bits) {

        boolean flag = false;
        boolean val = true;
        char[] arr = output.toCharArray();
        char[] bitArr = bits.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (xor(arr[i * 2], arr[i * 2 + 1]) != bitArr[i]) {
                val = false;
                damagedBit += (i + 1) + " ";
                flag = true;
            }
        }
        if (arr.length % 2 != 0) {
            if ((int) arr[arr.length - 1] != bitArr[bitArr.length - 1]) {
                val = false;
                damagedBit += arr.length - 1;
                flag = true;
            }
        }
        if (!flag)
            damagedBit = "Brak uszkodzonych bitów";
        return val;
    }

}
