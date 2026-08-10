import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main (String[] args ) throws IOException {


        //Var
        Scanner strInput = new Scanner(System.in);
        String clientInput;


        //Program starts here
        if (File_Handling.Bootstrap()){
            TitleText();
            while (Program_Info.PROGRAM_STATE){

                clientInput = strInput.nextLine();

                Commands.CommandList(clientInput);

            }
        }


        {
            //Test functions block

        }



    }

    //Class Functions

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

    public static void TitleText (){
        PrintFuncs.SysVerbose("\nJavanary\nFree Dictionary Application\n");
    }

}
