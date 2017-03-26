package ozlympics;

import static org.junit.Assert.*;
import org.junit.Test;

public class AthleteTest {

	@Test
	public void test() throws Exception {
		Athlete sam = new Athlete("Samwise", "Samwise Gamgee", 19, "VIC");
		System.out.println(sam.toString());
		
		Athlete swim = new Swimmer("JMswim", "John Malkovich", 26, "QLD");
		System.out.println(swim.toString());
			
		for (int i=0; i<1000; i++) {
			int samPoints = sam.compete("Swimming");
			System.out.println(samPoints);
			assertTrue(samPoints <= 200 && samPoints >=100);

			int swimPoints = swim.compete("Swimming");
			System.out.println(swimPoints);
			assertTrue(swimPoints <= 200 && swimPoints >=100);
			
			samPoints = sam.compete("Running");
			System.out.println(samPoints);	
			assertTrue(samPoints <= 20 && samPoints >=10);
		
			samPoints = sam.compete("Cycling");
			System.out.println(samPoints);	
			assertTrue(samPoints <= 800 && samPoints >=500);
			//	System.out.println(swim.compete("Running")); //Shouldn't work
		}
	}
}
