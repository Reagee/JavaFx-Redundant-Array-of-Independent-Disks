package sample.raid;

public class DataOperator {
    private static String input="";
    private static String dataOne="";
    private static String dataTwo="";
    private static String dataThree="";
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

    public static String mergeDataWithAdditionalDisc(String dataOne, String dataTwo, String dataThree) {
        char[] valuesOne = dataOne.toCharArray();
        char[] valuesTwo = dataTwo.toCharArray();
        char[] valuesThree = dataThree.toCharArray();

        String dataOutput = "";

        for (int i = 0; i < valuesOne.length/valuesOne.length/3; i++) {
            for(int j = 0; j < valuesOne.length/3; j++)
                dataOutput += valuesOne[i+j];
            for(int j = 0; j < valuesOne.length/3; j++)
                dataOutput += valuesTwo[i+j];
            for(int j = 0; j < valuesOne.length/3; j++)
                dataOutput += valuesThree[i+j];
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
            int length = input.length();
            char[] inputArray = input.toCharArray();

            for (int i = 0; i < inputArray.length / length; i++) {
                int j;
                char xorBit;
                for (j = 0; j < length/2; j++) {
                    dataOne += (inputArray[i * length + j]);
                    xorBit = BitsManipulator.xor(inputArray[i * length + j], inputArray[i * length + length/2 + j]);
                    parityBits += (xorBit);
                }
                for (j = length/2; j < length; j++)
                    dataTwo += (inputArray[i * length + j]);
            }
            dataOutput = mergeDataWithAdditionalDisc(dataOne,dataTwo,dataThree);
        }
    }

    public static String getDataThree() {
        return dataThree;
    }

    public static void overrideAllDataWithAdditionalDisc(String in){
        if((in==null || in.isEmpty() || in.equals(null)) && !dataFlag){
            input = "Niepoprawne dane";
            dataFlag = false;
        }
        else {
            dataFlag = true;
            input = in;
            int length = input.length();
            char[] inputArray = input.toCharArray();

            for (int i = 0; i < inputArray.length / length; i++) {
                int j;
                char xorBit;
                for (j = 0; j < length/3; j++) {
                    dataOne += (inputArray[i * length + j]);
                    xorBit = BitsManipulator.xor(inputArray[i * length + j], inputArray[i * length + length/2 + j]);
                    parityBits += (xorBit);
                }
                for (j = length/3; j < length*2/3; j++)
                    dataTwo += (inputArray[i * length + j]);
                for (j = length*2/3; j < length; j++)
                    dataThree += (inputArray[i * length + j]);
            }
            dataOutput = mergeDataWithAdditionalDisc(dataOne,dataTwo,dataThree);

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
            int length = input.length();
            char[] inputArray = input.toCharArray();
            dataFlag = true;
            for (int i = 0; i < inputArray.length / length; i++) {
                int j;
                char xorBit;
                for (j = 0; j < length/2; j++) {
                    xorBit = BitsManipulator.xor(inputArray[i * length + j], inputArray[i * length + length/2 + j]);
                    parityBits += (xorBit);
                }
            }
        }
    }

    public static void overideOutputDataWithAdditionalDisc(String in, String dataOne, String dataThree, String dataTwo){
        if((in==null || in.isEmpty() || in.equals(null) || dataOne==null || dataOne.isEmpty() || dataTwo==null || dataTwo.isEmpty() || dataThree==null || dataThree.isEmpty()) && !dataFlag){
            input = "Niepoprawne dane";
            dataFlag = false;
        }
        else {
            dataOutput = mergeDataWithAdditionalDisc(dataOne,dataTwo,dataThree);
            input = in;
            int length = input.length();
            char[] inputArray = input.toCharArray();
            dataFlag = true;
            for (int i = 0; i < inputArray.length / length; i++) {
                int j;
                char xorBit;
                for (j = 0; j < length/3; j++) {
                    xorBit = BitsManipulator.xor(inputArray[i * length + j], inputArray[i * length + length/2 + j]);
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
