package sample.raid;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileOpener {

    private String input = "1001001010101100";
    private String dataOne;
    private String dataTwo;
    private String dataThree;
    private String parityBits;
    private String dataOutput;

    public FileOpener() {
        save(input);
        saveWithAdditionalDisc(input);
        readData();
    }

    public void readData() {
        String user = System.getProperty("user.name");
        String absPath = "C:/Users/" + user + "/Desktop/Raid";
        String fileOnePath = absPath + "/DyskPierwszy.txt";
        String fileTwoPath = absPath + "/DyskDrugi.txt";
        String bitFilePath = absPath + "/DyskZBitamiParzystosci.txt";

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
            parityBits = bits.readLine();

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

    public String getDataThree() {
        return dataThree;
    }

    public void readDataWithAdditionalDisc() {
        String user = System.getProperty("user.name");
        String absPath = "C:/Users/" + user + "/Desktop/Raid/Additional_Disc";
        String fileOnePath = absPath + "/DyskPierwszy.txt";
        String fileTwoPath = absPath + "/DyskDrugi.txt";
        String fileThreePath = absPath + "/DyskTrzeci.txt";
        String bitFilePath = absPath + "/DyskZBitamiParzystosci.txt";

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
        if (!Files.exists(Paths.get(fileThreePath))) {
            System.out.println("Nie można odnaleźć pliku: " + fileThreePath);
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
            BufferedReader inDataThree = new BufferedReader(new FileReader(fileThreePath));
            System.out.println("Otworzono trzeci plik z danymi");

            dataOne = inDataOne.readLine();
            dataTwo = inDataTwo.readLine();
            dataThree = inDataThree.readLine();
            parityBits = bits.readLine();

            mergeWithAdditionalDisc(dataOne,dataTwo,dataThree);

            bits.close();
            inDataOne.close();
            inDataTwo.close();
            inDataThree.close();


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

    public void save(String data) {
        String user = System.getProperty("user.name");
        String absPath = "C:/Users/" + user + "/Desktop/Raid";
        String fileOnePath = absPath + "/DyskPierwszy.txt";
        String fileTwoPath = absPath + "/DyskDrugi.txt";
        String bitFilePath = absPath + "/DyskZBitamiParzystosci.txt";

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
            char[] inputArr = data.toCharArray();

            BufferedWriter outDataOneFile = new BufferedWriter(new FileWriter(fileOnePath));
            BufferedWriter outDataTwoFile = new BufferedWriter(new FileWriter(fileTwoPath));
            BufferedWriter outParityBitFile = new BufferedWriter(new FileWriter(bitFilePath));

            for (int i = 0; i < inputArr.length / 16; i++) {
                int j;
                char xorBit;
                for(j = 0; j < 8; j++) {
                    outDataOneFile.write(inputArr[i * 16 + j]);
                    xorBit = xor(inputArr[i * 16 + j], inputArr[i * 16 + 8 + j]);
                    outParityBitFile.write(xorBit);
                }
                for(j = 8; j < 16; j++)
                    outDataTwoFile.write(inputArr[i * 16 + j]);
            }

//            if (inputArr.length % 16 != 0)
//                outParityBitFile.write(inputArr[inputArr.length - 1]);

            outDataOneFile.close();
            outDataTwoFile.close();
            outParityBitFile.close();

            System.out.println("Zapisano plik: " + fileOnePath);
            System.out.println("Zapisano plik: " + fileTwoPath);
            System.out.println("Zapisano plik: " + bitFilePath);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveWithAdditionalDisc(String data) {
        String user = System.getProperty("user.name");
        String absPath = "C:/Users/" + user + "/Desktop/Raid/Additional_Disc";
        String fileOnePath = absPath + "/DyskPierwszy.txt";
        String fileTwoPath = absPath + "/DyskDrugi.txt";
        String fileThreePath = absPath + "/DyskTrzeci.txt";
        String bitFilePath = absPath + "/DyskZBitamiParzystosci.txt";

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
            char[] inputArr = data.toCharArray();

            BufferedWriter outDataOneFile = new BufferedWriter(new FileWriter(fileOnePath));
            BufferedWriter outDataTwoFile = new BufferedWriter(new FileWriter(fileTwoPath));
            BufferedWriter outDataThreeFile = new BufferedWriter(new FileWriter(fileThreePath));
            BufferedWriter outParityBitFile = new BufferedWriter(new FileWriter(bitFilePath));

            for (int i = 0; i < inputArr.length / input.length(); i++) {
                int j;
                char xorBit;
                for(j = 0; j < input.length()/3; j++) {
                    outDataOneFile.write(inputArr[i * input.length() + j]);
                    xorBit = xor(inputArr[i * input.length()+ j], inputArr[i * input.length() + input.length()/2 + j]);
                    outParityBitFile.write(xorBit);
                }
                for(j = input.length()/3; j < input.length()*2/3; j++)
                    outDataTwoFile.write(inputArr[i * input.length() + j]);
                for(j = input.length()*2/3; j < input.length(); j++)
                    outDataThreeFile.write(inputArr[i * input.length() + j]);
            }

            outDataOneFile.close();
            outDataTwoFile.close();
            outDataThreeFile.close();
            outParityBitFile.close();

            System.out.println("Zapisano plik: " + fileOnePath);
            System.out.println("Zapisano plik: " + fileTwoPath);
            System.out.println("Zapisano plik: " + fileThreePath);
            System.out.println("Zapisano plik: " + bitFilePath);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateResult(String input, String discOne, String discTwo, String parityBits, String errors) {

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
            out.write("Wejście:   Dane startowe: " + input + "\r\n");
            out.write("RAID 3:    Dysk nr.1: " + discOne + "    Dysk nr.2: " + discTwo + "    Bity parzystości: " + parityBits + "\r\n");
            out.write("Wyjście:   Dane po połączeniu: " + dataOutput + "\r\n");

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

    public void generateResultWithAdditionalDisc(String input, String discOne, String discTwo, String discThree, String parityBits, String errors) {

        String user = System.getProperty("user.name");
        String absPath = "C:/Users/" + user + "/Desktop/RAID/Additional_Disc";
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
            out.write("Wejście:   Dane startowe: " + input + "\r\n");
            out.write("RAID 3:    Dysk nr.1: " + discOne + "    Dysk nr.2: " + discTwo + "    Dysk nr.3: "+discThree+"      Bity parzystości: " + parityBits + "\r\n");
            out.write("Wyjście:   Dane po połączeniu: " + dataOutput + "\r\n");

            if (!dataOne.equals(discOne) && !dataTwo.equals(discTwo) && !dataThree.equals(discThree))
                out.write(errors + " uszkodzony dysk nr.1, nr.2 oraz nr.3");
            else if (!dataOne.equals(discOne) && !dataTwo.equals(discTwo))
                out.write(errors + " na dysku nr.1 oraz nr.2");
            else if (!dataTwo.equals(discTwo) && !dataThree.equals(discThree))
                out.write(errors + " na dysku nr.2 oraz nr.3");
            else if(!dataOne.equals(discOne) && !dataThree.equals(discThree))
                out.write(errors + " na dysku nr.1 oraz nr.3");
            else if(!dataOne.equals(discOne))
                out.write(errors + " na dysku nr.1");
            else if(!dataTwo.equals(discTwo))
                out.write(errors + " na dysku nr.2");
            else if(!dataThree.equals(discThree))
                out.write(errors + " na dysku nr.3");

            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void merge(String dataOne, String dataTwo) {
        dataOutput = DataOperator.mergeData(dataOne, dataTwo);

//        if (valuesOne.length != valuesTwo.length)
//            dataOutput += valuesOne[valuesOne.length];

        System.out.println("Dane po operacji łączenia: " + dataOutput + " ,rozmiar danych: " + dataOutput.length());
    }

    public void mergeWithAdditionalDisc(String dataOne,String dataTwo, String dataThree){
        dataOutput = DataOperator.mergeData(dataOne,dataTwo);
        dataOutput = DataOperator.mergeData(dataOutput,dataThree);

        System.out.println("Dane po operacji łączenia: " + dataOutput + " ,rozmiar danych: " + dataOutput.length());
    }

    public String getDataOutput() {
        return dataOutput;
    }

    public void setDataOutput(String dataOutput) {
        this.dataOutput = dataOutput;
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

    public String getParityBits() {
        return parityBits;
    }

    public void setParityBits(String parityBits) {
        this.parityBits = parityBits;
    }
}
