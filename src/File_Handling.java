import java.io.*;
import java.nio.file.FileSystems;

public class File_Handling {
    //Global Vars
    static File directoryName = new File("DictionaryData");
    static File[] filesListed=directoryName.listFiles();
    static File userDataFile = new File(directoryName, "userData.txt");
    static String fileSeparator = FileSystems.getDefault().getSeparator();



    public static boolean Bootstrap() throws IOException {

        PrintFuncs.SysVerbose("Performing System Checks.");

        //Checks for dir

        if (!directoryName.exists()){

            if (directoryName.mkdir()){
                PrintFuncs.Action("mkdir at : " + directoryName.toString());
            } else {
                PrintFuncs.Warning("Failed to create '"+directoryName.getName() +"' directory.");
                return false;
            }

        } else {
            PrintFuncs.LYK("Directory '" + directoryName.getName() + "' is already created.");
        }

        //Check for user data file

        if (!userDataFile.exists()){

            if (userDataFile.createNewFile()){
                PrintFuncs.Action("Created file at :" + userDataFile.toString());
            } else {
                PrintFuncs.Warning("Failed to create '"+userDataFile.getName()+"' directory.");
                return false;
            }

        } else {
            PrintFuncs.LYK("'"+ userDataFile.getName()+"' file is already created.");
        }

        //Checks for section files / creates section files

        for (int i = 0; i < Program_Info.letterArray.length; i++){


            File fileExist = new File(directoryName,Program_Info.letterArray[i]+".txt");

            if (fileExist.exists()){
                PrintFuncs.LYK("File ["+fileExist.getName()+"] was found.");
            } else {

                PrintFuncs.Warning("File ["+fileExist.getName()+"] was not found.");
                PrintFuncs.Action("Creating ["+fileExist.getName()+"].");

                File sectionFile = new File(directoryName,Program_Info.letterArray[i]+".txt");

                if (sectionFile.createNewFile()){
                    PrintFuncs.Action("File ["+sectionFile.getName()+"] was created.");
                } else {
                    PrintFuncs.Warning("Failed to create '"+sectionFile.getName()+"'.");
                    return false;
                }

            }

        }


        return true;
    }

    public static void DisplayWordRecord(String lineFromFile)  {

        if (lineFromFile == null){
            return;
        }

            { //Displays where the record is store
                PrintFuncs.Verbose("Raw record data:"+lineFromFile);

            }



            String Word = (String) lineFromFile.subSequence(1,lineFromFile.lastIndexOf("]"));
            System.out.println(Word);

            String Speech = (String) lineFromFile.subSequence(lineFromFile.indexOf('{')+1, lineFromFile.lastIndexOf('}'));
            System.out.println(Speech);

            String Definition = (String) lineFromFile.subSequence(lineFromFile.indexOf('"')+1,lineFromFile.lastIndexOf('"'));
            System.out.println(Definition+"\n");

    }

    public static char FindFile (char selectedSection){


        if (filesListed != null){

            for (int i =0; i < filesListed.length; i++){

                String a = String.valueOf(filesListed[i]);
                int index = a.indexOf(fileSeparator);


                if (a.charAt(index+1) == selectedSection && a.charAt(index+2) == '.'){

                    char returnChar = (a.charAt(15));

                    PrintFuncs.Verbose("Returned char is : " +returnChar );
                    return returnChar;
                }


            }

            PrintFuncs.SysVerbose("Warning: Section file was not found.");
        } else {
            PrintFuncs.SysVerbose("Warning: Directory is empty");
        }
        return '!';


    }

    public static String WordCheck (String clients_Word, char SelectedSectionChar) throws IOException {
        //Vars
        float similarWordLengthPercentMin = 0.5F;

        float similarWordLengthPercentMax = 1.5F;

        int likelyWordCounter = 0;

        int notLikelyWordCounter = 0;

        boolean found_a_solution =false;


        //Creating the reader
        BufferedReader reader = new BufferedReader(new FileReader(("DictionaryData\\"+SelectedSectionChar+".txt")));
        String lineFromFile;

        {   //Prints which section file is being access, debug info
            PrintFuncs.Verbose("You're accessing the '"+SelectedSectionChar+".txt'");
        }


        //Word search algorithm
        while ((lineFromFile = reader.readLine()) !=null) {

            //Ignores Blank or special lines
            if (lineFromFile.isBlank() || lineFromFile.contains("/")){
                continue;
            }

            //Parses the record to obtain the word
            String wordFromRecord = (String) lineFromFile.subSequence(1,lineFromFile.lastIndexOf("]"));

            int wordFromRecordLength = wordFromRecord.length();

            float similarWordLength = (float) wordFromRecordLength /clients_Word.length();



            // Compares the literal words
            {
                if (wordFromRecordLength > clients_Word.length() || similarWordLength < similarWordLengthPercentMin || similarWordLength > similarWordLengthPercentMax) {
                    PrintFuncs.Verbose("NO | "+wordFromRecord.toUpperCase());
                    continue;
                }

                if (clients_Word.equalsIgnoreCase(wordFromRecord)){
                    System.out.println("YES | "+wordFromRecord.toUpperCase() );
                    return lineFromFile;
                }
            }

            //Compares user word to likely word
            {
                for (int s = 1; s < wordFromRecordLength; ++s){

                    // Compares just Chars
                    if (clients_Word.charAt(s) == wordFromRecord.charAt(s)){
                        likelyWordCounter++;
                    } else {
                        notLikelyWordCounter++;

                    }
                }
            }

            //Final decision call
            {
                if (likelyWordCounter > notLikelyWordCounter){
                    PrintFuncs.SysVerbose("MAYBE | "+wordFromRecord.toUpperCase());
                    found_a_solution =true;
                } else {
                    PrintFuncs.Verbose("NO | "+wordFromRecord.toUpperCase());
                }
            }



            {   //Debug info
                PrintFuncs.Verbose(("Total Likely counter num "+likelyWordCounter));
                PrintFuncs.Verbose("Likely chance that "+"'"+wordFromRecord+"'"+" is the word: "+(float) likelyWordCounter/wordFromRecord.length());
                PrintFuncs.Verbose(("Total Not-likely counter num "+notLikelyWordCounter));
                PrintFuncs.Verbose("Unlikely chance that "+"'"+wordFromRecord+"'"+" is the word: "+(float) notLikelyWordCounter/wordFromRecord.length());
                PrintFuncs.Verbose("");
            }



        }

        reader.close();

        //If it couldn't find a solution, defaults to error message
        if (!found_a_solution){
            PrintFuncs.SysVerbose("Could not find ["+clients_Word + "].");
            PrintFuncs.Verbose("If it is a word, please add to "+SelectedSectionChar+".txt in '"+File_Handling.directoryName.getName()+"' directory.");
            PrintFuncs.Verbose("Located at: "+(File_Handling.directoryName.getAbsolutePath()));
        }





        return null;
    }

    public static void WriteToFile (String fileToBeWriten) throws IOException {

        BufferedWriter bWriter = new BufferedWriter(new FileWriter(fileToBeWriten));


    }
    public static void fileReader (){

    }
}


