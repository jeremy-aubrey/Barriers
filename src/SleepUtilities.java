// ** PROVIDED ** //

/**
 * utilities for causing a thread to sleep.
 * Note, we should be handling interrupted exceptions
 * but choose not to do so for code clarity.
 */

public class SleepUtilities
{
	private static final int NAP_TIME = 5;
	
	/**
	 * Nap between zero and NAP_TIME seconds.
	 */
	public static void takeNap()
	{
		takeNap(NAP_TIME);
	}

	/**
	 * Nap between zero and duration seconds.
	 */
	public static void takeNap(int duration)
	{
        int sleeptime = (int) (NAP_TIME * Math.random() );
        try {
        	Thread.sleep(sleeptime*1000);
        }
        catch (InterruptedException exception)
        {
        	exception.printStackTrace();
        	System.exit(1);
        }
	}
}