package com.toorosystems.interview.prep;

import java.util.*;

public class Solution {
    /*
    Assuming an axis with STARTING_POSITION to ENDING_POSITION (e.g. 1 to 20)
    Given a list of inputs where
        - inputA: index 3 points to position 7
        - inputB: index 5 points to position 1
        - inputC: index 9 points to position 17
        - inputD: index 17 points to position 3
        - expresses as a string: "3,7   5,1   9,17  17,3"

    Find the final position if a given input e.g. What is the index of position 9?

    input: "3,7   5,1   9,17   17,3"
    question:  where is the final destination of position 5?
     */
    public static void main(String[] args) {
        // 0. create map of 1 to 20
        // 1. apply all redirects/inputs
        // 2. determine final destination $position
        final int STARTING_POSITION = 1;
        final int ENDING_POSITION = 20;

        // 0. populate map
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = STARTING_POSITION-1; i < ENDING_POSITION; i++) {
            map.put(i, null);
        }

        // 1a. given input: "3,7   5,1   9,17"
        // 1b. given input: "5,6   11,19   19,20"
        // 1c. given input: "18,21"
        //final String input = "3,7   5,1  7,17  9,17  17,3";
        String[] inputs = {"3,7   5,1   9,17",
                "5,6   11,19   19,20",
                "19,11",
                // "19,11  7,17",
                "3,7   5,1  9,17  17,3"};
        String source = "9";
        applyFunction(map, inputs);
        System.out.println("Input=" + source);
        int destination = find(map, source);
        System.out.println("Final destination =>" + destination);
    }

    private static void applyFunction(Map<Integer, Integer> map, String[] inputs){
        Arrays.stream(inputs).forEach(input -> {
            String[] pairs = input.split("\\s+");
            for (String entry : pairs){
                int source = Integer.parseInt(entry.split(",")[0]);
                int destination = Integer.parseInt(entry.split(",")[1]);

                if(source >= map.size()){
                    System.out.println("IndexOutOfBounds. Source=" + source + " point to a destination=" + destination +" that is out of bounds. Skipping ... ");
                    continue;
                }
                else if(map.get(source-1) == null){
                    map.put(source-1, destination);
                }
            }
        });
    }
    // 2. determine final position of 5
    public static int find(Map<Integer, Integer> map, String source){
        int key = Integer.parseInt(source);
        Set<Integer> visitedKeys = new HashSet<>();
        while (map.get(key-1) != null){
            if(visitedKeys.contains(map.get(key-1))){
                System.out.println("Found a circular reference at position " + key + ". Can't continue ");
                break;
            }
            else {
                key = map.get(key-1);
                visitedKeys.add(key);
            }
        }
        return key;
    }
}
