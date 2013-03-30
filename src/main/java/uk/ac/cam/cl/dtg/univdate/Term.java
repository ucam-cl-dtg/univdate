package uk.ac.cam.cl.dtg.univdate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * Section 8: Terms and Long Vacation
 *  
 * The Michaelmas Term shall begin on 1 October and shall consist of eighty
 * days, ending on 19 December. The Lent Term shall begin on 5 January and shall
 * consist of eighty days, ending on 25 March or in any leap year on 24 March.
 * The Easter Term shall begin on 10 April and shall consist of seventy days
 * ending on 18 June, provided that in any year in which full Easter Term begins
 * on or after 22 April the Easter Term shall begin on 17 April and end on 25
 * June.
 * 
 * @author acr31
 * 
 */
@Path("/term/{year}")
public class Term {
	private int MICH_MONTH = 10;
	private int MICH_DAY = 1;
	private int MICH_DURATION_DAYS = 80;
	
	private int LENT_MONTH = 1;
	private int LENT_DAY = 5;
	private int LENT_DURATION_DAYS = 80;
	
	private int EASTER_MONTH = 4;
	private int EASTER_DAY = 10;
	private int EASTER_DURATION_DAYS = 70;
	
	
	@GET
	@Path("mich/start")
	public String getMichStart(@PathParam("year") int year) {
		DateTime t = new DateTime(year,MICH_MONTH,MICH_DAY,0,0);
		DateTimeFormatter f = ISODateTimeFormat.dateTime();
		return f.print(t);
	}

	@GET
	@Path("mich/end")
	public String getMichEnd(@PathParam("year") int year) {
		DateTime t = new DateTime(year,MICH_MONTH,MICH_DAY,0,0);
		t = t.plus(Days.days(MICH_DURATION_DAYS)).minus(Seconds.seconds(1));
		DateTimeFormatter f = ISODateTimeFormat.dateTime();
		return f.print(t);
	}

	@GET
	@Path("lent/start")
	public String getLentStart(@PathParam("year") int year) {
		DateTime t = new DateTime(year+1,LENT_MONTH,LENT_DAY,0,0);
		DateTimeFormatter f = ISODateTimeFormat.dateTime();
		return f.print(t);
	}

	@GET
	@Path("lent/end")
	public String getLentEnd(@PathParam("year") int year) {
		DateTime t = new DateTime(year+1,LENT_MONTH,LENT_DAY,0,0);
		t = t.plus(Days.days(LENT_DURATION_DAYS)).minus(Seconds.seconds(1));
		DateTimeFormatter f = ISODateTimeFormat.dateTime();
		return f.print(t);
	}

	@GET
	@Path("easter/start")
	public String getEasterStart(@PathParam("year") int year) {
		DateTime t = new DateTime(year+1,EASTER_MONTH,EASTER_DAY,0,0);
		DateTimeFormatter f = ISODateTimeFormat.dateTime();
		return f.print(t);
	}

	@GET
	@Path("easter/end")
	public String getEasterEnd(@PathParam("year") int year) {
		DateTime t = new DateTime(year+1,EASTER_MONTH,EASTER_DAY,0,0);
		t = t.plus(Days.days(EASTER_DURATION_DAYS)).minus(Seconds.seconds(1));
		DateTimeFormatter f = ISODateTimeFormat.dateTime();
		return f.print(t);
	}

	
	
}
