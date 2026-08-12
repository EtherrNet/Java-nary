public class PrintFuncs {


    static public void SysVerbose(String SysVerboseText){
        if (Program_Info.SysVerbose){
            System.out.println(SysVerboseText);
        }

    }
    static public void Verbose(String VerboseText){
        if (Program_Info.InfoVerbose){
            System.out.println(VerboseText);
        }

    }
    static public void Warning(String WarningText){

        String textToAppend = "WARNING: ";
        System.out.println(textToAppend + WarningText);

    }
    static public void Action (String ActionText){
        String textToAppend = "Action: ";
        System.out.println(textToAppend+ActionText);
    }
    static public void Error (String ErrorText){
        String textToAppend = "Error: ";
        System.out.println(textToAppend+ErrorText);
    }
    static public void LYK (String LYKText){
        String textToAppend = "LYK: ";
        System.out.println(textToAppend+LYKText);
    }

}
