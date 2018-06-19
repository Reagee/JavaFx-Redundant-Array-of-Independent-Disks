package sample.raid;

public class DataOperator {
    private static String input="";
    private static String dataOne="";
    private static String dataTwo="";
    private static String parityBits="";
    private static String dataOutput="";
    private static boolean dataFlag = false;



    public static String mergeData(String dataOne, String dataTwo) {
        char[] valuesOne = dataOne.toCharArray();
        char[] valuesTwo = dataTwo.toCharArray();

        String dataOutput = "";

        for (int i = 0; i < valuesOne.length/8; i++) {
            for(int j = 0; j < 8; j++)
                dataOutput += valuesOne[i+j];
            for(int j = 0; j < 8; j++)
                dataOutput += valuesTwo[i+j];
        }
        return dataOutput;

    }

    public static void overrideAllData(String in){
        if((in==null || in.isEmpty() || in.equals(null)) && !dataFlag){
            input = "Niepoprawne dane";
            dataFlag = false;
        }
        else {
            dataFlag = true;
            input = in;
            char[] inputArray = input.toCharArray();

            for (int i = 0; i < inputArray.length / 16; i++) {
                int j;
                char xorBit;
                for (j = 0; j < 8; j++) {
                    dataOne += (inputArray[i * 16 + j]);
                    xorBit = BitsManipulator.xor(inputArray[i * 16 + j], inputArray[i * 16 + 8 + j]);
                    parityBits += (xorBit);
                }
                for (j = 8; j < 16; j++)
                    dataTwo += (inputArray[i * 16 + j]);
            }
            dataOutput = mergeData(dataOne,dataTwo);
        }
    }






    public static void overideOutputData(String in, String dataOne, String dataTwo){
        if((in==null || in.isEmpty() || in.equals(null) || dataOne==null || dataOne.isEmpty() || dataTwo==null || dataTwo.isEmpty()) && !dataFlag){
            input = "Niepoprawne dane";
            dataFlag = false;
        }
        else {
            dataOutput = mergeData(dataOne, dataTwo);
            input = in;
            char[] inputArray = input.toCharArray();
            dataFlag = true;
            for (int i = 0; i < inputArray.length / 16; i++) {
                int j;
                char xorBit;
                for (j = 0; j < 8; j++) {
                    xorBit = BitsManipulator.xor(inputArray[i * 16 + j], inputArray[i * 16 + 8 + j]);
                    parityBits += (xorBit);
                }
            }
        }
    }

    public static void resetValues(){
        input="";
        dataOne="";
        dataTwo="";
        parityBits="";
        dataOutput="";
        dataFlag = false;
    }

    public static String getDataOne() {
        return dataOne;
    }

    public static String getDataTwo() {
        return dataTwo;
    }

    public static String getParityBits() {
        return parityBits;
    }

    public static String getDataOutput() {
        return dataOutput;
    }
    public static String getInput() {
        return input;
    }

    public static boolean isDataFlag() {
        return dataFlag;
    }
}
