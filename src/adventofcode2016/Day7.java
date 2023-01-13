package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day7 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day7/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day7 day = new Day7();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        int res = 0;
        for (String line : list) {
            boolean isTLS = false;
            boolean insideBrackets = false;
            for (int a = 0; a < line.length() - 3; a++) {
                insideBrackets = line.charAt(a) == '[' ? true : insideBrackets;
                insideBrackets = line.charAt(a) == ']' ? false : insideBrackets;
                if (line.charAt(a) == line.charAt(a + 3) 
                        && line.charAt(a + 1) == line.charAt(a + 2)
                        && line.charAt(a) != line.charAt(a + 1)) {
                   if (insideBrackets) {
                       isTLS = false;
                       break;
                   } else {
                       isTLS = true;
                   }
                }
            }
            res += isTLS ? 1 : 0;
        }
        return res;
    }

    private int part2(ArrayList<String> list) {
        int res = 0;
        for (String line : list) {
            ArrayList<String> strs = new ArrayList<>();
            ArrayList<String> brackets = new ArrayList<>();
            String tmp = "";
            for (char ch : line.toCharArray()) {
                if (ch == '[') {
                    strs.add(tmp);
                    tmp = "";
                } else if (ch == ']') {
                    brackets.add(tmp);
                    tmp = "";
                } else {
                    tmp += ch;
                }
            }
            if (tmp.length() > 0) {
                strs.add(tmp);
            }
            out: for (String str : strs) {
                for (int a = 0; a < str.length() - 2; a++) {
                    if (str.charAt(a) == str.charAt(a + 2)
                            && str.charAt(a) != str.charAt(a + 1)) {
                        String search = str.charAt(a + 1) + "" + str.charAt(a) + "" + str.charAt(a + 1);
                        for (String bracket : brackets) {
                            if (bracket.indexOf(search) >= 0) {
                                res++;
                                break out;
                            }
                        }
                    }
                }
            }
        }
        return res;
    }
}
