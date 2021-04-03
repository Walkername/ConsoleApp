package encryption;

import java.io.*;


public class Encryption {

    public final String key;

    public Encryption(String key) {
        this.key = key;
    }

    public int[] constructorKey (String keyStr) {
        int[] key = new int[keyStr.length() / 2];
        for (int i = 0, k = 0; i < keyStr.length(); i += 2) {
            String intToChar = keyStr.substring(i, i + 2);
            int number = Integer.parseInt(intToChar, 16);
            key[k++] = (byte) number;
        }
        return key;
    }

    public byte[] ciphxor(byte[] file, String key) {
        int[] keyInt = constructorKey(key);
        byte[] encrypted = new byte[file.length];
        for (int i = 0; i < file.length; i++) {
            encrypted[i] = (byte) (file[i] ^ keyInt[i% keyInt.length]);
        }
        return encrypted;
    }

    public void encrypt(String inputStream, String outputStream) {
        byte[] file = new byte[length(inputStream)];
        try {
            FileInputStream in = new FileInputStream(inputStream);
            FileOutputStream out = new FileOutputStream(outputStream);
            while (in.read(file) != -1) {
                out.write(ciphxor(file, key));
            }
            in.close();
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }

    public int length(String inputStream) {
        int number = 0;
        try {
            FileInputStream in = new FileInputStream(inputStream);
            while (in.read() != -1) {
                number++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return number;
    }

}


