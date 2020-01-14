import java.util.Locale;
import java.util.Random;
import java.util.concurrent.*;

public class Mean {
    static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(128);
    static double[] array;

    static void initArray(int size) {
        Random gen = new Random(2);
        array = new double[size];
        for(int i=0;i<size;i++){
            array[i] = gen.nextDouble() * size / (i + 1);
        }
    }
 
    public static void main(String[] args) {
        initArray(128000000);
        for(int cnt:new int[]{1,2,4,8,16,32,64,128}){
            parallelMean2(cnt);
        }
        // Wynik jest najlepszy dla liczby wątków zgodnej z fizyczną liczbą wątków procesora
    }

    static class MeanCalc extends Thread{
        private final int start;
        private final int end;
        final int length;
        double mean = 0;

        MeanCalc(int start, int end){
            this.start = start;
            this.end = Math.min(end, array.length);
            this.length = end - start;
        }

        public void run() {
            mean = 0.0;
            for (var i = start; i < end; i++)
                mean += array[i];
            mean /= length;

            try {
                results.put(mean);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf(Locale.US,"%d-%d mean=%f\n",start,end,mean);
        }
    }

    /**
     * Oblicza średnią wartości elementów tablicy array uruchamiając równolegle działające wątki.
     * Wypisuje czasy operacji
     * @param cnt - liczba wątków
     */
    static void parallelMean(int cnt){
        MeanCalc threads[] = new MeanCalc[cnt];
        // Bloki są równe poza ostatnim, który jest ograniczony do array.length (sprawdzane w konstruktorze MeanCalc)
        int range = (int)Math.ceil(array.length / (double)cnt);
        for (var i = 0; i < cnt; i++) {
            threads[i] = new MeanCalc(i*range, (i + 1)*range);
        }

        double t1 = System.nanoTime() / 1e6;
        for (var i = 0; i < cnt; i++)
            threads[i].start();
        double t2 = System.nanoTime() / 1e6;

        for (MeanCalc mc : threads) {
            try {
                mc.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        var mean = 0.0;
        for (var i = 0; i < cnt; i++) {
            mean += threads[i].mean  * ((double)threads[i].length / array.length);
        }
        double t3 = System.nanoTime() / 1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                mean);
    }

    static void parallelMean2(int cnt){
        MeanCalc threads[] = new MeanCalc[cnt];
        // Bloki są równe poza ostatnim, który jest ograniczony do array.length (sprawdzane w konstruktorze MeanCalc)
        int range = (int)Math.ceil(array.length / (double)cnt);
        for (var i = 0; i < cnt; i++) {
            threads[i] = new MeanCalc(i*range, (i + 1)*range);
        }

        double t1 = System.nanoTime() / 1e6;
        for (var i = 0; i < cnt; i++)
            threads[i].start();
        double t2 = System.nanoTime() / 1e6;

        double mean = 0.0, weight;
        for (var i = 0; i < cnt; i++) {
            // średnia ważona
            try {
                if (cnt - 1 == i)
                    weight = (double)(array.length - range * i) / array.length;
                else
                    weight = (double)range / array.length;
                mean += results.take() * weight;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        double t3 = System.nanoTime() / 1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                mean);
    }
}
