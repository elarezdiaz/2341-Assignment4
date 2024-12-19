public class ValidatePassword {
    public static String validatePassword(String password, SeparateChainingHashST[] scHashTable, LinearProbingHashST[] lpHashTable) {
        if (password.length() < 8) { return "Weak password. Must be at least 8 characters"; }

        // checks if password is a word in dictionary
        for (SeparateChainingHashST scht : scHashTable) {
            if (scht.search(password)) {
                return "Weak password. Is a dictionary word";
            }
            // checks if password matches dictionary word non-case-sensitive and digit at end for separate chaining
            if (password.matches("^[a-zA-Z0-9]+\\d$") && scht.search(password.substring(0, password.length() - 1))) {
                return "Weak password. Is a dictionary word with digit";
            }
        }
        // checks if password matches dictionary word
        for (LinearProbingHashST lpht : lpHashTable) {
            if (lpht.search(password)) { return "Weak password. Is a dictionary word"; }
            // checks if password matches dictionary word non-case-sensitive and digit at end for linear probing
            if (password.matches("^[a-zA-Z0-9]+\\d$") && lpht.search(password.substring(0, password.length() - 1))) {
                return "Weak password. Is a dictionary word with digit";
            }
        }
        return "Strong password.";
    }

    // logic for old hash code
    public static int oldHash(String str, int M) {
        int hash = 0;
        int skip = Math.max(1, str.length() / 8);
        for (int i = 0; i < str.length(); i++) {
            hash = (hash * 37 + str.charAt(i)) % M;
        }
        return Math.abs(hash);
    }

    // logic for new hash code
    public static int newHash(String str, int M) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash * 31 + str.charAt(i)) % M;
        }
        return Math.abs(hash);
    }
}
