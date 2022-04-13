/**
 * A worker using the Barrier
 */
public class Worker implements Runnable 
{
	private Barrier partA;

	public Worker(Barrier partA)
	{
		this.partA = partA;
	}

	/**
	 * Each thread will do some work for awhile, and then
	 * invoke waitForOthers(). A thread may proceed when all
	 * other threads have arrived at the barrier.
	 */ 
	public void run()
	{
		System.out.println("A");
		SleepUtilities.takeNap();

		partA.waitForOthers();

		System.out.println("B");
	}
}