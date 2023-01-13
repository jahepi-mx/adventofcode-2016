package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.TreeMap;

public class Day14 {
    
    public static void main(String[] args)throws IOException, NoSuchAlgorithmException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day14/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day14 day = new Day14();
        System.out.println("Part 1: " + day.part1(list, true));
        System.out.println("Part 2: " + day.part2(list, false));
    }
    
    private int part1(ArrayList<String> list, boolean part1) throws IOException, NoSuchAlgorithmException {
        return run(list, true);
    }
    
    private int part2(ArrayList<String> list, boolean part1) throws IOException, NoSuchAlgorithmException {
        return run(list, false);
    }
    
    private int run(ArrayList<String> list, boolean part1) throws IOException, NoSuchAlgorithmException {
        int total = 25_000;
        ArrayList<Integer>[] hashes = new ArrayList[256];
        TreeMap<Integer, Integer> trees = new TreeMap<>();
        for (int b = 0; b < hashes.length; b++) {
            hashes[b] = new ArrayList<>();
        }
        MessageDigest md = MessageDigest.getInstance("MD5");
        for (int a = 0; a < total; a++) {
            String md5 = list.get(0) + a;
            for (int c = 0; c < (part1 ? 1 : 2017); c++) {
                byte[] bytesOfMessage = md5.getBytes("UTF-8");
                bytesOfMessage = md.digest(bytesOfMessage);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < bytesOfMessage.length; i++) {
                    sb.append(Integer.toString((bytesOfMessage[i] & 0xff) + 0x100, 16).substring(1));
                }
                md5 = sb.toString();
                bytesOfMessage = md5.getBytes("UTF-8");
            }
            
            int prev = -1;
            int count = 0;
            boolean flag = false;
            for (int ch : md5.toCharArray()) {
                if (prev != ch) {
                    if (count >= 5) {
                        hashes[prev].add(a);
                    }
                    if (count >= 3 && !flag) {
                        flag = true;
                        trees.put(a, prev);
                    }
                    count = 1;
                } else {
                    count++;
                }
                prev = ch;
            }
            if (count >= 5) {
                hashes[prev].add(a);
            }
            if (count >= 3 && !flag) {
                trees.put(a, prev);
            }
        }
        int num = 0;
        for (int index : trees.keySet()) {
            int ch = trees.get(index);
            for (int index2 : hashes[ch]) {
                if (index2 >= index + 1 && index2 <= index + 1000 - 1) {
                    if (++num == 64) {
                        return index;
                    }
                    break;
                }
            }
        }
        return 0;
    }
    
}
