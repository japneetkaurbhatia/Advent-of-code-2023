import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GearRatios {

    private static HashMap<Integer, Map<String, IndexPair>> numberResult = new HashMap<>();
    private static HashMap<Integer, Map<String, Integer>> characterResult = new HashMap<>();

    private static int gearRatio() {

        
        int sum = 0;

        for (Map.Entry<Integer, Map<String, Integer>> characterEntry : characterResult.entrySet()) {
            Integer outerInteger = characterEntry.getKey();
            Map<String, Integer> innerMap = characterEntry.getValue();

            for (Map.Entry<String, Integer> innerEntry : innerMap.entrySet()) {
                String innerKey = innerEntry.getKey();
                Integer innerInteger = innerEntry.getValue();

                Map<String, IndexPair> numberMap = numberResult.get(outerInteger);
                if (numberMap != null) {
                    IndexPair indexPair = numberMap.get(innerKey);
                    if (indexPair != null) {
                        if (checkConditions(innerInteger, outerInteger, indexPair)) {
                            sum += Integer.parseInt(innerKey);
                        }
                    }
                }
            }
        }

        return sum;
    }

    private static boolean checkConditions(int innerInteger, int outerInteger, IndexPair indexPair) {
        int innerMinus1 = innerInteger - 1;
        int innerPlus1 = innerInteger + 1;
        int outerMinus1 = outerInteger - 1;
        int outerPlus1 = outerInteger + 1;

        return innerMinus1 == indexPair.getEndIndex() ||
                innerPlus1 == indexPair.getStartIndex() ||
                (innerMinus1 == outerMinus1 && innerMinus1 == indexPair.getEndIndex()) ||
                (innerMinus1 == outerPlus1 && innerMinus1 == indexPair.getStartIndex()) ||
                (innerPlus1 == outerMinus1 && innerPlus1 == indexPair.getEndIndex()) ||
                (innerPlus1 == outerPlus1 && innerPlus1 == indexPair.getStartIndex());
    }


    



    public static void main(String[] args) {
        String fileName = "Day02/input02.txt";
        
        try (Scanner scanner = new Scanner(new File(fileName))) {
            int res = 0;
            int no = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                getNumbersFromLine(line, no);
                getSpecialCharacterFromLine(line, no);
                no++;
            }
            res = gearRatio();
            System.out.println(res);
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
    }

    private static void getSpecialCharacterFromLine(String line, int no) {
        Map<String, Integer> resultMap = new HashMap<>();
        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);
            if(currentChar == '*' || currentChar == '#' || currentChar == '$' || currentChar == '+' || currentChar == '%') {
                resultMap.put("", i);
            }
        }
        characterResult.put(no, resultMap);
    }

    
    private static void getNumbersFromLine(String line, int no) {
        Pattern pattern = Pattern.compile("\\d+"); 
        Matcher matcher = pattern.matcher(line);

        Map<String, IndexPair> resultMap = new HashMap<>();


        while (matcher.find()) {
            String number = matcher.group();
            int startIndex = matcher.start();
            int endIndex = matcher.end();

            resultMap.put(number, new IndexPair(startIndex, endIndex));
        }

        numberResult.put(no, resultMap);
        
    }


    private static class IndexPair {
        private final int startIndex;
        private final int endIndex;

        public IndexPair(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        public int getStartIndex() {
            return startIndex;
        }

        public int getEndIndex() {
            return endIndex;
        }
    }
}
