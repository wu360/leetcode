//给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。若其中有多个可行的答案，则返回答案中字典序最小的单词。 
//
// 若无答案，则返回空字符串。 
//
// 示例 1: 
//
// 
//输入: 
//words = ["w","wo","wor","worl", "world"]
//输出: "world"
//解释: 
//单词"world"可由"w", "wo", "wor", 和 "worl"添加一个字母组成。
// 
//
// 示例 2: 
//
// 
//输入: 
//words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
//输出: "apple"
//解释: 
//"apply"和"apple"都能由词典中的单词组成。但是"apple"得字典序小于"apply"。
// 
//
// 注意: 
//
// 
// 所有输入的字符串都只包含小写字母。 
// words数组长度范围为[1,1000]。 
// words[i]的长度范围为[1,30]。 
// 
//

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution720 {
    public String longestWord(String[] words) {
        Arrays.sort(words);
        Set<String> set = new HashSet<>();
        String res = "";
        for (String word : words) {
            int len = word.length();
            if (len == 1 || set.contains(word.substring(0, len - 1))) {
                res = len > res.length() ? word : res;
                set.add(word);
            }
        }
        return res;
    }

    @Test
    public void test() {
        String[] words = {"a", "banana", "app", "appl", "ap", "apply","apply3", "apple"};
        String res = longestWord(words);
        System.out.println(res);
    }
}