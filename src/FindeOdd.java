import java.util.*;

public class FindeOdd {
    private int numberOfThreads;
    private Threads[] threads;
    private ArrayList<Integer> array;
    private int oddCount;


    FindeOdd(ArrayList<Integer> arr, int num) {
        threads = new Threads[num];
        array = arr;
        numberOfThreads = num;
        oddCount = 0;
    }

    public int getOddCount() {
        return oddCount;
    }

    public void remove() {
        oddCount = 0;
    }

    public class Threads extends Thread {
        private int startInd;
        private int endInd;
        private int res;


        Threads(int first, int second) {
            startInd = first;
            endInd = second;
        }

        @Override
        public void run() {
            while (startInd <= endInd) {
                if (array.get(startInd) % 2 != 0) {

                    res++;
                }
                startInd++;
            }

        }

        public int getRes() {
            return res;
        }
    }

    public void calcOddNum() {

        int k = 0;
        int parts;
        int ind = 0;

        parts = array.size() / numberOfThreads;

        while (ind < array.size()) {
            if (k == (numberOfThreads - 1) && (array.size() % numberOfThreads) != 0) {
                threads[k] = new Threads(ind, (array.size() - 1));
                ind = array.size();
            } else {
                threads[k] = new Threads(ind, (ind + parts - 1));
                ind += parts;
            }
            threads[k].start();
            k++;
        }
        for (int i = 0; i < numberOfThreads; i++) {
            if (threads[i] != null) {
                try {
                    threads[i].join();
                    oddCount += threads[i].getRes();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
