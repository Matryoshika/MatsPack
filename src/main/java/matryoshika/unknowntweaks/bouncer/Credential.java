package matryoshika.unknowntweaks.bouncer;

import matryoshika.unknowntweaks.config.ConfigHandler;

public class Credential implements ICredential{
	
	private long time = 0L;

	@Override
	public boolean hasDied() {
		return time > 0L;
	}
	
	@Override
	public void setDied(long tod) {
		time = tod;
	}

	@Override
	public long timePassed() {
		
		long current = System.currentTimeMillis();
		return current - time;
	}

	@Override
	public void tryGreenlight() {
		long limit = (long) (ConfigHandler.deathCooldown * 3600000);
		if(timePassed() >= limit) 
			time = 0;
			
	}

	@Override
	public long diedAt() {
		return time;
	}

}
