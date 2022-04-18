//********************************************************************
//
//  Author:        Jeremy Aubrey
//
//  Program #:     6
//
//  File Name:     BarrierImpl.java
//
//  Course:        COSC-4302 Operating Systems
//
//  Due Date:      04/09/2022 (Original, moved)
//
//  Instructor:    Fred Kumi 
//
//  Chapter:       6 & 7
//
//  Description:   A class that extends the abstract Barrier class and 
//                 implements the waitForOthers and freeAll() methods.
//                 The function of this class is to provide a means of 
//                 synchronization using a barrier.
//
//*********************************************************************

public class BarrierImpl extends Barrier {

	private int limit;         // max threads to wait
	private int waiting;       // current waiting thread count
	private boolean lifted;    // flag to allow threads to bypass wait (freeAll)
	
	// constructor
	public BarrierImpl(int limit) {
		this.limit = limit;
		lifted = false;
	}
	
    //***************************************************************
    //
    //  Method:       waitForOthers (Non Static)
    // 
    //  Description:  Causes current thread to wait if the number of 
    //                waiting threads is less than the barrier limit.
    //                If the invoking thread causes the limit to be met
    //                it notifies all threads to continue execution.
    //
    //  Parameters:   None
    //
    //  Returns:      N/A 
    //
    //**************************************************************
	@Override
	public void waitForOthers() {
		incrementWaiting();
		if(waiting < limit && !lifted) {
			beginWait();
		} else if(waiting == limit || lifted) {
			endAllWait();
		}
		decrementWaiting();
	}// end waitForOthers method
	
    //***************************************************************
    //
    //  Method:       beginWait (Non Static)
    // 
    //  Description:  A helper method that calls the inherited wait method.
    //                Causes current thread to wait until it is notified.
    //
    //  Parameters:   None
    //
    //  Returns:      N/A 
    //
    //**************************************************************
	public void beginWait() {
		synchronized(this) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}// end beginWait method
	
    //***************************************************************
    //
    //  Method:       endAllWait (Non Static)
    // 
    //  Description:  A helper method that calls the inherited notifyAll method.
    //                Notifies all waiting threads to continue execution.
    //
    //  Parameters:   None
    //
    //  Returns:      N/A 
    //
    //**************************************************************
	public void endAllWait() {
		synchronized(this) {
			notifyAll();
		}
	}// end endAllWait method
	
    //***************************************************************
    //
    //  Method:       incrementWaiting (Non Static)
    // 
    //  Description:  Increments the number of waiting threads. 
    //
    //  Parameters:   None
    //
    //  Returns:      N/A 
    //
    //**************************************************************
	public void incrementWaiting() {
		synchronized (this) {
			waiting++;
		}
	}// end incrementWaiting method
	
    //***************************************************************
    //
    //  Method:       decrementWaiting (Non Static)
    // 
    //  Description:  Decrements the number of waiting threads. 
    //
    //  Parameters:   None
    //
    //  Returns:      N/A 
    //
    //**************************************************************
	public void decrementWaiting() {
		synchronized (this) {
			waiting--;
		}
	}// end decrementWaiting method

    //***************************************************************
    //
    //  Method:       freeAll (Non Static)
    // 
    //  Description:  Frees all waiting threads from the barrier and prevents
    //                threads using this barrier from entering into a waiting state. 
    //
    //  Parameters:   None
    //
    //  Returns:      N/A 
    //
    //**************************************************************
	@Override
	public void freeAll() {
		synchronized(this) {
			lifted = true;
			notifyAll();
		}
	}// end freeAll method

}// end BarrierImpl class