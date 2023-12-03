import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GearRatio {

    public static boolean isSymbol(char c) {
        return (('!' <= c && c <= '/') && c != '.') || (':' <= c && c <= '@') || ('[' <= c && c <= '`')
                || ('{' <= c && c <= '~');
    }

    private static int calculateGearRatio(List<String> lines, int ans, int i, Map<Integer, List<Integer>> numDict) {
        for (Map.Entry<Integer, List<Integer>> entry : numDict.entrySet()) {
            List<Integer> v = entry.getValue();

            for (int t = 0; t < v.size(); t += 2) {
                if ((v.get(0) > 0 && isSymbol(lines.get(i).charAt(v.get(0) - 1))) ||
                        (v.get(1) < lines.get(i).length() - 1 && isSymbol(lines.get(i).charAt(v.get(1) + 1)))) {
                    System.out.println(entry.getKey() + " " + v.get(t) + " " + v.get(t + 1));
                    ans += entry.getKey();
                    continue;
                }

                for (int l = Math.max(0, v.get(t) - 1); l < Math.min(lines.get(i).length(),
                        v.get(t + 1) + 2); l++) {
                    if ((i > 0 && isSymbol(lines.get(i - 1).charAt(l))) ||
                            (i < lines.size() - 1 && isSymbol(lines.get(i + 1).charAt(l)))) {
                        System.out.println(entry.getKey() + " " + v.get(t) + " " + v.get(t + 1));
                        ans += entry.getKey();
                        break;
                    }
                }
            }
        }
        return ans;
    }

    private static Map<Integer, List<Integer>> findMattersIndex(Matcher matcher) {
        Map<Integer, List<Integer>> numDict = new HashMap<>();

        while (matcher.find()) {
            int num = Integer.parseInt(matcher.group());
            int start = matcher.start();
            int end = matcher.end() - 1;

            if (!numDict.containsKey(num)) {
                numDict.put(num, new ArrayList<>());
            }
            numDict.get(num).add(start);
            numDict.get(num).add(end);
        }
        return numDict;
    }

    private static int getGearRatio(List<String> lines, int ans) {
        for (int i = 0; i < lines.size(); i++) {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(lines.get(i));
            Map<Integer, List<Integer>> numDict = findMattersIndex(matcher);

            ans = calculateGearRatio(lines, ans, i, numDict);
        }
        return ans;
    }

    public static void main(String[] args) {
        String fileName = "input02.txt";
        List<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {

            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }

        int ans = 0;
        ans = getGearRatio(lines, ans);

        System.out.println(ans);
    }
}
