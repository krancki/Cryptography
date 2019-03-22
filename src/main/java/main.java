import ciphers.DESede;
import ciphers.ICryptoType;
import org.apache.commons.cli.ParseException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * Created by Krzysztof on 2019-03-07.
 */
public class main {


    public static void main(String[] args) throws ParseException, IOException, BadPaddingException, IllegalBlockSizeException {

        CmdLine cmdLine = new CmdLine(args);


        String currentDirectory = System.getProperty("user.dir");


        ICryptoType cryptoType = new DESede(cmdLine.getKey());
        Path path = FileSystems.getDefault().getPath(currentDirectory, cmdLine.getFile());
        byte[] file = Files.readAllBytes(path);

        if (cmdLine.isDecrypt()) {
            file = cryptoType.decrypt(file);
        } else {
            file = cryptoType.encrypt(file);
        }

        Files.write(path, file);

    }


}


