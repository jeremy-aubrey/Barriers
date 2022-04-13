
public interface Barrier {
	
	public void waitForOthers();
	public void freeAll();
	
	//remove
	public int getWaiting();
}
