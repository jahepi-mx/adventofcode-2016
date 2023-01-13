package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day18 {
    
    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day18/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day18 day = new Day18();
        System.out.println("Part 1: " + day.run(list, 40));
        System.out.println("Part 2: " + day.run(list, 400_000));
    }

    private int run(ArrayList<String> list, int height) {
        int count = 0;
        int width = list.get(0).length();
        int[] m = new int[width * height];
        
        for (int a = 0; a < width; a++) {
            m[a] = list.get(0).charAt(a) == '^' ? 1 : 0;
            count += m[a] == 0 ? 1 : 0;
        }
        for (int y = 1; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = 1, num = 0;
                for (int[] dir : new int[][] {{1,-1}, {0,-1}, {-1,-1}}) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (ny >= 0 && ny < height && nx >= 0 && nx < width) {
                        num += p * m[ny * width + nx];
                    }
                    p *= 2;
                }
                m[y * width + x] = num != 6 && num != 3 && num != 4 && num != 1 ? 0 : 1;
                count += m[y * width + x] == 0 ? 1 : 0;
            }
        }
        return count;
    }
}
