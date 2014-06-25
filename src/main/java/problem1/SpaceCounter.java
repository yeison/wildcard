package problem1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static java.lang.System.out;
import static org.apache.commons.math3.util.CombinatoricsUtils.factorial;

public class SpaceCounter {
    private static final int NUMBER_OF_NEW_CARDS = 5;
    private static final long FACTORIAL_5 = factorial(5);

    public static void main(String[] args){
        Stream<String> fileLineStream = FileLineReader.getLineStream("problem1input.txt");

        char[][] grid = convertLineStreamToGrid(fileLineStream);

        printGrid(grid);

        long totalConfigurations = 0;
        for(char[] row : grid)
            totalConfigurations += countConfigurations(row);

        out.println("\n");
        printGrid(reflectGridAcrossDiagonal(grid));

        for(char[] column : grid)
            totalConfigurations += countConfigurations(column);

        out.printf("\nTotal Configurations: %d\n", totalConfigurations);

    }

    public static int countSpaces(char[] vector){
        int count = 0;

        for(char c : vector){
            if(c == '*'){
                count++;
            }
        }

        return count;
    }

    public static long countConfigurations(char[] vector){
        int emptySpaces = countSpaces(vector);

        if(emptySpaces >= NUMBER_OF_NEW_CARDS){
            long permutations  =  factorial(emptySpaces) / factorial(emptySpaces-NUMBER_OF_NEW_CARDS);

            return permutations;
        }

        return 0;
    }

    public static long countContiguousConfigurations(char[] vector){
        int emptySpaces = countSpaces(vector);
        int positions =
                emptySpaces >= NUMBER_OF_NEW_CARDS ?  (emptySpaces-NUMBER_OF_NEW_CARDS+1) : 0;

        long permutations = FACTORIAL_5 * positions;

        return permutations;
    }

    public static char[][] convertLineStreamToGrid(Stream<String> fileLineStream){
        ArrayList<char[]> gridList = new ArrayList<>();

        fileLineStream.forEach( line -> gridList.add(line.toCharArray()) );

        return gridList.toArray(new char[][]{});
    }

    /**
     * Reflects a grid across the diagonal. May modify the input array.
     *
     * @param grid An input grid to be reflected across the cartesian diagonal.
     *             Warning: To save space, this function will mutate the input
     *             parameter if it is a square grid.
     * @return The reflected grid   */
    public static char[][] reflectGridAcrossDiagonal(char[][] grid){
        char[][] reflectedGrid;

        /* If we have a square grid, we can save some space at the cost
           of mutating the two-dimensional input array 'grid'. */
        if(grid.length == grid[0].length){
            reflectedGrid = grid;

            for (int i = 0; i < grid.length; i++) {
                for (int j = i; j < grid[0].length; j++) {
                    char temporary = grid[i][j];
                    reflectedGrid[i][j] = grid[j][i];
                    reflectedGrid[j][i] = temporary;
                }
            }
        }
        else {
            reflectedGrid = new char[grid[0].length][grid.length];

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    reflectedGrid[i][j] = grid[j][i];
                    reflectedGrid[j][i] = grid[i][j];
                }
            }
        }

        return reflectedGrid;
    }

    public static void printGrid(char[][] grid){
        int lineNumber = 0;
        for(char[] vector : grid)
            out.printf("%3d: %3d %4d  %s\n",
                    lineNumber++, countSpaces(vector), countConfigurations(vector), Arrays.toString(vector));

    }

}