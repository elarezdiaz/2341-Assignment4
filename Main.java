import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {
    // values don't change
    private static final int M_SCHT = 1000;
    private static final int M_LPHT = 2000;
    private static final String DICTIONARY = "https://www.mit.edu/~ecprice/wordlist.10000";

    public static void readDictionary(SeparateChainingHashST[] schTables, LinearProbingHashST[] lphTables) throws Exception {
        URL url = new URL(DICTIONARY);
        // read from dictionary url
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String str;
            while ((str = br.readLine()) != null) {
                str = str.trim();
                for (SeparateChainingHashST scht : schTables) {
                    scht.insert(str);
                }
                for (LinearProbingHashST lpht : lphTables) {
                    lpht.insert(str);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        SeparateChainingHashST sctOldHash = new SeparateChainingHashST(M_SCHT, ValidatePassword::oldHash);
        SeparateChainingHashST sctNewHash = new SeparateChainingHashST(M_SCHT, ValidatePassword::newHash);
        LinearProbingHashST lptOldHash = new LinearProbingHashST(M_LPHT, ValidatePassword::oldHash);
        LinearProbingHashST lptNewHash = new LinearProbingHashST(M_LPHT, ValidatePassword::newHash);

        SeparateChainingHashST[] schTables = {sctOldHash, sctNewHash};
        LinearProbingHashST[] lphTables = {lptOldHash, lptNewHash};

        System.out.println("Downloading dictionary. This may take a moment...");
        readDictionary(schTables, lphTables);
        System.out.println("Dictionary download is complete.");

        String[] passwordsToCheck = {
                "account8",
                "accountability",
                "9a$D#qW7!uX&Lv3zT",
                "B@k45*W!c$Y7#zR9P",
                "X$8vQ!mW#3Dz&Yr4K5"
        };

        for (String pw : passwordsToCheck) {
            System.out.println("Checking password: " + pw);
            for (int i = 0; i < schTables.length; i++) {
                String evaluation = ValidatePassword.validatePassword(pw, new SeparateChainingHashST[]{schTables[i]}, new LinearProbingHashST[]{lphTables[i]});
                System.out.println(" " + (i == 0 ? "Old" : "New") + " SC Hash: " + evaluation + ", Comparisons: " + schTables[i].getComparisons());
                System.out.println(" " + (i == 0 ? "Old" : "New") + " LP Hash: " + evaluation + ", Comparisons: " + lphTables[i].getComparisons());
            }
            System.out.println();
        }
    }
}
