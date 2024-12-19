public class LinearProbingHashST {
    private final String[] hashTable;
    private final int M;
    private final HashFunction hashFunc;
    private int comparisons;

    // constructor
    public LinearProbingHashST(int M, HashFunction hashFunc) {
        this.M = M;
        this.hashFunc = hashFunc;
        this.hashTable = new String[M];
    }

    // standard insert method
    // inserts in next available index spot if needed
    public void insert(String key) {
        int index = hashFunc.hash(key, M);
        while (hashTable[index] != null) {
            index = (index + 1) % M;
        }
        hashTable[index] = key;
    }

    // standard search method
    public boolean search(String key) {
        int index = hashFunc.hash(key, M);
        while (hashTable[index] != null) {
            comparisons++;
            if (hashTable[index].equals(key)) {
                return true;
            }
            index = (index + 1) % M;
        }
        return false;
    }

    // getter for comparisons
    public int getComparisons() {
        return comparisons;
    }
}
