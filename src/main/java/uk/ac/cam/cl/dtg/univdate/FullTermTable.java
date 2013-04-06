package uk.ac.cam.cl.dtg.univdate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

/**
 * Section 8. TERMS AND LONG VACATION
 * 
 * 3. The dates on which Full Terms begin and end shall be as shown in the table
 * appended to these regulations.
 * 
 * @author acr31
 * 
 */
public class FullTermTable {

	public static final Map<Integer, Year> table = Collections
			.unmodifiableMap(init());

	public static class Year {
		public final Term mich;
		public final Term lent;
		public final Term easter;

		public Year(int startingYear, int michStartDay, int michEndDay,
				int lentStartDay, int lentEndDay, int easterStartDay,
				int easterEndDay) {
			super();

			int michEndMonth = michEndDay < 15 ? 12 : 11;
			DateTime michStart = new DateTime(startingYear, 10, michStartDay,
					0, 0);
			DateTime michEnd = new DateTime(startingYear, michEndMonth,
					michEndDay, 0, 0).plusDays(1).minusMillis(1);
			this.mich = new Term(michStart, michEnd);

			DateTime lentStart = new DateTime(startingYear + 1, 1,
					lentStartDay, 0, 0);
			DateTime lentEnd = new DateTime(startingYear + 1, 3, lentEndDay, 0,
					0).plusDays(1).minusMillis(1);
			this.lent = new Term(lentStart, lentEnd);

			DateTime easterStart = new DateTime(startingYear + 1, 4,
					easterStartDay, 0, 0);
			DateTime easterEnd = new DateTime(startingYear + 1, 6,
					easterEndDay, 0, 0).plusDays(1).minusMillis(1);
			this.easter = new Term(easterStart, easterEnd);
		}
	}

	public static class Term {
		public final DateTime start;
		public final DateTime end;

		public Term(DateTime start, DateTime end) {
			super();
			this.start = start;
			this.end = end;
		}
	}

	private static Map<Integer, Year> init() {
		Map<Integer, Year> t = new HashMap<Integer, Year>();
		t.put(2011, new Year(2011, 4, 2, 17, 16, 24, 15));
		t.put(2012, new Year(2012, 2, 30, 15, 15, 23, 14));
		t.put(2013, new Year(2013, 8, 6, 14, 14, 22, 13));
		t.put(2014, new Year(2014, 7, 5, 13, 13, 21, 12));
		t.put(2015, new Year(2015, 6, 4, 12, 11, 19, 10));
		t.put(2016, new Year(2016, 4, 2, 17, 17, 25, 16));
		t.put(2017, new Year(2017, 3, 1, 16, 16, 24, 15));
		t.put(2018, new Year(2018, 2, 30, 15, 15, 23, 14));
		t.put(2019, new Year(2019, 8, 6, 14, 13, 21, 12));
		t.put(2020, new Year(2020, 6, 4, 19, 19, 27, 18));
		t.put(2021, new Year(2021, 5, 3, 18, 18, 26, 17));
		t.put(2022, new Year(2022, 4, 2, 17, 17, 25, 16));
		t.put(2023, new Year(2023, 3, 1, 16, 15, 23, 14));
		t.put(2024, new Year(2024, 8, 6, 21, 21, 29, 20));
		t.put(2025, new Year(2025, 7, 5, 20, 20, 28, 19));
		t.put(2026, new Year(2026, 6, 4, 19, 19, 27, 18));
		t.put(2027, new Year(2027, 5, 3, 18, 17, 25, 16));
		t.put(2028, new Year(2028, 3, 1, 16, 16, 24, 15));
		t.put(2029, new Year(2029, 2, 30, 15, 15, 23, 14));
		return t;
	}
}
