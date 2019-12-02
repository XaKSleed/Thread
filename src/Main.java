import java.util.concurrent.ExecutionException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        /*UsualMatrix aMtrx = new UsualMatrix(1000,1000);
        aMtrx.randMatrix(2);
        //System.out.println(aMtrx);
        UsualMatrix bMtrx = new UsualMatrix(1000,1000);
        bMtrx.randMatrix(2);
        //System.out.println(bMtrx);
        UsualMatrix oneResult;


        long startTime = System.currentTimeMillis();
        oneResult = aMtrx.MatrixProduct(bMtrx);
        long estimatedTime = System.currentTimeMillis() - startTime;
        //System.out.println(oneResult);
        System.out.println("обычно умножается за время: " + estimatedTime + " ms");




        for(int i = 2; i <= 10; i++){
            ParallelProduct test1 = new ParallelProduct(i, aMtrx, bMtrx);
            startTime = System.currentTimeMillis();
            test1.product();
            estimatedTime = System.currentTimeMillis() - startTime;
            System.out.println(i +" " + estimatedTime + " ms");

        }*/

        ArrayList<Integer> arr1 = new ArrayList<>();
        long startTime;
        int num;
        int min = 1;
        int max = 10;
        for (int i = 0; i < 1000; i++) {
            num = (int) (Math.random() * ++max) + min;
            arr1.add(num);
        }

        FindeOdd finde1;

        finde1 = new FindeOdd(arr1, 1);
        startTime = System.nanoTime();
        finde1.calcOddNum();
        int res = finde1.getOddCount();
        finde1.remove();
        System.out.println("Однопоточный поиск: кол во нечетных —" + res + " время: " + (System.nanoTime() - startTime));

        for (int i = 2; i <= 4; i++) {
            finde1 = new FindeOdd(arr1, i);
            startTime = System.nanoTime();
            finde1.calcOddNum();
            res = finde1.getOddCount();
            finde1.remove();
            System.out.println("Многопоточный поиск: кол во нечетных —" + res + " время: " + (System.nanoTime() - startTime));
        }
    }
}
