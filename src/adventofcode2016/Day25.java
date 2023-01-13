package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day25 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day25/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day25 day = new Day25();
        System.out.println("Part 1: " + day.part1(list));
    }

    private int part1(ArrayList<String> list) {
        int from = 0;
        out: while (true) {
            boolean isRunning = true;
            int[] mem = new int[26];
            mem[0] = from;
            int cursor = 0, prevBit = 0, count = 0;
            while (isRunning) {
                String ins = list.get(cursor);
                String[] parts = ins.split(" ");
                if (ins.indexOf("inc") >= 0) {
                    mem[parts[1].charAt(0) - 'a']++;
                    cursor++;
                } else if (ins.indexOf("dec") >= 0) {
                    mem[parts[1].charAt(0) - 'a']--;
                    cursor++;
                } else if (ins.indexOf("jnz") >= 0) {
                    char p1 = parts[1].charAt(0);
                    int x = p1 >= 'a' && p1 <= 'z' ?  mem[p1 - 'a'] : Integer.valueOf(parts[1]);
                    char p2 = parts[2].charAt(0);
                    int y = p2 >= 'a' && p2 <= 'z' ?  mem[p2 - 'a'] : Integer.valueOf(parts[2]);
                    cursor += x != 0 ? y : 0;
                    cursor += x == 0 ? 1 : 0;
                    isRunning = cursor < 0 || cursor >= list.size() ? false : isRunning;
                } else if (ins.indexOf("cpy") >= 0) {
                    char p1 = parts[1].charAt(0);
                    char p2 = parts[2].charAt(0);
                    int value1 = p1 >= 'a' && p1 <= 'z' ?  mem[p1 - 'a'] : Integer.valueOf(parts[1]);
                    if (p2 >= 'a' && p2 <= 'z') {
                        mem[p2 - 'a'] = value1;
                    }
                    cursor++;
                } else {
                    int currBit = mem[parts[1].charAt(0) - 'a'];
                    if (count >= 1 && currBit == prevBit) {
                        break;
                    }
                    if (count >= 1000) {
                        break out;
                    }
                    prevBit = currBit;
                    count++;
                    cursor++;
                }
            }
            from++;
        }
        return from;
    }

}
