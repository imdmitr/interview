package com.interview.others;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/discuss/interview-question/877624/
 * 
 * @author imdmi
 *
 */
public class FindDistinctSubstrings {

    public static void main(String[] args) {
        System.out.println(distinct("awaglk", 4));
        System.out.println(distinct("democracy", 5));
        System.out.println(distinct("wawaglknagagwunagkwkwagl", 4));
    }
    
    private static List<String> distinct(String inputString, int k) {
        List<String> result = new ArrayList<>();
        if (k == 1) {
            return result;
        }
        
        Map<Character, Integer> map = new HashMap<>();
        int currentSize = 0;
        char[] chars = inputString.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
            if (currentSize == k) {
                int newValue = map.get(chars[i - k]) - 1;
                if (newValue == 0) {
                    map.remove(chars[i - k]);
                } else {
                    map.put(chars[i - k], newValue);
                }
            } else {
                currentSize += 1;
            }
            
            if (map.size() == k - 1 && currentSize == k) {
                result.add(inputString.substring(i - k + 1, i + 1));
            }
        }
        return result;
    }
    
}
