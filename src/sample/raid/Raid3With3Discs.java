package sample.raid;

public class Raid3With3Discs {
    private StringBuffer disc1;
    private StringBuffer disc2;
    private StringBuffer discParity;

    public Raid3With3Discs(StringBuffer disc1, StringBuffer disc2, StringBuffer discParity) {

        this.disc1 = disc1;
        this.disc2 = disc2;
        this.discParity = discParity;
    }

    public StringBuffer getDisc1() {
        return disc1;
    }

    public StringBuffer getDisc2() {
        return disc2;
    }

    public StringBuffer getDiscParity() {
        return discParity;
    }
}
