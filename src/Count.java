public class Count implements Runnable {
    public int data = 0;

    @Override
    public void run() {
        synchronized (this){
            for (int i = 0; i < 100000; i++) {
                data++;
            }
        }
    }
}