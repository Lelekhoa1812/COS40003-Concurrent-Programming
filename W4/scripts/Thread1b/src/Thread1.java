
public class Thread1 extends Thread {

	public void run(){
		System.out.println(Thread.currentThread().getName() + " initiated");
		
		PrintNumbers.printNumbers();
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Main Thread Id: "+ Thread.currentThread().getId());

		
		System.out.println("Range of Priority: from "+Thread.MIN_PRIORITY + " to "+Thread.MAX_PRIORITY);

		
		Thread myThreads[] = new Thread[10];
		for (int i=0; i<10; i++){
			myThreads[i] = new Thread1();
			myThreads[i].setName("T"+i);
			//set priority here
			myThreads[i].start();			
		}
		
		
		PrintNumbers.printNumbers();
		
		for (int i=0; i<10; i++)//waiting for all threads to die
			try{ 		
				myThreads[i].join();
			} catch(InterruptedException e){}
		
		
	}
}
