package uk.ac.cam.cl.dtg.univdate;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.junit.Assert;
import org.junit.Test;

import uk.ac.cam.cl.dtg.univdate.FullTermTable.Term;
import uk.ac.cam.cl.dtg.univdate.FullTermTable.Year;

public class TestFullTermTable {

	@Test
	public void testTermDuration() {
		for (int i = 2011; i < 2030; ++i) {
			Year y = FullTermTable.table.get(i);

			checkTerm(y.mich, 60);
			checkTerm(y.lent, 60);
			checkTerm(y.easter, 53);
		}
	}

	private void checkTerm(Term t, int expectedDays) {
		Assert.assertEquals(DateTimeConstants.TUESDAY, t.start.getDayOfWeek());
		Assert.assertEquals(DateTimeConstants.FRIDAY, t.end.getDayOfWeek());
		Assert.assertEquals(expectedDays, countDays(t.start, t.end));
	}

	/**
	 * We need the number of days rather than the number of elapsed milliseconds
	 * divided by the number in a day - timezone changes ete... To avoid
	 * confusion about what constitutes a full day we count the number of
	 * lunchtimes (noon).
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	private int countDays(DateTime start, DateTime end) {
		int count = 0;
		Assert.assertEquals(start.getHourOfDay(),0);
		Assert.assertEquals(start.getMinuteOfHour(),0);
		start = start.plusHours(12);
		while (start.isBefore(end)) {
			count++;
			start = start.plusDays(1);
		}
		return count;
	}

}
