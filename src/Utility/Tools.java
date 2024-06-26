package Utility;

public class Tools {
    public static void wait(int sec) {

        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int randomGenerator(int number) {
        return (int) (Math.random() * number);
    }
}
