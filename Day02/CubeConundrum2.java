package Day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class CubeConundrum2 {

    private static int getSumOfMinValueOfEachColor(HashMap<String, Integer> colorVsNumber) {
        int value = 1;
        if (colorVsNumber.containsKey("green")) {
            value *= colorVsNumber.get("green");
            System.out.println(value);
        }
        if (colorVsNumber.containsKey("blue")) {
            value *= colorVsNumber.get("blue");
            System.out.println(value);
        }
        if (colorVsNumber.containsKey("red")) {
            value *= colorVsNumber.get("red");
            System.out.println(value);
        }
        
        return value;
    }

    private static int cubes(String cubes) {
        HashMap<String, Integer> colorVsNumber = new HashMap<>();
        String[] pairs = cubes.split(";");
        for(String pair : pairs) {
            String[] values = pair.split(",");
            for(String value : values) {
                String[] colorAndNumber = value.split(" ");
                String number = colorAndNumber[1];
                String color = colorAndNumber[2];
                if (colorVsNumber.containsKey(color)) {
                    if(colorVsNumber.get(color) < Integer.parseInt(number)) {
                        colorVsNumber.put(color, Integer.parseInt(number));
                    } else {
                        colorVsNumber.put(color, colorVsNumber.get(color));
                    }
                } else {
                    colorVsNumber.put(color, Integer.parseInt(number));
                }
            }
        }

        int minValue = getSumOfMinValueOfEachColor(colorVsNumber);

        return minValue;
    }

    private static int getCubeConundrumForLine(String line) {
        String[] gameVsCube = line.split(":");
        String cubes = gameVsCube[1];
        int minPossible = cubes(cubes); 
        return minPossible;
    }

    private static int cubeConundrum(String line) {
        int point = 0;
        point = getCubeConundrumForLine(line);

        System.out.println("line: " + point);
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