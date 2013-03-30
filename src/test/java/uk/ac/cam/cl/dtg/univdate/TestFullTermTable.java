package uk.ac.cam.cl.dtg.univdate;

import org.joda.time.Interval;
import org.junit.Assert;
import org.junit.Test;

import uk.ac.cam.cl.dtg.univdate.FullTermTable.Year;

public class TestFullTermTable {

	@Test
	public void testTermDuration() {
		for(int i=2011;i<2030;++i) {
			Year y = FullTermTable.table.get(i);
			Interval mich = new Interval(y.mich.start,y.mich.end);
			Interval lent = new Interval(y.lent.start,y.lent.end);
			Interval easter = new Interval(y.easter.start,y.easter.end);
			Assert.assertEquals(mich.toDuration().getStandardDays(),60);
			Assert.assertEquals(lent.toDuration().getStandardDays(),59);
			Assert.assertEquals(easter.toDuration().getStandardDays(),52);
			
		}
	}
	
}
