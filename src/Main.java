public class Main {

    /**
     * 加时赛锁
     */
    private final Object tieLock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Count count1 = new Count();
        Count count2 = new Count();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                new Main().getData(count1, count2);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                new Main().getData(count2, count1);
            }
        });
        thread1.start();
        thread2.start();
    }


    public void getData(Count count1, Count count2) {
        if (count1.hashCode() > count2.hashCode()) {
            synchronized (count1) {
                System.out.println("拿到了线程count1");
                synchronized (count2) {
                    System.out.println("拿到了线程count2");
                }
            }
        } else if (count2.hashCode() > count1.hashCode()) {
            synchronized (count2) {
                System.out.println("拿到了线程count2");
                synchronized (count1) {
                    System.out.println("拿到了线程count1");
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (count1) {
                    System.out.println("拿到了线程count1");
                    synchronized (count2) {
                        System.out.println("拿到了线程count2");
                    }
                }
            }
        }
    }

}
