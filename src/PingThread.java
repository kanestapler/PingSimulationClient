import java.net.*;
import java.io.*;

public class PingThread extends Thread {
	private int threadNum;

	
    public PingThread(int val){
        threadNum = val;
    }
    
    public void Run(){
        System.out.println("Starting Thread Number: " + threadNum);
        try {
            Thread.sleep((long)(Math.random()*10000));
        }catch (InterruptedException e) { }
        System.out.println("Stopping Thread Number: " + threadNum);
    }
}
