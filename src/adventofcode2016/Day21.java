package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day21 {
    
    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day21/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day21 day = new Day21();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }
    
    private String part1(ArrayList<String> list) {
        return scramble(list, "abcdefgh");
    }
    
    private String part2(ArrayList<String> list) {
        return decode("abcdefgh".toCharArray(), "", list);
    }
    
    private String scramble(ArrayList<String> list, String key) {
        char[] arr = key.toCharArray();
        for (String line : list) {
            String[] parts = line.split(" ");
            if (line.indexOf("swap position") >= 0) {
                int x = Integer.valueOf(parts[2]);
                int y = Integer.valueOf(parts[5]);
                char tmp = arr[x];
                arr[x] = arr[y];
                arr[y] = tmp;
            } else if (line.indexOf("swap letter") >= 0) {
                for (int a = 0; a < arr.length; a++) {
                    if (arr[a] == parts[2].charAt(0)) {
                        arr[a] = parts[5].charAt(0);
                    } else if (arr[a] == parts[5].charAt(0)) {
                        arr[a] = parts[2].charAt(0);
                    }
                }
            } else if (line.indexOf("rotate left") >= 0) {
                char[] tmp = new char[arr.length];
                int steps = Integer.valueOf(parts[2]);
                for (int a = 0; a < arr.length; a++) {
                    int index = (a - steps) % tmp.length;
                    index += index < 0 ? tmp.length : 0;
                    tmp[index] = arr[a];
                }
                arr = tmp;
            } else if (line.indexOf("rotate right") >= 0) {
                char[] tmp = new char[arr.length];
                int steps = Integer.valueOf(parts[2]);
                for (int a = 0; a < arr.length; a++) {
                    int index = (a + steps) % tmp.length;
                    tmp[index] = arr[a];
                }
                arr = tmp;
            } else if (line.indexOf("reverse") >= 0) {
                int start = Integer.valueOf(parts[2]);
                int end = Integer.valueOf(parts[4]);
                while (start < end) {
                    char tmp = arr[start];
                    arr[start] = arr[end];
                    arr[end] = tmp;
                    start++;
                    end--;
                }
            } else if (line.indexOf("move position") >= 0) {
                int start = Integer.valueOf(parts[2]);
                int end = Integer.valueOf(parts[5]);
                char[] tmp = new char[arr.length];
                char ch = arr[start];
                tmp[end] = ch;
                int cursor = 0;
                for (int a = 0; a < arr.length; a++) {
                    if (cursor < arr.length && arr[cursor] == ch) {
                        cursor++;
                    }
                    if ((int) tmp[a] != 0) {
                        continue;
                    } else if (tmp[a] == 0) {
                        tmp[a] = arr[cursor++];
                    }
                }
                arr = tmp;
            } else if (line.indexOf("rotate based") >= 0) {
                char letter = parts[6].charAt(0);
                int index = 0;
                for (int a = 0; a < arr.length; a++) {
                     index = arr[a] == letter ? a : index;
                }
                int steps = index + 1 + (index >= 4 ? 1 : 0);
                char[] tmp = new char[arr.length];
                for (int a = 0; a < arr.length; a++) {
                    index = (a + steps) % tmp.length;
                    tmp[index] = arr[a];
                }
                arr = tmp;
            }
        }
        return new String(arr);
    }

    private String decode(char[] chars, String out, ArrayList<String> ins) {
        String res = "";
        if (out.length() == chars.length) {
            String scrambled = scramble(ins, out);
            if ("fbgdceah".equals(scrambled)) {
                return out;
            }
        }
        int index = 0;
        for (char ch : chars) {
            if (ch != ' ') {
                chars[index] = ' ';
                String tmp = decode(chars, out + ch, ins);
                res = tmp.length() > 0 ? tmp : res;
                chars[index] = ch;
            }
            index++;
        }
        return res;
    }
}
