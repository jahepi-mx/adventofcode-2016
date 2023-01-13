package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day16 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day16/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day16 day = new Day16();
        System.out.println("Part 1: " + day.run(list, 272));
        System.out.println("Part 2: " + day.run(list, 35651584));
    }
    
    private String run(ArrayList<String> list, int size) {
        StringBuilder state = new StringBuilder(list.get(0));
        while (state.length() < size) {
            state.append('0');
            for (int a = state.length() - 2; a >= 0; a--) {
                state.append((char)('a' - state.charAt(a)));
            }
            
        }
        StringBuilder tmp = new StringBuilder();
        while (tmp.length() % 2 == 0) {
            tmp.setLength(0);
            for (int a = 0; a < size; a += 2) {
                int bit1 = state.charAt(a) - 'a';
                int bit2 = state.charAt(a + 1) - 'a';
                tmp.append((bit1 ^ bit2) == 0 ? '1' : '0');
            }
            state = new StringBuilder(tmp);
            size /= 2;
        }
        return tmp.toString();
    }
}
