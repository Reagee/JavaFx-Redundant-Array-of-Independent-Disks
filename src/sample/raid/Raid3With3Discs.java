package sample.raid;

public class Raid3With3Discs {
    private String disc1;
    private String disc2;
    private String discParity;

    public Raid3With3Discs(String disc1, String disc2, String discParity) {

        this.disc1 = disc1;
        this.disc2 = disc2;
        this.discParity = discParity;
    }

    public String getDisc1() {
        return disc1;
    }

    public String getDisc2() {
        return disc2;
    }

    public String getDiscParity() {
        return discParity;
    }
}
