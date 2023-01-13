package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day2 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day2/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day2 day2 = new Day2();
        System.out.println("Part 1: " + day2.part1(list));
        System.out.println("Part 2: " + day2.part2(list));
    }

    private int part1(ArrayList<String> list) {
        int res = 0;
        int[][] numpad = new int[][] {{1, 2, 3},{4, 5, 6},{7, 8, 9}};
        int[][] dirs = new int[][] {{1,0}, {0,1}, {-1,0}, {0,-1}};
        String dirsStr = "RULD";
        int x = 1, y = 1;
        for (String line : list) {
            for (char ch : line.toCharArray()) {
                int[] dir = dirs[dirsStr.indexOf(ch)];
                int tx = x + dir[0];
                int ty = y + dir[1];
                if (tx >= 0 && tx <= 2 && ty >= 0 && ty <= 2) {
                    x = tx;
                    y = ty;
                }
            }
            res *= 10;
            res += numpad[2 - y][x];
        }
        return res;
    }

    private String part2(ArrayList<String> list) {
        char[][] numpad = new char[][] {
            {'0','0','1','0','0'},
            {'0','2','3','4','0'},
            {'5','6','7','8','9'},
            {'0','A','B','C','0'},
            {'0','0','D','0','0'},
        };
        int x = 0, y = 2;
        String res = "";
        int[][] dirs = new int[][] {{1,0}, {0,1}, {-1,0}, {0,-1}};
        String dirsStr = "RULD";
        for (String line : list) {
            for (char ch : line.toCharArray()) {
                int[] dir = dirs[dirsStr.indexOf(ch)];
                int tx = x + dir[0];
                int ty = y + dir[1];
                if (tx >= 0 && tx <= 4 && ty >= 0 && ty <= 4 && numpad[4 - ty][tx] != '0') {
                    x = tx;
                    y = ty;
                }
            }
            res += numpad[4 - y][x];
        }
        return res;
    }
}
