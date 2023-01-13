package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day9 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day9/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day9 day = new Day9();
        System.out.println("Part 1: " + day.part1(list.get(0)));
        System.out.println("Part 2: " + day.part2(list.get(0)));
    }

    private long part1(String str) {
        boolean isCmd = false;
        String cmd = "";
        long count = 0;
        for (int a = 0; a < str.length(); a++) {
            char ch = str.charAt(a);
            if (ch == '(') {
                isCmd = true;
            } else if (ch == ')') {
                int nChars = Integer.valueOf(cmd.split("x")[0]);
                int factor = Integer.valueOf(cmd.split("x")[1]);
                count += factor * nChars;
                a += nChars;
                cmd = "";
                isCmd = false;
            } else {
                cmd += isCmd ? ch : "";
                count += !isCmd ? 1 : 0;
            }
        }
        return count;
    }
    
    private long part2(String str) {
        boolean isCmd = false;
        String cmd = "";
        long count = 0;
        for (int a = 0; a < str.length(); a++) {
            char ch = str.charAt(a);
            if (ch == '(') {
                isCmd = true;
            } else if (ch == ')') {
                int nChars = Integer.valueOf(cmd.split("x")[0]);
                int factor = Integer.valueOf(cmd.split("x")[1]);
                count += factor * part2(str.substring(a + 1, a + 1 + nChars));
                a += nChars;
                cmd = "";
                isCmd = false;
            } else {
                cmd += isCmd ? ch : "";
                count += !isCmd ? 1 : 0;
            }
        }
        return count;
    }
}
