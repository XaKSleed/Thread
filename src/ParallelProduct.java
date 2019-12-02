import java.util.ArrayList;

public class ParallelProduct {
    private int threadCount;
    private UsualMatrix aMtr;
    private UsualMatrix bMtr;
    private myThread[] threads;
    private UsualMatrix resMtr;

    public ParallelProduct(int count, UsualMatrix a, UsualMatrix b){
        threadCount = count;
        threads = new myThread[count];
        aMtr = a;
        bMtr = b;
        resMtr =  new UsualMatrix(aMtr.getRows(), bMtr.getColumns());
    }

    public class myThread extends Thread{
        private int stringIndex;
        private int counter;

        public myThread(int number, int str){
            stringIndex = number;
            counter = str;
        }
        public void run(){

            while(stringIndex <= counter){
                for(int i = 0; i < aMtr.getColumns(); i++){
                    int value = 0;
                    for(int j = 0; j < bMtr.getColumns(); ++j){
                        value += aMtr.getElement(stringIndex, j)*bMtr.getElement(j,i);
                        resMtr.setElement(stringIndex, i, value);
                    }
                }
                stringIndex++;
            }
        }
    }

    public UsualMatrix product(){
        int k = 0;
        int i = 0;
        int n = bMtr.getRows()/threadCount;
        while(i < bMtr.getRows()){
            if(k == threadCount&&(bMtr.getRows()%threadCount!=0)){
                k = k - threadCount;
                threads[k] = new myThread(i, (bMtr.getRows()-1));
            }else
                threads[k] = new myThread(i, (i+n-1));
            threads[k].start();
            i+=n;
            k++;
        }
        for(i = 0; i < threads.length; i++){
            if(threads[i] != null){
                try{
                    threads[i].join();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        return resMtr;
    }

}
