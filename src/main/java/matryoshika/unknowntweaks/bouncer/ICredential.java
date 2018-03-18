package matryoshika.unknowntweaks.bouncer;

public interface ICredential {
	
	public boolean hasDied();
	public long diedAt();
	public void setDied(long tod);
	
	public long timePassed();
	
	public void tryGreenlight();

}
