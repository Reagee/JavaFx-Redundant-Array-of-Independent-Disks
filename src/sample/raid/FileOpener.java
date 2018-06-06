package sample.raid;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileOpener {

    private String data;
    private String dataOne;
    private String dataTwo;
    private String bit;
    private String input = "1001001010101100";

    public FileOpener() {
        save(input);
        readData();
    }

    public void readData() {
        String user = System.getProperty("user.name");
        String absPath = "C:/Users/" + user + "/Desktop/Raid";
        String fileOnePath = absPath + "/DanePierwsze.txt";
        String fileTwoPath = absPath + "/DaneDrugie.txt";
        String bitFilePath = absPath + "/Bits.txt";

        if (!Files.exists(Paths.get(absPath))) {
            System.out.println("Nie można odnaleźć katalogu: " + absPath);
            System.exit(0);
        }
        if (!Files.exists(Paths.get(fileOnePath))) {
            System.out.println("Nie można odnaleźć pliku: " + fileOnePath);
            System.exit(0);
        }
        if (!Files.exists(Paths.get(fileTwoPath))) {
            System.out.println("Nie można odnaleźć pliku: " + fileTwoPath);
            System.exit(0);
        }
        if (!Files.exists(Paths.get(bitFilePath))) {
            System.out.println("Nie można odnaleźć pliku: " + bitFilePath);
            System.exit(0);
        }

        try {
            BufferedReader bits = new BufferedReader(new FileReader(bitFilePath));
            System.out.println("Otworzono plik z bitami");
            BufferedReader inDataOne = new BufferedReader(new FileReader(fileOnePath));
            System.out.println("Otworzono pierwszy plik z danymi");
            BufferedReader inDataTwo = new BufferedReader(new FileReader(fileTwoPath));
            System.out.println("Otworzono drugi plik z danymi");

            dataOne = inDataOne.readLine();
            dataTwo = inDataTwo.readLine();
            bit = bits.readLine();

            merge(dataOne, dataTwo);

            bits.close();
            inDataOne.close();
            inDataTwo.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFile(String path) {
        try {
            Files.createFile(Paths.get(path));
            System.out.println("Utworzono plik: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createDir(String path) {
        try {
            Files.createDirectory(Paths.get(path));
            System.out.println("Utworzono katalog: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private char xor(int a, int b) {

        if (a == b)
            return '0';
        else return '1';
    }

    private void save(String data) {
        String user = System.getProperty("user.name");
        String absPath = "C:/Users/" + user + "/Desktop/Raid";
        String fileOnePath = absPath + "/DanePierwsze.txt";
        String fileTwoPath = absPath + "/DaneDrugie.txt";
        String bitFilePath = absPath + "/Bits.txt";

        if (!Files.exists(Paths.get(absPath))) {
            createDir(absPath);
        }
        if (!Files.exists(Paths.get(fileOnePath))) {
            createFile(fileOnePath);
        }
        if (!Files.exists(Paths.get(fileTwoPath))) {
            createFile(fileTwoPath);
        }
        if (!Files.exists(Paths.get(bitFilePath))) {
            createFile(bitFilePath);
        }

        try {
            char[] inputArr = input.toCharArray();

            BufferedWriter outDataOneFile = new BufferedWriter(new FileWriter(fileOnePath));
            System.out.println("Zapisano plik: " + fileOnePath);
            BufferedWriter outDataTwoFile = new BufferedWriter(new FileWriter(fileTwoPath));
            System.out.println("Zapisano plik: " + fileTwoPath);
            BufferedWriter outParityBitFile = new BufferedWriter(new FileWriter(bitFilePath));
            System.out.println("Zapisano plik: " + bitFilePath);

            for (int i = 0; i < inputArr.length / 16; i++) {
                outDataOneFile.write(inputArr[i * 16 + 0]);
                outDataOneFile.write(inputArr[i * 16 + 1]);
                outDataOneFile.write(inputArr[i * 16 + 2]);
                outDataOneFile.write(inputArr[i * 16 + 3]);
                outDataOneFile.write(inputArr[i * 16 + 4]);
                outDataOneFile.write(inputArr[i * 16 + 5]);
                outDataOneFile.write(inputArr[i * 16 + 6]);
                outDataOneFile.write(inputArr[i * 16 + 7]);
                outDataTwoFile.write(inputArr[i * 16 + 8]);
                outDataTwoFile.write(inputArr[i * 16 + 9]);
                outDataTwoFile.write(inputArr[i * 16 + 10]);
                outDataTwoFile.write(inputArr[i * 16 + 11]);
                outDataTwoFile.write(inputArr[i * 16 + 12]);
                outDataTwoFile.write(inputArr[i * 16 + 13]);
                outDataTwoFile.write(inputArr[i * 16 + 14]);
                outDataTwoFile.write(inputArr[i * 16 + 15]);
                char xorBit0 = xor(inputArr[i * 16 + 0], inputArr[i * 16 + 8]);
                char xorBit1 = xor(inputArr[i * 16 + 1], inputArr[i * 16 + 9]);
                char xorBit2 = xor(inputArr[i * 16 + 2], inputArr[i * 16 + 10]);
                char xorBit3 = xor(inputArr[i * 16 + 3], inputArr[i * 16 + 11]);
                char xorBit4 = xor(inputArr[i * 16 + 4], inputArr[i * 16 + 12]);
                char xorBit5 = xor(inputArr[i * 16 + 5], inputArr[i * 16 + 13]);
                char xorBit6 = xor(inputArr[i * 16 + 6], inputArr[i * 16 + 14]);
                char xorBit7 = xor(inputArr[i * 16 + 7], inputArr[i * 16 + 15]);
                outParityBitFile.write(xorBit0);
                outParityBitFile.write(xorBit1);
                outParityBitFile.write(xorBit2);
                outParityBitFile.write(xorBit3);
                outParityBitFile.write(xorBit4);
                outParityBitFile.write(xorBit5);
                outParityBitFile.write(xorBit6);
                outParityBitFile.write(xorBit7);
            }

//            if (inputArr.length % 16 != 0)
//                outParityBitFile.write(inputArr[inputArr.length - 1]);

            outDataOneFile.close();
            outDataTwoFile.close();
            outParityBitFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateResult(String input, String discOne, String discTwo, String bits, String errors) {

        String user = System.getProperty("user.name");
        String absPath = "C:/Users/" + user + "/Desktop/RAID";
        String filePath = absPath + "/raport.txt";

        if (!Files.exists(Paths.get(absPath))) {
            createDir(absPath);
        }

        if (!Files.exists(Paths.get(filePath))) {
            createFile(filePath);
        }

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
            //mergeData
            out.write("Wejście: " + input + "    Dysk nr.1: " + dataOne + "    Dysk nr.2: " + dataTwo + "\r\n");
            out.write("Wyjście: " + data + "    Dysk nr.2: " + discOne + "    Dysk nr.2: " + discTwo + "\r\n");
            out.write("Bity parzystości: " + bits + "\r\n");

            if (!dataOne.equals(discOne) && !dataTwo.equals(discTwo))
                out.write(errors + " uszkodzony dysk nr.1 oraz nr.2");
            else if (!dataOne.equals(discOne))
                out.write(errors + " na dysku nr.1");
            else if (!dataTwo.equals(discTwo))
                out.write(errors + " na dysku nr.2");

            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void merge(String dataOne, String dataTwo) {
        char[] valuesOne = dataOne.toCharArray();
        char[] valuesTwo = dataTwo.toCharArray();

        data = "";

        for (int i = 0; i < valuesOne.length/8; i++) {
            data += valuesOne[i+0];
            data += valuesOne[i+1];
            data += valuesOne[i+2];
            data += valuesOne[i+3];
            data += valuesOne[i+4];
            data += valuesOne[i+5];
            data += valuesOne[i+6];
            data += valuesOne[i+7];

            data += valuesTwo[i+0];
            data += valuesTwo[i+1];
            data += valuesTwo[i+2];
            data += valuesTwo[i+3];
            data += valuesTwo[i+4];
            data += valuesTwo[i+5];
            data += valuesTwo[i+6];
            data += valuesTwo[i+7];
        }

//        if (valuesOne.length != valuesTwo.length)
//            data += valuesOne[valuesOne.length];

        System.out.println("Dane po operacji łączenia: " + data + " ,rozmiar danych: " + data.length());
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDataOne() {
        return dataOne;
    }

    public void setDataOne(String dataOne) {
        this.dataOne = dataOne;
    }

    public String getDataTwo() {
        return dataTwo;
    }

    public void setDataTwo(String dataTwo) {
        this.dataTwo = dataTwo;
    }

    public String getBit() {
        return bit;
    }

    public void setBit(String bit) {
        this.bit = bit;
    }
}
