package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Day24 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day24/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day24 day = new Day24();
        System.out.println("Part 1: " + day.run(list, true));
        System.out.println("Part 2: " + day.run(list, false));
    }
    
    int width, height;
    HashMap<Integer, Integer> dests = new HashMap<>();
    int[][] dirs = new int[][] {{1,0},{0,1},{-1,0},{0,-1}};
    private int run(ArrayList<String> list, boolean part1) {
        width = list.get(0).length();
        height = list.size();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (list.get(y).charAt(x) >= '0' && list.get(y).charAt(x) <= '9') {
                    dests.put(list.get(y).charAt(x) - '0' + 1, y * width + x);
                }
            }
        }
        int[] digits = new int[dests.size() - 1];
        int index = 0;
        for (int n : digits) {
            digits[index++] = index + 1;
        }
        ArrayList<Integer> perms = new ArrayList<Integer>();
        perms(digits, 0, 0, perms);
        
        int min = Integer.MAX_VALUE;
        for (int n : perms) {
            n = part1 ? n * 10 + 1 : ((int) Math.pow(10, 8) + n * 10 + 1);
            min = Math.min(calculateDist(n * 10 + 1, list), min);
        }
        return min;
    }
    
    private int calculateDist(int n, ArrayList<String> list) {
        int dist = 0;
        while (n > 10) {
            int from = dests.get(n % 10);
            n /= 10;
            HashSet<Integer> visited = new HashSet<>();
            Queue<Integer> queue = new LinkedList<>();
            int[] distances = new int[width * height];
            queue.add(from);
            visited.add(from);
            queue: while (!queue.isEmpty()) {
                int node = queue.remove();
                int x = node % width;
                int y = node / width;
                for (int[] dir : dirs) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx >= 0 && nx < width && ny >= 0 && ny < height 
                            && list.get(ny).charAt(nx) != '#'
                            && !visited.contains(ny * width + nx)) {
                        queue.add(ny * width + nx);
                        visited.add(ny * width + nx);
                        distances[ny * width + nx] = distances[node] + 1;
                        if (ny * width + nx == dests.get(n % 10)) {
                            dist += distances[ny * width + nx];
                            break queue;
                        }
                    }
                }
            }
        }
        return dist;
    }
    
    private void perms(int[] digits, int number, int size, ArrayList<Integer> perms) {
        if (size == digits.length) {
            perms.add(number);
        }
        int index = 0;
        for (int d : digits) {
            if (d >= 0) {
                digits[index] = -1;
                perms(digits, number * 10 + d, size + 1, perms);
                digits[index] = d;
            }
            index++;
        }
    }
}
