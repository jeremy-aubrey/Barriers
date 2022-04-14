
public class BarrierImpl implements Barrier {

	private int limit;
	private int waiting;
	
	public BarrierImpl(int limit) {
		this.limit = limit;
	}
	
	@Override
	public void waitForOthers() {
		incrementWaiting();
		if(waiting < limit) {
			beginWait();
		} else if(waiting == limit || limit == 0) {
			endAllWait();
		}
	}
	
	public void beginWait() {
		synchronized(this) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void endAllWait() {
		synchronized(this) {
			notifyAll();
			waiting = 0;
		}
	}
	
	public void incrementWaiting() {
		synchronized (this) {
			waiting++;
		}
	}

	@Override
	public void freeAll() {
		synchronized(this) {
			limit = 0;
			notifyAll();
		}
	}
}
