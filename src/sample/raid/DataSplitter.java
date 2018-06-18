package sample.raid;

public class DataSplitter {

    public Raid3With3Discs splitInto3Discs(String input) {
        StringBuffer disc1 = new StringBuffer();
        StringBuffer disc2 = new StringBuffer();
        StringBuffer discParity = new StringBuffer();

        for (int i = 0; i < input.length() / 16; i++) {
            String disc1Byte = input.substring(16 * i + 0, 16 * i + 8);
            String disc2Byte = input.substring(16 * i + 8, 16 * i + 16);
            StringBuffer parityByte = new StringBuffer();
            for (int j = 0; j < 8; j++) {
                parityByte.append(xor(disc1Byte.charAt(j), disc2Byte.charAt(j)));
            }
            disc1.append(disc1Byte);
            disc2.append(disc2Byte);
            discParity.append(parityByte);
        }

        return new Raid3With3Discs(disc1.toString(), disc2.toString(), discParity.toString());
    }

    public Raid3With4Discs splitInto4Discs(String input) {
        StringBuffer disc1 = new StringBuffer();
        StringBuffer disc2 = new StringBuffer();
        StringBuffer disc3 = new StringBuffer();
        StringBuffer discParity = new StringBuffer();

        for (int i = 0; i < input.length() / 24; i++) {
            String disc1Byte = input.substring(24 * i + 0, 24 * i + 8);
            String disc2Byte = input.substring(24 * i + 8, 24 * i + 16);
            String disc3Byte = input.substring(24 * i + 16, 24 * i + 24);
            StringBuffer parityByte = new StringBuffer();
            for (int j = 0; j < 8; j++) {
                parityByte.append(xor(disc1Byte.charAt(j), disc2Byte.charAt(j), disc3Byte.charAt(j)));
            }
            disc1.append(disc1Byte);
            disc2.append(disc2Byte);
            disc3.append(disc3Byte);
            discParity.append(parityByte);
        }

        return new Raid3With4Discs(disc1.toString(), disc2.toString(), disc3.toString(), discParity.toString());
    }

    private char xor(char a, char b) {
        if (a == b) return '0';
        else return '1';
    }

    private char xor(char a, char b, char c) {
        return xor(xor(a, b), c);
    }
}
