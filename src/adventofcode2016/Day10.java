package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day10 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day10/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day10 day = new Day10();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    int[][] bots = new int[256][2];
    int[] outputs = new int[256];
    HashMap<Integer, String> map = new HashMap<>();
    
    private int part1(ArrayList<String> list) {
        
        int startBot = 0;
        for (String line : list) {
            String[] parts = line.split(" ");
            if (line.indexOf("value") >= 0) {
                int value = Integer.valueOf(parts[1]);
                int bot = Integer.valueOf(parts[5]);
                if (bots[bot][0] == 0) {
                    bots[bot][0] = value;
                } else if (bots[bot][1] == 0) {
                    bots[bot][1] = value;
                }
                if (bots[bot][0] > 0 && bots[bot][1] > 0) {
                    startBot = bot;
                }
            } else {
                int bot = Integer.valueOf(parts[1]);
                map.put(bot, line);
            }
        }
        return simulate(startBot);
    }
    
    int simulate(int bot) {
        int currBot = 0;
        String ins = map.get(bot);
        String[] parts = ins.split(" ");
        String dest1 = parts[5];
        int bot2 = Integer.valueOf(parts[6]);
        String dest2 = parts[10];
        int bot3 = Integer.valueOf(parts[11]);
        int low = Math.min(bots[bot][0], bots[bot][1]);
        int high = Math.max(bots[bot][0], bots[bot][1]);
        currBot = low == 17 && high == 61 ? bot : currBot;
        bots[bot][0] = bots[bot][1] = 0;
        if (dest1.equals("bot")) {
            if (bots[bot2][0] == 0) {
                bots[bot2][0] = low;
            } else if (bots[bot2][1] == 0) {
                bots[bot2][1] = low;
            }
        } else {
            outputs[bot2] = low;
        }
        if (bots[bot2][0] > 0 && bots[bot2][1]> 0) {
            currBot |= simulate(bot2);
        }
        
        if (dest2.equals("bot")) {
            if (bots[bot3][0] == 0) {
                bots[bot3][0] = high;
            } else if (bots[bot3][1] == 0) {
                bots[bot3][1] = high;
            }
        } else {
            outputs[bot3] = high;
        }
        if (bots[bot3][0] > 0 && bots[bot3][1]> 0) {
            currBot |= simulate(bot3);
        }
        return currBot;
    }

    private int part2(ArrayList<String> list) {
        return outputs[0] * outputs[1] * outputs[2];
    }
}
