package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day19 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day19/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        
        Day19 day = new Day19();
        System.out.println("Part 1: " + day.part1(3017957));
        System.out.println("Part 2: " + day.part2(3017957));
    }
    
    private int part1(int sizeParam) {
        size = sizeParam;
        MyNode root = null, prev = null;
        for (int i = 1; i <= size; i++) {
            MyNode node = new MyNode();
            node.value = i;
            node.prev = prev;
            if (prev != null) {
                prev.next = node;
            }
            prev = node;
            root = root == null ? node : root;
            root.prev = node;
        }
        root.prev.next = root;
        while (root != root.next) {
            remove(root.next);
            root = root.next;
        }
        return root.value;
    }

    int size = 0;
    private int part2(ArrayList<String> list, int sizeParam) {
        size = sizeParam;
        MyNode root = null, prev = null;
        for (int i = 1; i <= size; i++) {
            MyNode node = new MyNode();
            node.value = i;
            node.prev = prev;
            if (prev != null) {
                prev.next = node;
            }
            prev = node;
            root = root == null ? node : root;
            root.prev = node;
        }
        root.prev.next = root;
        while (size > 1) {
            int moves = size / 2;
            MyNode tmp = root;
            for (int a = moves; a >= 1; a--) {
                tmp = tmp.next;
            }
            remove(tmp);
            root = root.next;
        }
        
        return root.value;
    }
    
    private void remove(MyNode node) {
        MyNode prev = node.prev;
        MyNode next = node.next;
        prev.next = next;
        next.prev = prev;
        size--;
    }
    
    class MyNode {
        int value;
        MyNode prev, next;
    }
    
    // https://oeis.org/A334473
    private int highestPower(int n) {
        int option = 0;
        while (Math.pow(3, option) <= n) {
            option++;
        }
        return (int) Math.pow(3, option - 1);
    }
    
    private int part2(int n) {
        int x = highestPower(n);
        if (x == n) {
            return x;
        } else {
            if (n < 2 * x) {
                return n % x;
            } else {
                return x + 2 * (n % x);
            }
        }
    }
}
