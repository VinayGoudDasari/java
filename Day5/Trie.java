package Day7;

class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    // Constructor
    public TrieNode() {
        children = new TrieNode[26]; // Assuming only lowercase English letters a-z
        isEndOfWord = false;
    }
}

public class Trie {
    private TrieNode root;

    // Constructor
    public Trie() {
        root = new TrieNode();
    }

    // Method to insert a word into the Trie
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    // Method to check if there is any word in the Trie that starts with the given prefix
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return true;
    }

    // Main method to test the Trie implementation
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        trie.insert("app");
        trie.insert("apply");

        System.out.println("Does any word start with 'app'? " + trie.startsWith("app")); // true
        System.out.println("Does any word start with 'apl'? " + trie.startsWith("apl")); // false
        System.out.println("Does any word start with 'appl'? " + trie.startsWith("appl")); // true
        System.out.println("Does any word start with 'apple'? " + trie.startsWith("apple")); // true
        System.out.println("Does any word start with 'apples'? " + trie.startsWith("apples")); // false
    }
}
