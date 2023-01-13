package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;


public class Day1 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day1/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day1 day1 = new Day1();
        System.out.println("Part 1: " + day1.part1(list));
        System.out.println("Part 2: " + day1.part2(list));
    }

    private int part1(ArrayList<String> list) {
        int index = -1, x = 0, y = 0;
        int[][] dirs = new int[][] {{1,0}, {0,-1}, {-1,0}, {0,1}};
        String[] ins = list.get(0).split(",");
        for (String i : ins) {
            i = i.trim();
            char dir = i.charAt(0);
            int magnitude = Integer.valueOf(i.substring(1));
            index += dir == 'L' ? -1 : 1;
            index = (index + dirs.length) % dirs.length;
            x += dirs[index][0] * magnitude;
            y += dirs[index][1] * magnitude;
        }
        return Math.abs(x) + Math.abs(y);
    }

    private int part2(ArrayList<String> list) {
        HashSet<Integer> set = new HashSet<>();
        int index = -1, x = 0, y = 0;
        int[][] dirs = new int[][] {{1,0}, {0,-1}, {-1,0}, {0,1}};
        String[] ins = list.get(0).split(",");
        outer: for (String i : ins) {
            i = i.trim();
            char dir = i.charAt(0);
            index += dir == 'L' ? -1 : 1;
            index = (index + dirs.length) % dirs.length;
            int magnitude = Integer.valueOf(i.substring(1));
            for (int a = 0; a < magnitude; a++) {
                x += dirs[index][0];
                y += dirs[index][1];
                if (set.contains(y * 5_000 + x)) {
                    break outer;
                }
                set.add(y * 5_000 + x);
            }
        }
        return Math.abs(x) + Math.abs(y);
    }
}
