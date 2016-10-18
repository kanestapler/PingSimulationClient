public class Client {

	public static void main(String[] args) {
		final int NUMBER_OF_PINGS = 10;
		
		for (int i=0; i<NUMBER_OF_PINGS; i++){

			Thread thr = new PingThread(i);
			thr.start();

		}
	}

}