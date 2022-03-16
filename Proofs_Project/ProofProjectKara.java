import java.io.IOException;
import java.util.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;
import org.knowm.xchart.*;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class ProofProjectKara {
    
    public static int keyInTable;  //for half the samples ensure it is successful search
    // THE FOLLOWING ARE HELPER METHODS TO GENERATE RANDOM INSTANCES OF THE PROBLEM: 


    // generates a random nonzero integer
    public static int generateRandomKey() {
        Random rand = new Random();        
        return rand.nextInt();
    }

    public static HashTable generateRandomTable(int n){ //n is number of keys

        HashTable hash = new HashTable(n);
        int[] keys = new int[n];
        Random rand = new Random();
        int grabKey = rand.nextInt(n);


        for(int i=0; i<n; i++){
            keys[i] = generateRandomKey();
            if(grabKey==i)  keyInTable = keys[i];       //to ensure successful search at least 1/2 of the time: save random key from table in static variable
        }

        hash.insertKeyArray(keys);
        return hash;
    }

    // runs the Bellman-Ford algoritm on a random graph
    // returns the time of execution in microseconds
    public static double runSearch(int key, HashTable table) {

        double start = System.nanoTime();
        table.search(key);
        double end = System.nanoTime();
        
        // execution time in microseconds
        double duration = (end - start) / 1000;  
        return duration;
    }


    // runs the Bellman-Ford algorithm on a series of samples and outputs a chart of the runtime
    public static void main(String[] args) {
        // number of sample executions
        int samples = 500;
        double[] inputSize = new double[samples];
        double[] execution_times = new double[samples];
        int key;
        HashTable test;
        int numKeys = 100;

        for (int i=0; i<samples; i++) {
            numKeys += 100;        //increase number of keys by 100 for each sample
            test = generateRandomTable(numKeys);
            if(i%2==0){
                key = generateRandomKey();
            }else{
                key = keyInTable;
            }
            execution_times[i] = runSearch(key, test);
            inputSize[i] = numKeys;
        }

        // create chart
        XYChart chart = QuickChart.getChart("Execution Time of Search", "Number of Keys", "Execution Time (us)", "Search Runtime", inputSize, execution_times);
        

        //add reference O(n)
        chart.addSeries("Input Size n", inputSize, inputSize).setMarker(SeriesMarkers.NONE); 
        // display chart
        //new SwingWrapper<>(chart).displayChart();
        
        // save chart
        try {
            BitmapEncoder.saveBitmapWithDPI(chart, "./Run_Time_Chart", BitmapFormat.PNG, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
