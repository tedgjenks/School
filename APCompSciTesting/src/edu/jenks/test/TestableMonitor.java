package edu.jenks.test;

public class TestableMonitor implements Runnable {
	
	private final ThreadGroup THREAD_GROUP;
	private final String THREAD_NAME;
	private Thread thread;
	private final long MAX_RUNNING_TIME_MILLIS;
	private final long SLEEP_MILLIS;
	private final boolean TEST_MODE;

	public TestableMonitor(ThreadGroup threadGroup, String threadName, long maxRunningTimeMillis, boolean testMode) {
		THREAD_GROUP = threadGroup;
		THREAD_NAME = threadName;
		MAX_RUNNING_TIME_MILLIS = maxRunningTimeMillis;
		SLEEP_MILLIS = 2;
		TEST_MODE = testMode;
	}
	
	public void start() {
		if(thread == null) {
			thread = new Thread(this, THREAD_NAME);
			thread.start();
		}
	}

	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		boolean threadsRunning = true;
		do {
			try {
				Thread.sleep(SLEEP_MILLIS);
				int activeCount = THREAD_GROUP.activeCount();
				threadsRunning = activeCount > 0;
			} catch (InterruptedException e) {
				e.printStackTrace(System.err);
			}
		} while(threadsRunning && (TEST_MODE || (System.currentTimeMillis() - startTime) < MAX_RUNNING_TIME_MILLIS));
		int activeCount = THREAD_GROUP.activeCount();
		if(activeCount > 0) {
			System.out.println(activeCount + " threads running at time expired in " + THREAD_NAME);
			THREAD_GROUP.stop();
		}
		System.out.println(THREAD_NAME + " monitor end.");
	}

	public Thread getThread() {
		return thread;
	}

}
