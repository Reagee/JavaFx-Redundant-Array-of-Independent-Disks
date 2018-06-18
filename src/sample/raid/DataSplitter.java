package sample.raid;

public class DataSplitter {

    public Raid3With3Discs splitInto3Discs(String input) {
        StringBuffer disc1 = new StringBuffer();
        StringBuffer disc2 = new StringBuffer();
        StringBuffer discParity = new StringBuffer();

        //

        return new Raid3With3Discs(disc1, disc2, discParity);
    }

    public Raid3With4Discs splitInto4Discs(String input) {
        StringBuffer disc1 = new StringBuffer();
        StringBuffer disc2 = new StringBuffer();
        StringBuffer disc3 = new StringBuffer();
        StringBuffer discParity = new StringBuffer();

        //

        return new Raid3With4Discs(disc1, disc2, disc3, discParity);
    }
}
