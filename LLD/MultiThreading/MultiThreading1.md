How to create threads in JAVA? 
- TWO WAYS
 -- Extending Thread class
 -- Implementing Runnable interface(preferred)


class CounterTask implements Runnable{

    @Override
    public void run(){
        
    }
}


CounterTask task = new CounterTask();  //Now create a instance of this class

Thread thread = new Thread(task); //Now pass this to the constructor of thread class
thread.start(); // starts running on the new thread
// thread.run() does not create a new thread


When you start a java class a single thread is created and that is responsible for running the java program. 
As soon as thread.start() is executed a new thread will be spawned and it will start executing the run method
which we have overriden.


_________
How memory sharing happens??
- Every thread in java has its own stack memory(local variables, references and method calls)
- Shared heap memory(actual objects are present here, shared memory)


We should be very careful when using multithreading since threads share the same object memory and there can be anomalies

 Room with lock example. So that there isolation(among threads)

 synchronize - one thread can hold the lock at the time.



public class SharedCounter{
    private int counter;
    private final int LIMIT = 10;

    public SharedCounter(){
        this.counter = 0;
    }
    public print(){
        System.out.println(counter + " " + Thread.currentThread.getName());
        counter++;
    }
}

public class OddPrinter implements Runnable{

    private SharedCounter counter;

     public OddPrinter (SharedCounter counter){
        this.counter = counter;
     }
    
    @Override
    public void run(){
        // wrap inside try catch
        counter.print(1);
    }
}

public class EvenPrinter implements Runnable{
    private SharedCounter counter;

    public EvenPrinter(SharedCounter counter){
        this.counter = counter;
    }
    @Override
    public void run(){
        // wrap inside try catch

        counter.print(0);
    }
}




public class SharedCounter {
    private int counter;
    private final int LIMIT = 10;

    public SharedCounter() {
        this.counter = 0;
    }

    // expectedRemainder = 0 for even thread, 1 for odd thread
    public synchronized void print(int expectedRemainder) throws InterruptedException {
        while (counter < LIMIT) {
            while (counter % 2 != expectedRemainder && counter < LIMIT) {
                wait();
            }
            if (counter >= LIMIT) break;
            System.out.println(counter + " " + Thread.currentThread().getName());
            counter++;
            notifyAll();
        }
    }
}