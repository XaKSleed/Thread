
class MatrixThread extends Thread {
    private int sstart, finish;
    private UsualMatrix first, second, resultMatrix;
    private Parallel product;

    MatrixThread(int[] array, UsualMatrix firstMatrix, UsualMatrix secondMatrix, UsualMatrix resultMatrix, Parallel product) {
        this.resultMatrix = resultMatrix;
        first = firstMatrix;
        second = secondMatrix;
        this.product = product;
        sstart = array[0];
        finish = array[1];
    }

    public void run() {
        int counter = 0;
        Object obj = new Object();
        for (int i = sstart; i < finish; i++) {
            for (int j = 0; j < second.getColumns(); j++) {
                synchronized(this){
                    for (int k = 0; k < first.getColumns(); k++) {
                        counter += first.getElement(i, k) * second.getElement(k, j);
                    }
                    resultMatrix.setElement(i, j, counter);
                    counter = 0;
                }
            }
        }
        product.counter++;
    }

}

public class Parallel {
    private int threadCount;
    int counter = 0;
    private MatrixThread[] threadArray;

    public int getThreadCount(){return threadCount;}

    Parallel(int threadCount){
        this.threadCount = threadCount;
        threadArray = new MatrixThread[threadCount];
    }

    UsualMatrix product(UsualMatrix firstMatrix, UsualMatrix secondMatrix) throws Exception{
        if(firstMatrix.getRows() != secondMatrix.getColumns())
            throw new Exception("width != height");


        UsualMatrix newMatrix = new UsualMatrix(firstMatrix.getRows(), secondMatrix.getColumns());

        int[] array = new int[2];
        int cnt = firstMatrix.getRows() / threadCount;
        for(int i = 0 ;i < threadCount; i++){
            array[0] = i * cnt;
            array[1] = array[0] + cnt;
            threadArray[i] = new MatrixThread(array, firstMatrix, secondMatrix, newMatrix, this);
            threadArray[i].start();
        }
        if(firstMatrix.getRows() % threadCount != 0) {
            array[0] = firstMatrix.getRows() - (firstMatrix.getRows() % threadCount);
            array[1] = firstMatrix.getRows();
            MatrixThread thread = new MatrixThread(array, firstMatrix, secondMatrix, newMatrix, this);
            this.counter--;
            thread.start();
        }

        for(;;){
            Thread.sleep(5);
            if(counter == threadCount) {
                counter = 0;
                break;
            }
        }
        return newMatrix;
    }
}
