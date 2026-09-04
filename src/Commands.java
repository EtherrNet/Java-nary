import java.io.IOException;
import java.util.Scanner;

/*
this class calls external class-methods as to streamline which methods the user wants to invoke happen.
*/

public class Commands {

    // This function handles the user input.
    //As to which functions does the user want to invoke.
    public static void CommandList (String userInput) throws IOException {

        //As Methods can only take 1 input, the creation of another input will be for 'DictionaryFunc'
        Scanner strInput = new Scanner(System.in);



        if (userInput.isBlank()) {
            //when the user needs help it will display some help :)
            System.out.println("For help: '?' or 'help'");
        } else if (userInput.equals("sr") || userInput.equals("search")) {
            //Invokes DictionaryFunc
            DictionaryFunc(strInput);
        } else if (userInput.trim().charAt(0) == '-' && userInput.length() >= 2){
            //Invokes DashCommands
            DashCommands(userInput);
        } else if (userInput.equals("q") || userInput.equalsIgnoreCase("quit")) {
            //quits program
            Program_Info.PROGRAM_STATE = false;
        } else if (userInput.equals("?") || userInput.equalsIgnoreCase("help")) {
            //Displays help commands
            PrintFuncs.SysVerbose("Commands:\n [? : help] list commands\n [sr : search] to lookup\n [q : quit]\n [-v] displays verbose bool\n [-vf : -vt] set verbose to false or true");
        } else {
            PrintFuncs.SysVerbose("Unknown Command.");
        }



    }

    //The main function that handles all the dictionary class-method calling.
    private static void DictionaryFunc (Scanner strInput) throws IOException {

        String clientInput;
        //The client word to be searched.
        clientInput = strInput.nextLine();

        char fileSelectedChar;


        //Checks to see if user throws an invalid input
        char selectedChar = SectionLetterCheck(clientInput);

        if (selectedChar == '!') {
            PrintFuncs.Error("Bad input!!!");
            return;
        }

        /* OPINION
        It might be best to remove the method 'FindFile' (as at 'bootstrap' we check if all files exist)
        or integrate the function of it into 'SectionLetterCheck'. Maybe the former.
         */
        //Checks if the section file exist.
        fileSelectedChar = File_Handling.FindFile(selectedChar);

        //Last guardrail before the main act.
        if (fileSelectedChar == '!') {
            PrintFuncs.Error("You really fucked up.");
            return;
        }

        //Measuring Time
        TimeToExecute.startTime = System.nanoTime();

        //Finds the word within the text file
        String lineFromFile = File_Handling.WordCheck(clientInput, fileSelectedChar);
        TimeToExecute.TimeToFinish();

        //Displays the entry found in 'WordCheck;
        File_Handling.DisplayWordRecord(lineFromFile);

    }

    //Split off from CommandList allows me to add more complexity to DashCommands,-
    // without blowing up the Commandlist method.
    private static void DashCommands (String userInput){

            // The V-erbose bool switch command
            if (userInput.charAt(1) == 'v' && userInput.length() == 2) {
                PrintFuncs.SysVerbose("Verbose setting :" + Program_Info.InfoVerbose);
            } else if (userInput.length() == 3 && userInput.charAt(2) == 't') {
                Program_Info.InfoVerbose = true;
            } else if (userInput.length() == 3 && userInput.charAt(2) == 'f') {
                Program_Info.InfoVerbose = false;
            } else {
                PrintFuncs.SysVerbose("Unknown Command.");

            }


    }

    //We check if the first letter of the client's word is a valid character.
    public static char SectionLetterCheck (String intialWord){

        intialWord = intialWord.toLowerCase();

        if (!intialWord.isBlank() && !(intialWord.length() == 1)){
            for (int i  = 0; i < Program_Info.letterArray.length; ++i){

                if (intialWord.charAt(0) == Program_Info.letterArray[i]){
                    PrintFuncs.Verbose("Word belongs to the '"+Program_Info.letterArray[i]+"' section");
                    return Program_Info.letterArray[i];
                }

            }
        }

        return '!';

    }
}


