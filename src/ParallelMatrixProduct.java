
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelMatrixProduct {
    private int threadCount;
    private UsualMatrix aMtr;
    private UsualMatrix bMtr;
    private UsualMatrix resMtr;

    public ParallelMatrixProduct(int count, UsualMatrix a, UsualMatrix b){
        threadCount = count;
        aMtr = a;
        bMtr = b;
        resMtr =  new UsualMatrix(aMtr.getRows(), bMtr.getColumns());
    }

    public UsualMatrix parallelProduct() throws InterruptedException{
        ExecutorService exServ = Executors.newFixedThreadPool(this.threadCount);
        for(int i = 0; i < aMtr.getColumns(); i++) {
            exServ.execute(new myThread(i));
        }
            exServ.shutdown();
            /*exServ.awaitTermination(1, TimeUnit.SECONDS);*/
            return this.resMtr;

    }
    private class myThread implements Runnable{
        private int stringIndex;

        public myThread(int index) {

            this.stringIndex = index;
        }
        @Override
        public void run(){
            int counter = 0;
            for(int j = 0; j < bMtr.getColumns(); j++){
                for(int k = 0; k < aMtr.getColumns(); k++){
                    counter = counter+aMtr.getElement(this.stringIndex, k)*bMtr.getElement(k, j);
                }
                resMtr.setElement(this.stringIndex, j, counter);
                counter = 0;
            }
        }
    }
}
