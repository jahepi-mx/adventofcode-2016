package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day19 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day19/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day19 day = new Day19();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        /*
          
          1 2 3 4 5
                1
                2
              1 5
          2 4 2 4
          1 3 5 3
          
          
          
          1 2 3 4 5 6 7
                    6
                    5
                    4
                  6 3
                1 5 1
          2 4 6 2 4 2
          1 3 5 7 3 7 
           
         */
        return 0;
    }

    private int part2(ArrayList<String> list) {
        return 0;
    }
}
