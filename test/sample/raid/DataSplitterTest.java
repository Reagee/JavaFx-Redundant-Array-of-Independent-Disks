package sample.raid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataSplitterTest {
    @Test
    void splitInto3Discs() {
        //GIVEN
        DataSplitter dataSplitter = new DataSplitter();
        String input = "100000001100000011100000111100001111100011111100";

        //WHEN
        Raid3With3Discs raid3With3Discs = dataSplitter.splitInto3Discs(input);

        //THEN
        Assertions.assertEquals("100000001110000011111000",raid3With3Discs.getDisc1());
        Assertions.assertEquals("110000001111000011111100",raid3With3Discs.getDisc2());
        Assertions.assertEquals("010000000001000000000100",raid3With3Discs.getDiscParity());
    }

    @Test
    void splitInto4Discs() {

        //GIVEN
        DataSplitter dataSplitter = new DataSplitter();
        String input = "100000001100000011100000111100001111100011111100";

        //WHEN
        Raid3With4Discs raid3With4Discs = dataSplitter.splitInto4Discs(input);

        //THEN
        Assertions.assertEquals("1000000011110000",raid3With4Discs.getDisc1());
        Assertions.assertEquals("1100000011111000",raid3With4Discs.getDisc2());
        Assertions.assertEquals("1110000011111100",raid3With4Discs.getDisc3());
        Assertions.assertEquals("1010000011110100",raid3With4Discs.getDiscParity());
    }

}