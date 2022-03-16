import java.io.*;
import java.util.*;

public class Chaining {
    public int m; // number of slots
    public int n; // number of keys in table
    public LinkedList<Integer>[] Table;

    public Chaining(int m){
        this.m = m;
        this.Table = new LinkedList<Integer>[m];
        this.load = 0;
        this.n = 0;
    }



    //inserts key in table, calls rehash if load factor over 0.5
    public void insertKey(Integer key){
        if((float)(n+1)/m > 0.5){      
            rehash();
        }
        int index = key%m;
        Table[index].addFirst(key);     //adds all keys to front of list to be compatible with successful search proof
        n++;         
    }

    public void rehash(int key){
        int oldSize = m;
		m = 2*m;            //double size of table
		n = 0;       
		LinkedList<Integer>[] temp = this.Table;
		
        this.Table = new LinkedList<Integer>[m];    //create new table double the size

		for(int i=0; i<oldSize; i++) {
			if(!temp[i].isEmpty()) {
				for(Integer k: temp[i]){        //insert all keys into new table
                    insertKey(k);
                }
			}
		}
    }


    /**Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions */
    public void insertKeyArray (int[] keyArray){
        for (int key: keyArray) {
            insertKey(key);
        }
    }
}
