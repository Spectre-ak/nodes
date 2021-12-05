
 
 //ref from: https://www.studytonight.com/advanced-data-structures/trie-data-structure-explained-with-examples
 
 public class Trie{
 
   public static void main(String[] args) {
     root = new TrieNode();
     
     Trie trie = new Trie();
     trie.insert("cat");
     trie.insert("car");
     trie.insert("dog");
     trie.insert("pick");
     trie.insert("pickle");
     trie.insert("akash");
     
     System.out.println(trie.search("cat"));
     System.out.println(trie.startsWith("pic"));
     System.out.println(trie.search("akash"));
     System.out.println("------------------------------------- - - - --- -----------------------------------------");
     
   }
 
   public void insert(String word) {
       TrieNode node = root;
       
       for (char c : word.toCharArray()) {
           if (node.children[c-'a'] == null) {
               node.children[c-'a'] = new TrieNode();
           }
           node = node.children[c-'a'];
       }
       node.isEndOfWord = true;
   }
 
   public boolean search(String word) {
       return isMatch(word, root, 0, true);
   }
 
   public boolean startsWith(String prefix) {
       return isMatch(prefix, root, 0, false);
   }
 
   public boolean isMatch( String s, TrieNode node, int index, boolean isFullMatch) {
       if (node == null)
           return false;
       
       if (index == s.length())
           return !isFullMatch || node.isEndOfWord;
       
       return isMatch(s, node.children[s.charAt(index) - 'a'], index + 1, isFullMatch);      
   }  
 
   static TrieNode root;
 
   static class TrieNode{
     boolean isEndOfWord;
     TrieNode children[];
     
     public TrieNode(){
         isEndOfWord = false;
         children = new TrieNode[26];
     }
   }
 
 
 
 }
