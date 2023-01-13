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

public class Day22 {
    
    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day22/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day22 day = new Day22();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }
    
    class Data {
        int size, used, avail, use;
    }

    private int part1(ArrayList<String> list) {
        int width = 50;
        int count = 0;
        int maxAvail = 0;
        HashMap<Integer, Data> map = new HashMap<>();
        for (int a = 2; a < list.size(); a++) {
            String[] parts = list.get(a).split("\\s+");
            String[] coords = parts[0].split("-");
            int x = Integer.valueOf(coords[1].substring(1, coords[1].length()));
            int y = Integer.valueOf(coords[2].substring(1, coords[2].length()));
            
            Data data = new Data();
            data.size = Integer.valueOf(parts[1].substring(0, parts[1].length() - 1));
            data.used = Integer.valueOf(parts[2].substring(0, parts[2].length() - 1));
            data.avail = Integer.valueOf(parts[3].substring(0, parts[3].length() - 1));
            data.use = Integer.valueOf(parts[4].substring(0, parts[4].length() - 1));
            map.put(y * width + x, data);
            maxAvail = Math.max(maxAvail, data.avail);
        }
        HashSet<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited.add(0);
        int[][] dirs = new int[][] {{1,0},{0,1},{-1,0},{0,-1}};
        while (!queue.isEmpty()) {
            int node = queue.remove();
            int x = node % width;
            int y = node / width;
            Data dataNode1 = map.get(node);
            count += dataNode1.used > 0 && dataNode1.used <= maxAvail ? 1 : 0;
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx >= 0 && ny >= 0 && map.containsKey(ny * width + nx) 
                        && !visited.contains(ny * width + nx)) {
                    visited.add(ny * width + nx);
                    queue.add(ny * width + nx);                       
                }
            } 
        }
        return count;
    }

    int width = 50;
    HashMap<Integer, Data> map = new HashMap<>();
    
    private int part2(ArrayList<String> list) {
        int count = 0;
        int maxX = 0;
        int emptyNode = 0;
        for (int a = 2; a < list.size(); a++) {
            String[] parts = list.get(a).split("\\s+");
            String[] coords = parts[0].split("-");
            int x = Integer.valueOf(coords[1].substring(1, coords[1].length()));
            int y = Integer.valueOf(coords[2].substring(1, coords[2].length()));
            Data data = new Data();
            data.size = Integer.valueOf(parts[1].substring(0, parts[1].length() - 1));
            data.used = Integer.valueOf(parts[2].substring(0, parts[2].length() - 1));
            data.avail = Integer.valueOf(parts[3].substring(0, parts[3].length() - 1));
            data.use = Integer.valueOf(parts[4].substring(0, parts[4].length() - 1));
            map.put(y * width + x, data);
            maxX = Math.max(maxX, x);
            if (data.used == 0) {
                emptyNode = y * width + x;
            }
        }
        int offset = 1;
        int from = emptyNode;
        int to = 0 * width + (maxX - offset);
        int mainNode = 0 * width + maxX;
        
        int dist = search(from, to, mainNode);
        count += dist;
        while (to > 0) {
            offset++;
            map.get(to).used = map.get(mainNode).used;
            map.get(mainNode).used = 0;
            int tmpMainNode = mainNode;
            mainNode = to;
            from = tmpMainNode;
            to = 0 * width + (maxX - offset);
            dist = search(from, to, mainNode);
            count += dist + 1;
        }
        return count + 1;
    }
    
    private int search(int from, int to, int mainNode) {
        HashSet<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(from);
        visited.add(from);
        visited.add(mainNode);
        int[] distances = new int[2000];
        int[] parents = new int[2000];
        Arrays.fill(parents , -1);
        int[][] dirs = new int[][] {{1,0},{0,1},{-1,0},{0,-1}};
        queue: while (!queue.isEmpty()) {
            int node = queue.remove();
            Data parentNodeData = map.get(node);
            int x = node % width;
            int y = node / width;
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx >= 0 && ny >= 0 && map.containsKey(ny * width + nx) 
                        && !visited.contains(ny * width + nx)) {
                    Data currNodeData = map.get(ny * width + nx);
                    if (currNodeData.used <= parentNodeData.size) {
                        visited.add(ny * width + nx);
                        queue.add(ny * width + nx); 
                        distances[ny * width + nx] = distances[node] + 1;
                        parents[ny * width + nx] = node;
                        if (ny * width + nx == to) {
                            break queue;
                        }
                    }
                }
            } 
        } 
        
        int tmp = to;
        int used = 0;
        while (parents[tmp] != -1) {
            int tmpUsed = map.get(tmp).used;
            map.get(tmp).used = used;
            tmp = parents[tmp];
            used = tmpUsed;
        }
        map.get(tmp).used = used;
        return distances[to];
    }
}
