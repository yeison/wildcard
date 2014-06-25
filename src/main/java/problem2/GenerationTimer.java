package problem2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.System.out;

public class GenerationTimer {
    private static final int[] GENERATION_TIME = new int[]{9, 10, 21, 20, 7, 11, 4, 15, 7, 7, 14, 5, 20, 6, 29, 8, 11,
                                                           19, 18, 22, 29, 14, 27, 17, 6, 22, 12, 18, 18, 30};

    private static final int[] OVERHEAD = new int[]{21, 16, 19, 26, 26, 7, 1, 8, 17, 14, 15, 25, 20, 3, 24, 5, 28, 9, 2,
                                                   14, 9, 25, 15, 13, 15, 9, 6, 20, 27, 22};

    private static final int BUDGET = 2912;


    public static void main(String[] args){
        ArrayList<WildCard> cardList = new ArrayList<>();
        int sumGen = 0;
        int sumOverhead = 0;

        for (int i = 0; i < GENERATION_TIME.length; i++) {
            sumGen += GENERATION_TIME[i];
            sumOverhead += OVERHEAD[i];

            cardList.add(new WildCard(GENERATION_TIME[i], OVERHEAD[i]));
        }

        cardList.sort(Comparator.<WildCard>naturalOrder());


        int maxCount = getMaxCardCountEstimate(cardList);
        out.printf("\nThe estimated max card count is %d for a budget of %d\n", maxCount, BUDGET);

    }

    /**
     * The goal of this function is to return the best estimate of max card count by calculating
     * the impact of the overhead to the total cost.
     * @param cardList A list of {@link} problem2.WildCard instances.
     * @return The maximum number of cards that can be included in the set.
     */
    public static int getMaxCardCountEstimate(List<WildCard> cardList){
        int cardCount = 0;
        int totalOverhead = 0;
        int totalGen = 0;
        for (WildCard c : cardList){
            cardCount++;

            totalGen += c.getGenerationTime();
            totalOverhead += c.getOverhead();

            int maxCostEstimate = totalOverhead * (cardCount - 1) + totalGen;


            out.printf("Card %2d:   total cost:%5d   %s\n", cardCount, maxCostEstimate, c);

            if(maxCostEstimate > BUDGET){
                return cardCount-1;
            }

        }

        return cardCount;
    }

}