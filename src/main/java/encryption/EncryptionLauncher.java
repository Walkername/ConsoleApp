package encryption;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class EncryptionLauncher {

    @Option(name = "-c" , aliases = {"-d"},metaVar = "Key", required = true, usage = "Encrypt/Decrypt")
    private String key;

    @Argument(required = true, metaVar = "InputName", usage = "Input file name")
    private String inputFileName;

    @Option(name = "-o", metaVar = "OutputName", required = true, usage = "Output file name")
    private String outputFileName;



    public static void main(String[] args) {
        new EncryptionLauncher().launch(args);
    }


    public void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
            if (!checkKey(key)) throw new IllegalArgumentException("Need to change the key");
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            throw new IllegalArgumentException("java -jar ConsoleApp.jar [-c key] [-d key] inputname.txt [-o outputname.txt]");
        }

        Encryption encryption = new Encryption(key);
        encryption.encrypt(inputFileName, outputFileName);
        System.out.println("Encrypting... Done!");
    }


    public boolean checkKey(String key) {
        Matcher mat = Pattern.compile("^(([a-fA-F0-9]{2})*)$").matcher(key);
        return mat.matches();
    }

}

// Encrypt: java -jar target/ConsoleApp-1.0-SNAPSHOT-jar-with-dependencies.jar -c A29F inputname.txt -o outputname.txt
// Decrypt: java -jar target/ConsoleApp-1.0-SNAPSHOT-jar-with-dependencies.jar -c A29F outputname.txt -o inputname.txt
