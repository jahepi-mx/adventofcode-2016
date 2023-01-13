package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day23 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day23/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day23 day = new Day23();
        System.out.println("Part 1: " + day.run(list, true));
        System.out.println("Part 2: " + day.run(list, false));
    }

    private int run(ArrayList<String> listParam, boolean part1) {
        ArrayList<String> list = new ArrayList<>(listParam);
        boolean isRunning = true;
        int[] mem = new int[26];
        mem[0] = part1 ? 7 : 12;
        int cursor = 0;
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
                if (!part1 && ins.equals("jnz d -5")) {
                    mem[2] = 0;
                    mem[0] += mem[3] * mem[1];
                    mem[3] = 0;
                }
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
                int value = mem[parts[1].charAt(0) - 'a'];
                int pos = value + cursor;
                if (pos >= 0 && pos < list.size()) {
                    String tins = list.get(pos);
                    if (tins.indexOf("dec") >= 0) {
                        list.set(pos, tins.replace("dec", "inc"));
                    } else if (tins.indexOf("inc") >= 0) {
                        list.set(pos, tins.replace("inc", "dec"));
                    } else if (tins.indexOf("tgl") >= 0) {
                        list.set(pos, tins.replace("tgl", "inc"));
                    } else if (tins.indexOf("jnz") >= 0) {
                        list.set(pos, tins.replace("jnz", "cpy"));
                    } else {
                        list.set(pos, tins.replace("cpy", "jnz"));
                    }
                }
                cursor++;
            } 
        }
        return mem[0];
    }
}
