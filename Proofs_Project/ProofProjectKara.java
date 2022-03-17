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
        int k = rand.nextInt(500);
        return k;
    }

    public static HashTable generateRandomTable(int n){ //n is number of keys

        HashTable hash = new HashTable(n);
        int[] keys = new int[n];
        Random rand = new Random();
        int grabKey = rand.nextInt(n);			//to ensure saved key is randomly selected			


        for(int i=0; i<n; i++){
            keys[i] = generateRandomKey();
            if(grabKey==i)  keyInTable = keys[i];       //to ensure successful search at least 1/2 of the time: save random key from table in static variable
        }

        hash.insertKeyArray(keys);
        return hash;
    }

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
        int samples = 100;
        double[] inputSize = new double[samples];
        double[] execution_times = new double[samples];
        int key;
        HashTable test;
        int numKeys = 1;
        int[] k = new int[samples];

        for (int i=0; i<samples; i++) {     //trial and error, 500 iterations is what it takes to even out the optimization
        	//if(i>=samples) continue;
            numKeys += 2;        //increase number of keys by 100 for each sample
            test = generateRandomTable(numKeys);
            if(i%2==0){
                key = keyInTable;
            }else{
                key = generateRandomKey();
            }
            execution_times[i] = runSearch(key, test);
            inputSize[i] = numKeys;
            k[i] = key;
        }

        // for(int j=0; j<execution_times.length; j++){
        //     if(execution_times[j]>5){
        //         System.out.println("i: "+ j+" key:"+ k[j]+ " time:"+ execution_times[j]+ " size:"+ inputSize[j]);
        //     }
        // }
        double[] exec = Arrays.copyOfRange(execution_times, 2, execution_times.length);
        double[] size = Arrays.copyOfRange(inputSize, 2, inputSize.length);
        // create chart
        XYChart chart = QuickChart.getChart("Execution Time of Search", "Number of Keys", "Execution Time (us)", "Search Runtime", size, exec);
        
        double[] ones = new double[samples];
        for(int d=0; d<ones.length; d++){
            ones[d]=1;
        }
        //add reference O(n)
        chart.addSeries("Input Size n", inputSize, ones).setMarker(SeriesMarkers.NONE); 
        // display chart
        new SwingWrapper<>(chart).displayChart();
        
        // save chart
        try {
            BitmapEncoder.saveBitmapWithDPI(chart, "./Run_Time_Chart", BitmapFormat.PNG, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}