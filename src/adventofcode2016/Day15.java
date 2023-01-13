package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day15 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day15/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day15 day = new Day15();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }
    
    private int part1(ArrayList<String> list) {
        return run(list, true);
    }
    
    private int part2(ArrayList<String> list) {
        return run(list, false);
    }

    private int run(ArrayList<String> list, boolean part1) {
        int index = 0;
        int[] has = new int[list.size()];
        int[] pos = new int[list.size()];
        for (String line : list) {
            String[] parts = line.split(" ");
            has[index] = Integer.valueOf(parts[3]);
            pos[index++] = Integer.valueOf(parts[11].substring(0, parts[11].length() - 1));
        }
        int counter = 1;
        int x = 0;
        boolean isGood = false;
        while (!isGood) {
            int turn = 1;
            x = has[0] * counter - (turn + pos[0]);
            isGood = true;
            for (int a = 1; a < (part1 ? has.length - 1 : has.length); a++) {
                turn++;
                if ((x + turn + pos[a]) % has[a] > 0) {
                    isGood = false;
                }
            }
            counter++;
        }
        return x;
    }
}
