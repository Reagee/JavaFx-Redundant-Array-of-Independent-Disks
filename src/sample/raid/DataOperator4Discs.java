package sample.raid;

public class DataOperator4Discs {
    private static String input="";
    private static String dataOne="";
    private static String dataTwo="";
    private static String dataThree="";
    private static String parityBits="";
    private static String dataOutput="";
    private static boolean dataFlag = false;

    public static String mergeDataWithAdditionalDisc(String dataOne, String dataTwo, String dataThree) {
        char[] valuesOne = dataOne.toCharArray();
        char[] valuesTwo = dataTwo.toCharArray();
        char[] valuesThree = dataThree.toCharArray();

        String dataOutput = "";

        for (int i = 0; i < valuesOne.length/8; i++) {
            for(int j = 0; j < 8; j++)
                dataOutput += valuesOne[i+j];
            for(int j = 0; j < 8; j++)
                dataOutput += valuesTwo[i+j];
            for(int j = 0; j < 8; j++)
                dataOutput += valuesThree[i+j];
        }
        return dataOutput;

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

            for (int i = 0; i < inputArray.length / 24; i++) {
                int j;
                char xorBit;
                for (j = 0; j < 8; j++) {
                    dataOne += (inputArray[i * 24 + j]);
                    xorBit = BitsManipulator.xor(inputArray[i * 24 + j], inputArray[i * 24 + 8 + j], inputArray[i * 24 + 16 + j]);
                    parityBits += (xorBit);
                }
                for (j = 8; j < 16; j++)
                    dataTwo += (inputArray[i * 24 + j]);
                for (j = 16; j < 24; j++)
                    dataThree += (inputArray[i * 24 + j]);
            }
            dataOutput = mergeDataWithAdditionalDisc(dataOne,dataTwo,dataThree);

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
            char[] inputArray = input.toCharArray();
            dataFlag = true;
            for (int i = 0; i < inputArray.length / 24; i++) {
                int j;
                char xorBit;
                for (j = 0; j < 8; j++) {
                    xorBit = BitsManipulator.xor(inputArray[i * 24 + j], inputArray[i * 24 + 8 + j], inputArray[i * 24 + 16 + j]);
                    parityBits += (xorBit);
                }
            }
        }
    }

    public static String getInput() {
        return input;
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

    public static boolean isDataFlag() {
        return dataFlag;
    }

    public static String getDataThree() {
        return dataThree;
    }
}
