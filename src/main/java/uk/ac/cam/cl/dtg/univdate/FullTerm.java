package uk.ac.cam.cl.dtg.univdate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.joda.time.DateTime;

import uk.ac.cam.cl.dtg.univdate.FullTermTable.Term;
import uk.ac.cam.cl.dtg.univdate.FullTermTable.Year;

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
 * 3. The dates on which Full Terms begin and end shall be as shown in the table
 * appended to these regulations.
 * 
 * @author acr31
 * 
 */
@Path("/fullterm")
public class FullTerm {

	@GET
	@Path("/{year}/{term}/{point}")
	public Response getDate(@PathParam("year") int year,
			@PathParam("term") String term, @PathParam("point") String point) {

		Year entry = FullTermTable.table.get(year);
		if (entry == null) {
			return Response.status(404).build();
		}

		Term detail;
		if (term.equals("mich")) {
			detail = entry.mich;
		} else if (term.equals("lent")) {
			detail = entry.lent;
		} else if (term.equals("easter")) {
			detail = entry.easter;
		} else {
			return Response.status(404).build();
		}

		DateTime date;
		if (point.equals("start")) {
			date = detail.start;
		} else if (point.equals("end")) {
			date = detail.end;
		} else {
			return Response.status(404).build();
		}

		return Response.ok((date.toDate().getTime() + "")).type("text/plain")
				.build();
	}

	@GET
	@Path("mich/end")
	public String getMichEnd(@PathParam("year") int year) {
		return FullTermTable.table.get(year).mich.end.toDate().getTime() + "";
	}

	@GET
	@Path("lent/start")
	public String getLentStart(@PathParam("year") int year) {
		return FullTermTable.table.get(year).lent.start.toDate().getTime() + "";
	}

	@GET
	@Path("lent/end")
	public String getLentEnd(@PathParam("year") int year) {
		return FullTermTable.table.get(year).lent.end.toDate().getTime() + "";
	}

	@GET
	@Path("easter/start")
	public String getEasterStart(@PathParam("year") int year) {
		return FullTermTable.table.get(year).easter.start.toDate().getTime()
				+ "";
	}

	@GET
	@Path("easter/end")
	public String getEasterEnd(@PathParam("year") int year) {
		return FullTermTable.table.get(year).easter.end.toDate().getTime() + "";
	}

}
