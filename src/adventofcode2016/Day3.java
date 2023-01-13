package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day3 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day3/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day3 day3 = new Day3();
        System.out.println("Part 1: " + day3.part1(list));
        System.out.println("Part 2: " + day3.part2(list));
    }

    private int part1(ArrayList<String> list) {
        int res = 0;
        for (String line : list) {
            String[] nums = line.trim().split("\\s+");
            int a = Integer.valueOf(nums[0]);
            int b = Integer.valueOf(nums[1]);
            int c = Integer.valueOf(nums[2]);
            res += a + b > c && a + c > b && b + c > a ? 1 : 0;
        }
        return res;
    }

    private int part2(ArrayList<String> list) {
        int res = 0;
        for (int i = 0; i < list.size(); i += 3) {
            String[] numsA = list.get(i).trim().split("\\s+");
            String[] numsB = list.get(i + 1).trim().split("\\s+");
            String[] numsC = list.get(i + 2).trim().split("\\s+");
            int a = Integer.valueOf(numsA[0]);
            int b = Integer.valueOf(numsB[0]);
            int c = Integer.valueOf(numsC[0]);
            res += a + b > c && a + c > b && b + c > a ? 1 : 0;
            a = Integer.valueOf(numsA[1]);
            b = Integer.valueOf(numsB[1]);
            c = Integer.valueOf(numsC[1]);
            res += a + b > c && a + c > b && b + c > a ? 1 : 0;
            a = Integer.valueOf(numsA[2]);
            b = Integer.valueOf(numsB[2]);
            c = Integer.valueOf(numsC[2]);
            res += a + b > c && a + c > b && b + c > a ? 1 : 0;
        }
        return res;
    }
}
