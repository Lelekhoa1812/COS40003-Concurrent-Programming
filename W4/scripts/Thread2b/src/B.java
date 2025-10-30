
public class B extends Thread{

	//----- Modifications-------
	//add an array of integer here
	
	//add a constructor here. Constructor gets an integer array
	//and put it in the class.
	
	//Run should loop through the array to find the maximum
	
	public void run()
	{
		while(true)
		{
			System.out.println("This is Thread B");
			try{
				Thread.sleep(3000);
			}catch(InterruptedException e){}
		}
	}
}

