import java.util.*;

public class ProofProject_Kara {
    public int m; // number of slots
    public int n; // number of keys in table
    public LinkedList<Integer>[] Table;

    public ProofProject_Kara(int m){
        this.m = m;
        this.Table = new LinkedList<Integer>[m];
        this.load = 0;
        this.n = 0;
    }

    //Insert key in table
    public void insertKey(Integer key){
        if((float)(n+1)/m > 0.5){                   //if load factor above 0.5, resize table    
            rehash();
        }
        int index = key%m;
        Table[index].addFirst(key);                 //add key to beginning of linked list
        n++;         
    }

    //Resize table to maintain load factor under 0.5
    public void rehash(int key){
        int oldSize = m;
		m = 2*m;                                    //double size of table
		n = 0;       
		LinkedList<Integer>[] temp = this.Table;
		
        this.Table = new LinkedList<Integer>[m];    //create new table double the size

		for(int i=0; i<oldSize; i++) {
			if(!temp[i].isEmpty()) {
				for(Integer k: temp[i]){            //insert all keys into new table
                    insertKey(k);
                }
			}
		}
    }


    //Insert array of keys
    public void insertKeyArray (int[] keyArray){
        for (int key: keyArray) {
            insertKey(key);
        }
    }


    //Search for key in table
    public boolean search(int key){
        int hash = key%m;

        if(Table[hash].isEmpty()) return false;     //if list at hash is empty, search unsuccessful

        for(Integer k: Table[hash]){
            if(k.intValue()==key) return true;      
        }

        return false;                               //if all keys in list examined, search unsuccessful

    }

}
