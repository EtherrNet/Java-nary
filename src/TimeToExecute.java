public class TimeToExecute
{
    // Wanted to measure how long it takes for the wordcheck to find a word.
    static double startTime = 0;

    public static void TimeToFinish(){
        double executionTime = (System.nanoTime() - startTime) / 1_000_000;
        PrintFuncs.Verbose("Time to complete function "+executionTime+"ms");

    }
}
