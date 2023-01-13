package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Day20 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day20/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day20 day = new Day20();
        System.out.println("Part 1: " + day.run(list, true));
        System.out.println("Part 2: " + day.run(list, false));
    }

    private long run(ArrayList<String> list, boolean part1) {
        HashMap<Long, Long> map = new HashMap<>();
        ArrayList<Long> lows = new ArrayList<>();
        for (String line : list) {
            long low = Long.valueOf(line.split("-")[0]);
            long high = Long.valueOf(line.split("-")[1]);
            lows.add(low);
            map.put(low, high);
        }
        Collections.sort(lows);
        long min = 0, count = 0;
        for (long low : lows) {
            long high = map.get(low);
            if (min < low) {
                long diff = (low - min) - 1;
                if (part1 && diff > 0) {
                    return min + 1;
                }
                count += diff;
                min = high;
            } else if (min >= low && min <= high) {
                min = high;
            }
        }
        return count;
    }
}
