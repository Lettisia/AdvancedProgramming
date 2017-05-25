package Assign2.Algorithm;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AthleteTest {
	Athlete swim;
	Athlete sam;
	
	@Before
	public void runFirst() {
		sam = new Athlete("Samwise", "Samwise Gamgee", 19, "VIC");
		assertTrue("SuperAthlete, Samwise, Samwise Gamgee, 19, VIC, ".equals(sam.toString()));
		
		swim = new Swimmer("JMswim", "John Malkovich", 26, "QLD");
		assertTrue("Swimmer, JMswim, John Malkovich, 26, QLD, ".equals(swim.toString()));
	}
	
	@Test
	public void test() throws Exception {
		for (int i=0; i<1000; i++) {
			int samPoints = sam.compete("Swimming");
			assertTrue(samPoints <= 200 && samPoints >=100);

			int swimPoints = swim.compete("Swimming");
			assertTrue(swimPoints <= 200 && swimPoints >=100);
			
			samPoints = sam.compete("Running");
			assertTrue(samPoints <= 20 && samPoints >=10);
		
			samPoints = sam.compete("Cycling");
			assertTrue(samPoints <= 800 && samPoints >=500);
		}
	}
	
	@Test(expected = WrongSportException.class)	
	public void wrongTest() throws WrongSportException {
		int swimPoints = swim.compete("Running"); 
	}
}
