package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day12 {
    public static void main(String[] args) throws IOException {
        
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day12/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day12 day = new Day12();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        return process(list, new int[] {0, 0, 0, 0});
    }
    
    private int part2(ArrayList<String> list) {
        return process(list, new int[] {0, 0, 1, 0});
    }
    
    private int process(ArrayList<String> list, int[] registers) {
        int curr = 0;
        boolean isRunning = true;
        while (isRunning) {
            String ins = list.get(curr);
            String[] parts = ins.split(" ");
            if (ins.indexOf("cpy") >= 0) {
                String from = parts[1];
                if (from.charAt(0) >= '0' && from.charAt(0) <= '9') {
                    registers[parts[2].charAt(0) - 'a'] = Integer.valueOf(from);
                } else {
                    registers[parts[2].charAt(0) - 'a'] = registers[from.charAt(0) - 'a'];
                }
            } else if (ins.indexOf("dec") >= 0) {
                registers[parts[1].charAt(0) - 'a']--;
            } else if (ins.indexOf("inc") >= 0) {
                registers[parts[1].charAt(0) - 'a']++; 
            } else {
                int offset = Integer.valueOf(parts[2]);
                int value = parts[1].charAt(0) >= '0' && parts[1].charAt(0) <= '9' ? Integer.valueOf(parts[1]) : registers[parts[1].charAt(0) - 'a'];
                if (value != 0) {
                    int pos = curr + offset;
                    if (pos >= list.size() || pos < 0) {
                        isRunning = false;
                    } else {
                        curr += offset;
                        continue;
                    }
                }
            }
            curr++;
            isRunning = curr < list.size();
        }
        return registers[0];
    }
}
