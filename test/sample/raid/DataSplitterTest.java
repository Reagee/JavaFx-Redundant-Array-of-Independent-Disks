package sample.raid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class DataSplitterTest {
    @Test
    void splitInto3Discs() {
        //GIVEN
        DataSplitter dataSplitter = new DataSplitter();
        String input = "100000001100000011100000111100001111100011111100";

        //WHEN
        dataSplitter.splitInto3Discs(input);

        //THEN
        //Assertions.assertArrayEquals();
    }

    @Test
    void splitInto4Discs() {
    }

}