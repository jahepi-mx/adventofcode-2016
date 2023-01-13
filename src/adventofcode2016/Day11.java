package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Day11 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day14/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day11 day = new Day11();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }
    
    //String[] compounds = new String[] {"", "h_g", "h_m", "l_g", "l_m"};
    //String[] compounds = new String[] {"", "elerium_g", "elerium_m", "dilithium_g", "dilithium_m", "promethium_g", "promethium_m", "cobalt_g", "curium_g", "ruthenium_g", "plutonium_g", "cobalt_m", "curium_m", "ruthenium_m", "plutonium_m"};
    String[] compounds = new String[] {"", "promethium_g", "promethium_m", "cobalt_g", "curium_g", "ruthenium_g", "plutonium_g", "cobalt_m", "curium_m", "ruthenium_m", "plutonium_m"};
    
    private int part1(ArrayList<String> list) {
        
        
        
        //int[] floors = new int[] {1,2,1,3,1};
        int[] floors = new int[] {1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3};
        
        HashSet<Long> visited = new HashSet<>();
        Queue<Long> queue = new LinkedList<>();
        long stateN = stateToInt(floors);
        queue.add(stateN);
        visited.add(stateN);
        int[] dirs = new int[] {1,-1};
        HashMap<Long, Integer> distances = new HashMap<>();
        
        while (!queue.isEmpty()) {
            long num = queue.remove();
            floors = intToState(num);
            
            if (num == 44444444444l) {
                System.out.println(distances.get(num));
                break;
            }
            
            for (int dir : dirs) {
                
                int elevator = floors[0];
                if (elevator + dir < 1 || elevator + dir > 4) {
                    continue;
                }
                
                ArrayList<Integer> tmp = new ArrayList<>();
                for (int a = 1; a < floors.length; a++) {
                    if (floors[a] == elevator) {
                        tmp.add(a);
                    }
                }
                for (int a = 0; a < tmp.size(); a++) {
                    
                    int[] floorsCopy = floors.clone();
                    int[] floorsCopy2 = floors.clone();
                    floorsCopy[0] += dir;
                    floorsCopy[tmp.get(a)] += dir;
                    floorsCopy2[tmp.get(a)] += dir;
                    long tmpStateN = stateToInt(floorsCopy);
                    if (validate(floorsCopy, compounds) && validate(floorsCopy2, compounds)) {
                        if (!visited.contains(tmpStateN)) {
                            queue.add(tmpStateN);
                            visited.add(tmpStateN);
                            distances.put(tmpStateN, distances.containsKey(num) ? distances.get(num) + 1 :  1);
                        }
                    }
                    
                    for (int b = a + 1; b < tmp.size(); b++) {
                        
                        String cmp1 = compounds[tmp.get(a)];
                        String cmp2 = compounds[tmp.get(b)];
                        if ((cmp1.indexOf("_g") >= 0 && cmp2.indexOf("_g") >= 0)
                                || (cmp1.indexOf("_m") >= 0 && cmp2.indexOf("_m") >= 0)
                                || cmp1.split("_")[0].equals(cmp2.split("_")[0])) {
                        
                            floorsCopy = floors.clone();
                            floorsCopy2 = floors.clone();
                            floorsCopy[0] += dir;
                            floorsCopy[tmp.get(a)] += dir;
                            floorsCopy[tmp.get(b)] += dir;
                            floorsCopy2[tmp.get(a)] += dir;
                            floorsCopy2[tmp.get(b)] += dir;
                            tmpStateN = stateToInt(floorsCopy);
                            if (validate(floorsCopy, compounds) && validate(floorsCopy2, compounds)) {
                                if (!visited.contains(tmpStateN)) {
                                    queue.add(tmpStateN);
                                    visited.add(tmpStateN);
                                    distances.put(tmpStateN, distances.containsKey(num) ? distances.get(num) + 1 :  1);
                                }
                            }
                        }
                    }
                }
                
                
                
            }
            
        }
        
        return 0;
    }
    
    private boolean validate(int[] floors, String[] compounds) {
        ArrayList<Integer> tmp = new ArrayList<>();
        for (int a = 1; a < floors.length; a++) {
            if (floors[a] == floors[0]) {
                tmp.add(a);
            }
        }
        if (tmp.size() == 0) {
            return true;
        }
        int count = 0;
        for (int i : tmp) {
            count += compounds[i].indexOf("_g") >= 0 ? 1 : 0;
        }
        if (count == tmp.size()) {
            return true;
        }
        count = 0;
        for (int i : tmp) {
            count += compounds[i].indexOf("_m") >= 0 ? 1 : 0;
        }
        if (count == tmp.size()) {
            return true;
        }
        int[] flags = new int[floors.length];
        Arrays.fill(flags, -1);
        for (int i : tmp) {
            flags[i] = 0;
        }
        for (int a = 0; a < tmp.size(); a++) {
            String comp1 = compounds[tmp.get(a)];
            for (int b = a + 1; b < tmp.size(); b++) {
                String comp2 = compounds[tmp.get(b)];
                if (comp1.split("_")[0].equals(comp2.split("_")[0])) {
                    flags[tmp.get(a)] = 1;
                    flags[tmp.get(b)] = 1;
                }
            }
        }
        int nGenerators = 0;
        int spaces = 0;
        for (int a = 1; a < flags.length; a++) {
            if (flags[a] == 0) {
                nGenerators += compounds[a].indexOf("_g") >= 0 ? 1 : 0;
                spaces++;
            }
        }
        if (spaces == nGenerators) {
            return true;
        }
        if (spaces == 0) {
            return true;
        }
        return false;
    }
    
    private long stateToInt(int[] state) {
        long n = 0;
        for (int num : state) {
            n *= 10l;
            n += num;
        }
        return n;
    }
    
    private int[] intToState(long num) {
        int[] state = new int[compounds.length];
        for (int a = state.length - 1; a >= 0; a--) {
            state[a] = (int)(num % 10);
            num /= 10;
        }
        return state;
    }

    private int part2(ArrayList<String> list) {
        return 0;
    }
    
}
