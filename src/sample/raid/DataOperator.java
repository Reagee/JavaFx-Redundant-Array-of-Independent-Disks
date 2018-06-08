package sample.raid;

public class DataOperator {
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
}
