import java.util.*;



public class HashTable {
    public int m; // number of slots
    public int n; // number of keys in table
    public LinkedList<Integer>[] Table;

    public HashTable(int n){        //takes in number of keys so that can make sure n is always proportional to m
        this.m = n*2;
        this.Table = (LinkedList<Integer>[])new LinkedList<?>[m];
        this.n = 0;
    }

    //Insert key in table
    public void insertKey(Integer key){
        // if((float)(n+1)/m > 0.5){                   //if load factor above 0.5, resize table    
        //     rehash();
        // }
        int index = Math.abs(key%m);
        if(Table[index]==null) Table[index] = new LinkedList<>();
        Table[index].addFirst(key);                 //add key to beginning of linked list
        n++;         
    }

    //Insert array of keys
    public void insertKeyArray (int[] keyArray){
        for (int key: keyArray) {
            insertKey(key);
        }
    }

    //Resize table to maintain load factor under 0.5
    // public void rehash(){
    //     int oldSize = m;
	// 	m = 2*m;                                    //double size of table
	// 	n = 0;       
	// 	LinkedList<Integer>[] temp = this.Table;
		
    //     this.Table = (LinkedList<Integer>[])new LinkedList<?>[m];    //create new table double the size

	// 	for(int i=0; i<oldSize; i++) {
	// 		if(!temp[i].isEmpty()) {
	// 			for(Integer k: temp[i]){            //insert all keys into new table
    //                 insertKey(k);
    //             }
	// 		}
	// 	}
    // }

    //Search for key in table
    public boolean search(int key){
        int hash = Math.abs(key%m);                           

        if(Table[hash]==null || Table[hash].isEmpty()) return false;     //if list at hash is empty or null, search unsuccessful

        for(Integer k: Table[hash]){
            if(k.intValue()==key) return true;      
        }

        return false;                               //if all keys in list examined, search unsuccessful
    }

}
