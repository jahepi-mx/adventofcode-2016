package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day6 {
    
    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day6/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day6 day = new Day6();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private String part1(ArrayList<String> list) {
        String res = "";
        int[][] hashes = new int[list.get(0).length()][255];
        int[] maxes = new int[hashes.length];
        int[] chars = new int[hashes.length];
        for (String line : list) {
            for (int a = 0; a < line.length(); a++) {
                char ch = line.charAt(a);
                hashes[a][ch]++;
                if (hashes[a][ch] > maxes[a]) {
                    maxes[a] = hashes[a][ch];
                    chars[a] = ch;
                }
            }
        }
        for (int ch : chars) {
            res += (char) ch;
        }
        return res;
    }

    private String part2(ArrayList<String> list) {
        String res = "";
        int[][] hashes = new int[list.get(0).length()][255];
        int[] mins = new int[hashes.length];
        int[] chars = new int[hashes.length];
        Arrays.fill(mins, Integer.MAX_VALUE);
        for (String line : list) {
            for (int a = 0; a < line.length(); a++) {
                char ch = line.charAt(a);
                hashes[a][ch]++;
            }
        }
        for (int b = 0; b < hashes.length; b++) {
            for (int a = 0; a < hashes[b].length; a++) {
                if (hashes[b][a] > 0 && hashes[b][a] < mins[b]) {
                    mins[b] = hashes[b][a];
                    chars[b] = a;
                }
            }
        }
        for (int ch : chars) {
            res += (char) ch;
        }
        return res;
    }
}
