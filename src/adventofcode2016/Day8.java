package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Day8 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day8/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day8 day = new Day8();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        int width = 50;
        int height = 6;
        int res = 0;
        int[] map = new int[width * height];
        for (String line : list) {
            if (line.indexOf("rect") >= 0) {
                String[] parts = line.split(" ")[1].split("x");
                for (int x = 0; x < Integer.valueOf(parts[0]); x++) {
                    for (int y = 0; y < Integer.valueOf(parts[1]); y++) {
                        map[y * width + x] = 1;
                    }
                }
            } else if (line.indexOf("column") >= 0) {
                ArrayList<Integer> tmp = new ArrayList<>();
                String[] parts = line.split(" ");
                int lx = Integer.valueOf(parts[2].split("=")[1]);
                int move = Integer.valueOf(parts[4]);
                for (int ly = 0; ly < height; ly++) {
                    if (map[ly * width + lx] == 1) {
                        map[ly * width + lx] = 0;
                        tmp.add(ly * width + lx);
                    }
                }
                for (int pos : tmp) {
                    int ly = pos / width + move;
                    ly = ly % height;
                    map[ly * width + lx] = 1;
                }
            } else if (line.indexOf("row") >= 0) {
                ArrayList<Integer> tmp = new ArrayList<>();
                String[] parts = line.split(" ");
                int ly = Integer.valueOf(parts[2].split("=")[1]);
                int move = Integer.valueOf(parts[4]);
                for (int lx = 0; lx < width; lx++) {
                    if (map[ly * width + lx] == 1) {
                        map[ly * width + lx] = 0;
                        tmp.add(ly * width + lx);
                    }
                }
                for (int pos : tmp) {
                    int lx = pos % width + move;
                    lx = lx % width;
                    map[ly * width + lx] = 1;
                }
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(map[y * width + x] == 0 ? " " : "0");
                res += map[y * width + x];
            }
            System.out.println("");
        }
        return res;
    }

    private int part2(ArrayList<String> list) {
        return 0;
    }
}
