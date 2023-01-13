package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Day13 {
    
    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day13/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day13 day = new Day13();
        System.out.println("Part 1: " + day.run(list, true));
        System.out.println("Part 2: " + day.run(list, false));
    }

    private int run(ArrayList<String> list, boolean part1) {
        int x = 1;
        int y = 1;
        int targetX = 31;
        int targetY = 39;
        int key = Integer.valueOf(list.get(0));
        int width = 3000;
        int steps = 50;
        
        int[][] vectors = new int[][] {{1,0}, {0,1}, {-1,0}, {0,-1}};
        HashSet<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(y * width + x);
        visited.add(y * width + x);
        int[] distances = new int[width * width];
        int minDist = 0;
        out: while (!queue.isEmpty()) {
            int pos = queue.remove();
            x = pos % width;
            y = pos / width;
            for (int[] vector : vectors) {
                int nx = x + vector[0];
                int ny = y + vector[1];
                if (nx < 0 || ny < 0) {
                    continue;
                }
                int value = nx * nx + 3 * nx + 2 * nx * ny + ny + ny * ny;
                value += key;
                int bits = 0 ;
                while (value > 0) {
                    bits += value % 2;
                    value /= 2;
                }
                if (bits % 2 == 0 && !visited.contains(ny * width + nx)) {
                    visited.add(ny * width + nx);
                    distances[ny * width + nx] = 1 + distances[y * width + x];
                    if (!part1 && distances[ny * width + nx] < steps) {
                        queue.add(ny * width + nx);
                    }
                    if (part1) {
                        queue.add(ny * width + nx);
                    }
                    if (part1 && ny == targetY && nx == targetX) {
                        minDist = distances[ny * width + nx];
                        break out;
                    }
                }
            }
        }
        return part1 ? minDist : visited.size();
    }
}
