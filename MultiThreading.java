package classWork;
/*Objectives of this exercise
 * How to make two threads work on a single object...
 * How to create two threads and assign two jobs
 * How to control threads
 */
public class MultiThreading {
	public static void main(String[] args) {
		Thread t=Thread.currentThread();
		t.setName("Nasima");
		System.out.println("Reservation counter starting working..."+t.getName());
		
		ReservationCounter central=new ReservationCounter();
		
		Thread Swetha=new Thread(new BookingJob(central,1000),"Swetha");
		Thread Doni=new Thread(new BookingJob(central,500),"Doni");
		
		Swetha.start();
		Doni.start();
	}
}
class BookingJob implements Runnable{
	ReservationCounter central;int amt;
	public BookingJob(ReservationCounter central,int amt) {
		this.central=central;
		this.amt=amt;
	}
	@Override
	public void run() {
		central.bookTicket(amt);
		central.giveChange();
	}
}
class ReservationCounter{
	int amt;
	public void bookTicket(int amt) {
		Thread t=Thread.currentThread();
		this.amt=amt;
		String name=t.getName();
		System.out.println(name+" has come to book the ticket...");
		System.out.println(name+" brought...:"+amt);
	}
	
	public void giveChange() {
		Thread t=Thread.currentThread();
		String name=t.getName();
		System.out.println("Change given to...:"+name);
		System.out.println(name+" takes...:"+(amt-100));
	}
}
