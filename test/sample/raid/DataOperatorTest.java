package sample.raid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataOperatorTest {
    @Test
    void mergeData() {

        //Given
        DataOperator dataOperator = new DataOperator();
        String dataOne="11111111";
        String dataTwo="00000000";
        String expectedMegedData="1111111100000000";

        //When
        String mergedData = dataOperator.mergeData(dataOne, dataTwo);

        //Then
        Assertions.assertEquals(expectedMegedData, mergedData);
    }

}