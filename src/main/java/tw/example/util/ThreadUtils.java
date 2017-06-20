package tw.example.util;

public class ThreadUtils {

	/**
	 * Let the running thread sleep for a while silently (ignore the
	 * interrupted exception.
	 *
	 * @param time the time to sleep in milliseconds
	 */
	public static void sleepSilently(final long time) {
		try {
			Thread.sleep(time);
		}
		catch (InterruptedException e) {}
	}
}
