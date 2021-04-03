package encryption;

import org.junit.Test;
import java.io.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class EncryptionTest {

    @Test
    public void key() {
        String str = "A29F fileInput.txt";
        String[] strings = str.split("\\s+");
        assertThrows(IllegalArgumentException.class, () -> new EncryptionLauncher().launch(strings));
        str = "-f A29F fileInput.txt";
        String[] strings1 = str.split("\\s+");
        assertThrows(IllegalArgumentException.class, () -> new EncryptionLauncher().launch(strings1));
    }

    @Test
    public void inputName() {
        String str = "-c A29F";
        String[] strings = str.split("\\s+");
        assertThrows(IllegalArgumentException.class, () -> new EncryptionLauncher().launch(strings));
        str = "-d A29F";
        String[] strings1 = str.split("\\s+");
        assertThrows(IllegalArgumentException.class, () -> new EncryptionLauncher().launch(strings1));
    }

    @Test
    public void outputName() {
        String str = "-c A29F fileInput.txt fileOutput";
        String[] strings = str.split("\\s+");
        assertThrows(IllegalArgumentException.class, () -> new EncryptionLauncher().launch(strings));
        str = "-c A29F fileInput.txt -o";
        String[] strings1 = str.split("\\s+");
        assertThrows(IllegalArgumentException.class, () -> new EncryptionLauncher().launch(strings1));
        str = "-c A29F fileInput.txt -p";
        String[] strings2 = str.split("\\s+");
        assertThrows(IllegalArgumentException.class, () -> new EncryptionLauncher().launch(strings2));
    }

    @Test
    public void cmdLine() {
        String str = "";
        String[] strings = str.split("\\s+");
        assertThrows(IllegalArgumentException.class, () -> new EncryptionLauncher().launch(strings));
        str = "-c A29F inputname.txt";
        String[] strings1 = str.split("\\s+");
        assertThrows(IllegalArgumentException.class, () -> new EncryptionLauncher().launch(strings1));
    }

    @Test
    public void ciphxor() throws IOException {
        String key = "A29F";
        String inputFile = "input/inputname.txt";
        String outputFile = "input/outputname.txt";
        String outputChange = "input/outputchange.txt";
        Encryption file = new Encryption(key);
        Encryption file1 =new Encryption(key);
        file.encrypt(inputFile, outputFile);
        file1.encrypt(outputFile, outputChange);
        FileInputStream fileReader = new FileInputStream(inputFile);
        FileInputStream fileReader1 = new FileInputStream(outputChange);
        int str;
        int str1;
        while ((str = fileReader.read()) != -1) {
            str1 = fileReader1.read();
            assertEquals(str, str1);
        }
        fileReader.close();
        fileReader1.close();
    }

    @Test
    public void length() {
        Encryption file = new Encryption("A29F");
        assertEquals(60, file.length("input/inputname.txt"));
        assertEquals(60, file.length("input/outputname.txt"));
        assertEquals(60, file.length("input/outputchange.txt"));
    }

}
