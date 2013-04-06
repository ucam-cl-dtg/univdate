package uk.ac.cam.cl.dtg.univdate.model;

import java.util.Date;

import javax.persistence.Embeddable;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalTime;

import uk.ac.cam.cl.dtg.univdate.FullTermTable;
import uk.ac.cam.cl.dtg.univdate.FullTermTable.Term;
import uk.ac.cam.cl.dtg.univdate.FullTermTable.Year;
import uk.ac.cam.cl.dtg.univdate.InvalidRelativeDateSpecification;

@Embeddable
public class RelativeDate {

	public static final int TERM_MICHAELMAS = 0;
	public static final int TERM_LENT = 1;
	public static final int TERM_EASTER = 2;

	public static final int DAY_MONDAY = DateTimeConstants.MONDAY;
	public static final int DAY_TUESDAY = DateTimeConstants.TUESDAY;
	public static final int DAY_WEDNESDAY = DateTimeConstants.WEDNESDAY;
	public static final int DAY_THURSDAY = DateTimeConstants.THURSDAY;
	public static final int DAY_FRIDAY = DateTimeConstants.FRIDAY;
	public static final int DAY_SATURDAY = DateTimeConstants.SATURDAY;
	public static final int DAY_SUNDAY = DateTimeConstants.SUNDAY;

	private int term;
	private int day;

	private int hourOfDay;
	private int minute;

	public RelativeDate() {
	}

	/**
	 * @param term
	 *            Constant indicating which term should the event take place
	 * @param dayOfWeek
	 *            Constant indicating which day of the week
	 * @param week
	 *            The nth time that we see the relevant day once term starts. A
	 *            value of 0 indicates the first time, a value of -1 indicates a
	 *            day before full term starting.
	 * @param time
	 *            The time that during the day chosen
	 */
	public RelativeDate(int term, int day, int hourOfDay, int minute) {
		super();
		this.term = term;
		this.day = day;
		this.hourOfDay = hourOfDay;
		this.minute = minute;
	}

	public Date getTime(int academicYear)
			throws InvalidRelativeDateSpecification {
		Term termInfo = getTermInfo(academicYear);
		DateTime date = termInfo.start;
		date = date.plusDays(day);
		return date.toLocalDate().toDateTime(getTime()).toDate();
	}

	private Term getTermInfo(int academicYear)
			throws InvalidRelativeDateSpecification {
		Year year = FullTermTable.table.get(academicYear);
		switch (term) {
		case TERM_MICHAELMAS:
			return year.mich;
		case TERM_LENT:
			return year.lent;
		case TERM_EASTER:
			return year.easter;
		default:
			throw new InvalidRelativeDateSpecification();
		}
	}

	public int getTerm() {
		return term;
	}

	public String getTermName() {
		switch (term) {
		case TERM_MICHAELMAS:
			return "Michaelmas";
		case TERM_LENT:
			return "Lent";
		case TERM_EASTER:
			return "Easter";
		default:
			return "UNKONWN";
		}
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHourOfDay() {
		return hourOfDay;
	}

	public void setHourOfDay(int hourOfDay) {
		this.hourOfDay = hourOfDay;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public LocalTime getTime() {
		return new LocalTime(hourOfDay, minute);
	}

}