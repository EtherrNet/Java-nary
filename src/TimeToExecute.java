public class TimeToExecute
{
    static long startTime = 0;
    static long endTime = 0;

    public static void TimeToExecute (){
        long executionTime = (System.nanoTime() - startTime) /1000000;
        PrintFuncs.Verbose("Time to find a solution took "+executionTime+"ms");

    }
}
