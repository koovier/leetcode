/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 */
class Solution {
    private String findMostCommonWord(List<String> wordList) {
        int[][] table = new int[26][6];
        
        for (int i=0; i<wordList.size(); i++) {
            String word = wordList.get(i);
            for (int j=0; j<word.length(); j++) {
                table[word.charAt(j)-'a'][j]++;
            }
        }
        
        String mostCommonWord = null;
        int maxScore = 0;
        for (int i=0; i<wordList.size(); i++) {
            int score = 0;
            String word = wordList.get(i);
            for (int j=0; j<word.length(); j++) {
                score += table[word.charAt(j)-'a'][j];
            }
            
            if (maxScore < score) {
                maxScore = score;
                mostCommonWord = wordList.get(i);
            }
        }
        
        return mostCommonWord;
    }
    
    private List<String> removeInvalidWord(List<String> wordList, int match, String currentWord) {
        List<String> res = new ArrayList<>();
        
        for (int i=0; i<wordList.size(); i++) {
            if (match == pairMatch(currentWord, wordList.get(i))) {
                res.add(wordList.get(i));
            }
        }
        
        return res;
    }
    
    private int pairMatch(String word1, String word2) {
        int matchCount = 0;
        
        for (int i=0; i<6; i++) {
            if (word1.charAt(i) == word2.charAt(i)) {
                matchCount++;
            }
        }
        
        return matchCount;
    }
    
    
    public void findSecretWord(String[] wordlist, Master master) {
        List<String> wordList = Arrays.asList(wordlist);
        
        while(wordList.size()!=0) {
            String mostCommonWord = findMostCommonWord(wordList);
            int match = master.guess(mostCommonWord);
            
            if (6 == match) {
                return;
            } else {
                wordList = removeInvalidWord(wordList, match, mostCommonWord);
            }
        }
    }
}