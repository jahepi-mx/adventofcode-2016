package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day4 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day4/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day4 day = new Day4();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        int sum = 0;
        for (String line : list) {
            int[] counting = new int[255];
            String[] parts = line.split("-");
            for (int a = 0; a < parts.length - 1; a++) {
                for (char ch : parts[a].toCharArray()) {
                    counting[ch]++;
                }
            }
            ArrayList<Integer> nums = new ArrayList<>();
            for (int n : counting) {
                if (n > 0) {
                    nums.add(n);
                }
            }
            Collections.sort(nums, Collections.reverseOrder());
            String[] otherParts = parts[parts.length - 1].split("\\[");
            int num = Integer.valueOf(otherParts[0]);
            boolean isOk = true;
            for (int a = 0; a < 5; a++) {
                char ch = otherParts[1].charAt(a);
                isOk = counting[ch] != nums.get(a) ? false : isOk;
            }
            sum += isOk ? num : 0;
        }
        return sum;
    }

    private int part2(ArrayList<String> list) {
        for (String line : list) {
            String[] parts = line.split("-");
            String[] otherParts = parts[parts.length - 1].split("\\[");
            int num = Integer.valueOf(otherParts[0]);
            String msg = "";
            for (int a = 0; a < parts.length - 1; a++) {
                for (char ch : parts[a].toCharArray()) {
                    ch = (char) ('a' + ((ch - 'a') + num) % 26);
                    msg += ch;
                }
            }
            if (msg.indexOf("northpole") >= 0) {
                return num;
            }
        }
        return -1;
    }
}
