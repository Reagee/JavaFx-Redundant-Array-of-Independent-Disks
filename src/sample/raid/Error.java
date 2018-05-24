package sample.raid;

public class Error {

    public String damagedBit = "Uszkodzone bity na pozycjach: ";

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

    public boolean check(String output, String bits){

        boolean val = true;
        char[] arr = output.toCharArray();
        char[] bitArr = bits.toCharArray();

        damagedBit = "Uszkodzone bity na pozycjach: ";

        for(int i = 0;i < arr.length; i++){
            if(xor(arr[i*2], arr[i*2+1]) != bitArr[i]){
                val = false;
                damagedBit += (i+1)+" ";
            }
        }
        if(arr.length%2 != 0){
            if((int)arr[arr.length-1] != bitArr[bitArr.length-1]){
                val = false;
                damagedBit += arr.length-1;
            }
        }
        return val;
    }

}
