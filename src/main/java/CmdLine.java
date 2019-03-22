import org.apache.commons.cli.*;

import java.io.File;

/**
 * Created by Krzysztof on 2019-03-21.
 */
public class CmdLine {

    private CommandLine commandLine;

    private static final String PASSWORD = "password";
    private static final String FILE = "file";
    private static final String DECRYPT = "decrypt";
    private static final String HELP = "help";

    public CmdLine(String[] args) throws ParseException {

        CommandLineParser parser = new DefaultParser();
        Options options = initOptions();
        commandLine = parser.parse(options,args);

        if(commandLine.hasOption('h')){
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar crypto [option]",options);
        }
    }

    private Options initOptions(){
        Options options = new Options();
        options.addOption("p", PASSWORD ,true, "Encryption key");
        options.addOption("f", FILE,true,"Input file or directory");
        options.addOption("d", DECRYPT,false, "Set Decrypt because Encrypt is current ");
        options.addOption("h", HELP,false, "Print this help");

        return options;
    }

    public String getKey(){
        return commandLine.getOptionValue(PASSWORD);
    }

    public String getFile(){
        return commandLine.getOptionValue(FILE);
    }

    public boolean isDecrypt(){
        return  commandLine.hasOption(DECRYPT);
    }




}
