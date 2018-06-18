package sample.raid;

public class Raid3With4Discs {
    private StringBuffer disc1;
    private StringBuffer disc2;
    private final StringBuffer disc3;
    private final StringBuffer discParity;

    public Raid3With4Discs(StringBuffer disc1, StringBuffer disc2, StringBuffer disc3, StringBuffer discParity) {

        this.disc1 = disc1;
        this.disc2 = disc2;
        this.disc3 = disc3;
        this.discParity = discParity;
    }

    public StringBuffer getDisc1() {
        return disc1;
    }

    public StringBuffer getDisc2() {
        return disc2;
    }

    public StringBuffer getDisc3() {
        return disc3;
    }

    public StringBuffer getDiscParity() {
        return discParity;
    }
}
