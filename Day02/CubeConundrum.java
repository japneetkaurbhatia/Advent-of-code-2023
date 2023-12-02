package Day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class CubeConundrum {

    // 12 red cubes, 13 green cubes, and 14 blue cubes
    private static HashMap<String, Integer> valueMap() {
        HashMap<String, Integer> colorVsNumber = new HashMap<>();
        colorVsNumber.put("red", 12);
        colorVsNumber.put("green", 13);
        colorVsNumber.put("blue", 14);
        return colorVsNumber;
    }

    private static boolean cubes(String cubes) {
        HashMap<String, Integer> colorVsNumber = valueMap();
        String[] pairs = cubes.split(";");
        for (String pair : pairs) {
            String[] values = pair.split(",");
            for (String value : values) {
                String[] colorAndNumber = value.split(" ");
                String number = colorAndNumber[1];
                String color = colorAndNumber[2];
                if (colorVsNumber.containsKey(color)) {
                    if (Integer.parseInt(number) > colorVsNumber.get(color)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static int getCubeConundrumForLine(String line) {
        String[] gameVsCube = line.split(":");
        String gameNumber = gameVsCube[0].split(" ")[1];
        String cubes = gameVsCube[1];
        boolean isPossible = cubes(cubes);
        return isPossible ? Integer.parseInt(gameNumber) : 0;
    }

    private static int cubeConundrum(String line) {
        int point = 0;
        point = getCubeConundrumForLine(line);
        return point;
    }

    public static void main(String[] args) {
        String fileName = "Day02/input02.txt";

        try (Scanner scanner = new Scanner(new File(fileName))) {
            int res = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                res += cubeConundrum(line);
            }
            System.out.println(res);
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
    }
}