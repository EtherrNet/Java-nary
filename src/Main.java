import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main (String[] args ) throws IOException {


        //Var
        Scanner strInput = new Scanner(System.in);
        String clientInput;


        //Program starts here
        if (File_Handling.Bootstrap()) {
            PrintFuncs.SysVerbose("\nJavanary\nFree Dictionary Application\n");
            while (Program_Info.PROGRAM_STATE) {

                clientInput = strInput.nextLine();

                Commands.CommandList(clientInput);

            }
        }

        { //test funcs
            //File_Handling.WriteToFile(File_Handling.userDataFile.getAbsolutePath());
        }

    }
}
