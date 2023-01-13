package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;

public class Day17 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day17/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day17 day = new Day17();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private String part1(ArrayList<String> list) {
        return findPath(from, to, list.get(0), 0, "");
    }
    
    private int part2(ArrayList<String> list) {
        return findPath2(from, to, list.get(0), 0, "");
    }
    
    int[][] dirs = new int[][] {{0,-1}, {0,1}, {-1,0}, {1,0}};
    String dirsStr = "UDLR";
    int width = 4;
    int height = 4;
    int from = 0;
    int to = width * 3 + 3;
    
    String findPath(int from, int target, String passcode, int dist, String path) {
        if (from == target) {
            return path;
        }
        String ret = "";
        int len = Integer.MAX_VALUE;
        String md5 = getHash(passcode);
        int x = from % width;
        int y = from / width;
        for (int a = 0; a < dirs.length; a++) {
            int[] dir = dirs[a];
            char dirStr = dirsStr.charAt(a);
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && nx < width && ny >= 0 && ny < height && isWalkable(md5.charAt(a))) {
                String tmpPath = findPath(ny * width + nx, target, passcode + dirStr, dist + 1, path + dirStr);
                if (tmpPath.length() > 0 && tmpPath.length() < len) {
                    len = tmpPath.length();
                    ret = tmpPath;
                }
            }
        }
        return ret;
    }
    
    int findPath2(int from, int target, String passcode, int dist, String path) {
        int ret = Integer.MIN_VALUE;
        if (from == target) {
            return dist;
        }
        String md5 = getHash(passcode);
        int x = from % width;
        int y = from / width;
        for (int a = 0; a < dirs.length; a++) {
            int[] dir = dirs[a];
            char dirStr = dirsStr.charAt(a);
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && nx < width && ny >= 0 && ny < height && isWalkable(md5.charAt(a))) {
                ret = Math.max(ret, findPath2(ny * width + nx, target, passcode + dirStr, dist + 1, path + dirStr));
            }
        }
        return ret;
    }
    
    private boolean isWalkable(char ch) {
        return "bcdef".indexOf(ch) >= 0;
    }

    private String getHash(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytesOfMessage = str.getBytes("UTF-8");
            bytesOfMessage = md.digest(bytesOfMessage);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytesOfMessage.length; i++) {
                sb.append(Integer.toString((bytesOfMessage[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception e) {}
        return "";
    }
}
