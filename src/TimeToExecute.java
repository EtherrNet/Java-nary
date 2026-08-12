public class TimeToExecute
{
    // Wanted to measure how long it takes for the wordcheck to find a word.
    static long startTime = 0;

    public static void TimeToFinish(){
        long executionTime = (System.nanoTime() - startTime) /1000000;
        PrintFuncs.Verbose("Time to complete function "+executionTime+"ms");

    }
}
