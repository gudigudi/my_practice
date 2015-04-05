package examples.test;

import org.junit.Test;
import examples.Batter;
import examples.Batter.AtBatResults;
/**
 * A class that checks an implementation of the Batter
 * interface. An instance of the class will be initialized with
 * an array of AtBatResult objects that will be used to generate
 * statistics in a Batter instance. The tests will then ensure
 * that the statistics reported are correct.
 * For simplicity's sake, the current implementation
 * only checks the Slugging Average.
 */
public class CheckBatter {
    private Batter.AtBatResult[] testData;

    public CheckBatter(Batter.AtBatResult[] data) {
        testData = data;
    }
    @Test
    public void testGetSlugging() {
        for (int i = 0; i < testBatters.length; i++) {
            float testAvg = testBatters[i].getSlugging();
            float calcAvg = (float)testBatters[i].getTotalBases()
                          / (float)testBatters[i].getAtBats();
            assertEquals("Slugging test", testAvg, calcAvg, .02);
        }
    }
}
