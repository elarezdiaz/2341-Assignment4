import java.util.LinkedList;

public class SeparateChainingHashST {
    private final int M;
    private final LinkedList<String>[] hashTable;
    private final HashFunction hashFunc;
    private int comparisons;

    // constructor
    public SeparateChainingHashST(int M, HashFunction hashFunc) {
        this.M = M;
        this.hashFunc= hashFunc;
        this.hashTable = new LinkedList[M];
        // creates buckets at each index
        for (int i = 0; i < M; i++) {
            hashTable[i] = new LinkedList<>();
        }
    }

    // standard insert method
    public void insert(String key) {
        int index = hashFunc.hash(key, M);
        hashTable[index].add(key);
  }

     // standard search method
    public boolean search(String key) {
        int index = hashFunc.hash(key, M);
        comparisons = 0;
        for (String s : hashTable[index]) {
            comparisons++;
            if (s.equals(key)) { return true; }
        }
        return false;
    }

    // getter for comparisons
    public int getComparisons() {
        return comparisons;
    }
}
