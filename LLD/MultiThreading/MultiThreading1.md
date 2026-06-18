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
- Every thread in java has its own memory(local variables)
- Shared heap memory(objects are present here)