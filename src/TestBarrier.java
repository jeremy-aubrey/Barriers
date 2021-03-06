// ** PROVIDED ** //

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * A testing harness for the Barrier class.
 *
 * Proper output is that we should see is that all threads 
 * output an 'A' before reaching the barrier and then a 'B'
 * after proceeding through the barrier. Therefore, output
 * should appear as a series of 'A's followed by an equal count
 * of 'B's. (There should not be an intermingling of 'A's and 'B's.
 */

public class TestBarrier  
{
	public static final int THREAD_COUNT = 5;

    public static void main(String args[]) throws java.io.IOException
	{
    	
    	TestBarrier obj = new TestBarrier();
    	obj.developerInfo();
    	
		System.out.println("Proper output is that we should see is that all threads");
 		System.out.println("output an 'A' before reaching the barrier and then a 'B'");
 		System.out.println("after proceeding through the barrier. Therefore, output");
 		System.out.println("should appear as a series of 'A's followed by an equal count");
 		System.out.println("of 'B's. (There should not be an intermingling of 'A's and 'B's.");

 		System.out.println("\n\nPress Enter to begin the test:");
		
		(new BufferedReader(new InputStreamReader(System.in))).read();

		// initialize the barrier to the number of threads
		// waiting upon the barrier
        Barrier barrier = new BarrierImpl(THREAD_COUNT);

		Thread[] workers = new Thread[THREAD_COUNT];
		for (int i = 0; i < workers.length; i++)
		{ 
			workers[i] = new Thread(new Worker(barrier));
			workers[i].start();
		}

		try {
			for (int i = 0; i < workers.length; i++)
				workers[i].join();
		}
		catch (InterruptedException ie)
		{
			
		}
		
		System.out.println("\n\nPress Enter to begin the freeAll() test:");
        (new BufferedReader(new InputStreamReader(System.in))).read();

		barrier = new BarrierImpl(THREAD_COUNT + 1);
		workers = new Thread[THREAD_COUNT];
		
        for (int i = 0; i < workers.length; i++)
        {
            workers[i] = new Thread(new Worker(barrier));
            workers[i].start();
        }

		try {
			Thread.sleep(3000);
		}
		catch (InterruptedException ie)
		{
			
		}
		
		barrier.freeAll();

    }
    
    //***************************************************************
    //
    //  Method:       developerInfo (Non Static)
    // 
    //  Description:  The developer information method of the program.
    //
    //  Parameters:   None
    //
    //  Returns:      N/A 
    //
    //**************************************************************
    public void developerInfo()
    {
       System.out.println("Name:    Jeremy Aubrey");
       System.out.println("Course:  COSC 4302 Operating Systems");
       System.out.println("Program: 6\n");

    }// end developerInfo method
}
