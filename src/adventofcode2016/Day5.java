package adventofcode2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Day5 {
    
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day5/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day5 day = new Day5();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private String part1(ArrayList<String> list) throws IOException, NoSuchAlgorithmException {
        int limit = 8, count = 0;
        String res = "";
        MessageDigest md = MessageDigest.getInstance("MD5");
        while (limit > 0) {
            byte[] bytesOfMessage = ("ugkcyxxp" + count++).getBytes("UTF-8");
            byte[] theMD5digest = md.digest(bytesOfMessage);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < theMD5digest.length; i++) {
                sb.append(Integer.toString((theMD5digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            String md5 = sb.toString();
            if (md5.indexOf("00000") == 0) {
                res += md5.charAt(5);
                limit--;
            }
        }
        return res;
    }

    private String part2(ArrayList<String> list) throws IOException, NoSuchAlgorithmException {
        int[] pass = new int[] {-1,-1,-1,-1,-1,-1,-1,-1};
        int limit = 8, count = 0;
        String res = "";
        MessageDigest md = MessageDigest.getInstance("MD5");
        while (limit > 0) {
            byte[] bytesOfMessage = ("ugkcyxxp" + count++).getBytes("UTF-8");
            byte[] theMD5digest = md.digest(bytesOfMessage);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < theMD5digest.length; i++) {
                sb.append(Integer.toString((theMD5digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            String md5 = sb.toString();
            if (md5.indexOf("00000") == 0) {
                char pos = md5.charAt(5);
                if (pos >= '0' && pos <= '7' && pass[pos - '0'] == -1) {
                    pass[pos - '0'] = md5.charAt(6);
                    limit--;
                }
            }
        }
        for (int n : pass) {
            res += (char) n;
        }
        return res;
    }
}
