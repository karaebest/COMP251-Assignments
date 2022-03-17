import java.io.IOException;
import java.util.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;
import org.knowm.xchart.*;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class ProofProjectKara {
    
    public static int keyInTable; 
    // THE FOLLOWING ARE HELPER METHODS TO GENERATE RANDOM INSTANCES OF THE PROBLEM: 


    // generates a random integer between 0(inclusive) and 500(exclusive)
    public static int generateRandomKey() {
        Random rand = new Random(); 
        int k = rand.nextInt(500);
        return k;
    }

    //generates a hash table filled with n random keys
    public static HashTable generateRandomTable(int n){ 

        HashTable hash = new HashTable(n);
        int[] keys = new int[n];
        //randomly select an index from the array of keys
        Random rand = new Random();
        int grabKey = rand.nextInt(n);						


        for(int i=0; i<n; i++){
            keys[i] = generateRandomKey();
            //store the key at the randomly selected index to ensure half of the searches are successful when timing the samples
            if(grabKey==i)  keyInTable = keys[i];       
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


    // runs the search algorithm on a series of samples and outputs a chart of the runtime
    public static void main(String[] args) {
        // number of sample executions
        int samples = 100;
        double[] inputSize = new double[samples];
        double[] execution_times = new double[samples];
        int key;
        HashTable test;
        int numKeys = 0;

        for (int i=0; i<samples; i++) {
            //increase number of keys by 3 for each sample     
            numKeys += 3;                           
            test = generateRandomTable(numKeys);
            if(i%2==0){  
                //search for a key that is known to be in the table test 
                //to ensure successful search for at least half the timed searches                           
                key = keyInTable;
            }else{
                //search for randomly generated key for the other half
                key = generateRandomKey();
            }
            execution_times[i] = runSearch(key, test);
            inputSize[i] = numKeys;
        }

        // create subarrays to omit loop iterations that were not optimized
        double[] exec = Arrays.copyOfRange(execution_times, 3, 95);
        double[] size = Arrays.copyOfRange(inputSize, 3, 95);
        // create chart
        XYChart chart = QuickChart.getChart("Execution Time of Search", "Number of Keys", "Execution Time (us)", "Search Runtime", size, exec);
        
        double[] ones = new double[92];
        for(int d=0; d<ones.length; d++){
            ones[d]=1;
        }
        //add reference O(1)
        chart.addSeries("1", size, ones).setMarker(SeriesMarkers.NONE); 
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
