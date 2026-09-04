public class PrintFuncs {

    //Custom print functions for my needs. I hope the names explains themselves

    // System Verbose is text that is needed to inform the user.
    static public void SysVerbose(String SysVerboseText){
        if (Program_Info.SysVerbose){
            System.out.println(SysVerboseText);
        }

    }
    // Debug print
    static public void Verbose(String VerboseText){
        if (Program_Info.InfoVerbose){
            System.out.println(VerboseText);
        }

    }
    //Used for functions that have warnings,
    static public void Warning(String WarningText){

        String textToAppend = "WARNING: ";
        System.out.println(textToAppend + WarningText);

    }
    //Informs the user that an action has taken place on their system
    static public void Action (String ActionText){
        String textToAppend = "Action: ";
        System.out.println(textToAppend+ActionText);
    }
    //Basic error print
    static public void Error (String ErrorText){
        String textToAppend = "Error: ";
        System.out.println(textToAppend+ErrorText);
    }
    //Like 'Action' print method but lets the user know about a something.
    static public void LYK (String LYKText){
        String textToAppend = "LYK: ";
        System.out.println(textToAppend+LYKText);
    }

}
