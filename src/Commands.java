import java.io.IOException;
import java.util.Scanner;

public class Commands {

    public static void CommandList (String userInput) throws IOException {

        Scanner strInput = new Scanner(System.in);


        //Dictionary Search
        if (userInput.isBlank()) {
            System.out.println("For help: '?' or 'help'");

        } else if (userInput.equals("sr") || userInput.equals("search")) {

            DictionaryFunc(strInput);

            //Dash commands
        } else if (DashCommands(userInput)){

        //Help user commands
        } else if (userInput.equals("q") || userInput.equalsIgnoreCase("quit")) {
            Program_Info.PROGRAM_STATE = false;
        } else if (userInput.equals("?") || userInput.equalsIgnoreCase("help")) {
            PrintFuncs.SysVerbose("Commands:\n [? : help] list commands\n [sr : search] to lookup\n [q : quit]\n [-v] displays verbose bool\n [-vf : -vt] set verbose to false or true");
        } else {
            PrintFuncs.SysVerbose("Unknown Command.");
        }



    }
    private static void DictionaryFunc (Scanner strInput) throws IOException {
        //The word finder
        String clientInput;

        clientInput = strInput.nextLine();

        char selectedChar = Main.SectionLetterCheck(clientInput);

        char fileSelectedChar = '!'; //default null for safety

        if (selectedChar != '!') {
            fileSelectedChar = File_Handling.FindFile(selectedChar);
        } else {
            PrintFuncs.Error("Bad input!!!");
        }


        if (fileSelectedChar != '!') {
            File_Handling.WordCheck(clientInput, fileSelectedChar);
        }
    }

    private static boolean DashCommands (String userInput){
        //verbose command

        if (userInput.trim().charAt(0) == '-' && userInput.length() >= 2){


            if (userInput.charAt(1) == 'v') {

                if (userInput.length() == 3 && userInput.charAt(2) == 't') {
                    Program_Info.InfoVerbose = true;
                } else if (userInput.length() == 3 && userInput.charAt(2) == 'f') {
                    Program_Info.InfoVerbose = false;
                } else {
                    PrintFuncs.SysVerbose("Verbose setting :" + Program_Info.InfoVerbose);
                }
            }

            return true;
        }
        return false;
    }
}
