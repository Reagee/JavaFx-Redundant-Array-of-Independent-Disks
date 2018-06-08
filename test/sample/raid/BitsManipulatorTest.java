package sample.raid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BitsManipulatorTest {

    @Test
    void shouldRotateArray() {
        // GIVEN
        BitsManipulator bitsManipulator = new BitsManipulator();
        String input = "1010";
        String expectedOutput = "0101";

        // WHEN
        String output = bitsManipulator.rotateArr(input);

        // THEN
        Assertions.assertEquals(expectedOutput, output);
    }


}