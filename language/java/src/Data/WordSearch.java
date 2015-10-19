package Data;

// WordSearch class interface: solve word search puzzle
//
// CONSTRUCTION: with no initializer
// **************PUBLIC OPERATIONS**************
// int solvePuzzle()  --> Print all words found in the puzzle;
//                        return number of matches

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordSearch {

    private int rows, columns;
    private char theBoard[][];
    private String []theWords;
    private BufferedReader puzzleStream;
    private BufferedReader wordStream;
    private BufferedReader in = new
            BufferedReader(new InputStreamReader(System.in));

    public WordSearch() throws IOException {
        puzzleStream = openFile("Enter puzzle file");
        wordStream = openFile("Enter dictionary name");
        System.out.println("Reading files...");
        readPuzzle();
        readWords();
    }

    public int solvePuzzle() {
        int matches = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                for (int rd = -1; rd <= 1; rd++) {
                    for (int cd = -1; cd <= 1; cd++) {
                        if (rd != 0 || cd != 0)
                            matches += solveDirection(r, c, rd, cd);
                    }
                }
            }
        }

        return matches;
    }

    private static int prefixSearch(String[] a, String x) {
        int idx = Arrays.binarySearch(a, x);

        if (idx < 0)
            return -idx-1;
        else
            return idx;
    }

    private BufferedReader openFile(String message) {
        String fileName = "";
        FileReader theFile;
        BufferedReader fileIn = null;

        do {
            System.out.println(message + ":");

            try {
                fileName = in.readLine();
                if (fileName == null)
                    System.exit(0);
                theFile = new FileReader(fileName);
                fileIn = new BufferedReader(theFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (fileIn == null);

        System.out.println("Opened " + fileName);

        return fileIn;
    }

    private void readWords() throws IOException {
        List<String> words = new ArrayList<String>();

        String lastWord = null;
        String thisWord;

        while ((thisWord = wordStream.readLine()) != null) {
            if (lastWord != null && thisWord.compareTo(lastWord) < 0) {
                System.err.println("Dictionary is not sorted... skipping");
                continue;
            }
            words.add(thisWord);
            lastWord = thisWord;
        }

        theWords = new String[words.size()];
        theWords = words.toArray(theWords);
    }

    private void readPuzzle() throws IOException {
        String oneLine;
        List<String> puzzleLines = new ArrayList<String>();

        if ((oneLine = puzzleStream.readLine()) == null)
            throw new IOException("No lines in puzzle file");

        columns = oneLine.length();
        puzzleLines.add(oneLine);

        while ((oneLine = puzzleStream.readLine()) != null) {
            if (oneLine.length() != columns)
                System.err.println("Puzzle is not rectangular; skipping row");
            else
                puzzleLines.add(oneLine);
        }

        rows = puzzleLines.size();
        theBoard = new char[rows][columns];

        int r = 0;
        for (String theLine : puzzleLines) {
            theBoard[r++] = theLine.toCharArray();
        }
    }

    private int solveDirection(int baseRow, int baseCol, int rowDelta, int colDelta) {
        String charSequence = "";
        int numMatches = 0;
        int searchResult;

        charSequence += theBoard[baseRow][baseCol];

        for (int i = baseRow+rowDelta, j = baseCol+colDelta;
                 i >= 0 && j >= 0 && i < rows && j < columns;
                 i += rowDelta, j += colDelta) {
            charSequence += theBoard[i][j];
            searchResult = prefixSearch(theWords, charSequence);

            if (searchResult == theWords.length)
                break;
            if (!theWords[searchResult].startsWith(charSequence))
                break;
            if (theWords[searchResult].equals(charSequence)) {
                numMatches++;
                System.out.println("Found " + charSequence + " at " +
                                   baseRow + " " + baseCol + " to " +
                                   i + " " + j);
            }
        }

        return numMatches;
    }

    public static void main(String[] args) throws IOException {
        WordSearch p = null;

        p = new WordSearch();

        System.out.println("Solving...");
        p.solvePuzzle();
    }
}
