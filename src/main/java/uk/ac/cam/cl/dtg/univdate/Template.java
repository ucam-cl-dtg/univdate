package uk.ac.cam.cl.dtg.univdate;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.hibernate.Session;
import org.joda.time.Period;

import uk.ac.cam.cl.dtg.univdate.model.EventTemplate;
import uk.ac.cam.cl.dtg.univdate.model.RelativeDate;

import com.google.common.collect.ImmutableMap;
import com.googlecode.htmleasy.RedirectException;
import com.googlecode.htmleasy.ViewWith;

@Path("/template")
public class Template {

	@GET
	@ViewWith("/soy/calendar.eventtemplates")
	public Map<String, ?> view(@Context HttpServletRequest request) {
		Session session = DBUtil.getSession(request);
		session.beginTransaction();
		List<EventTemplate> result = DBUtil.selectAll(session,
				EventTemplate.class);
		session.getTransaction().commit();
		session.close();
		return ImmutableMap.of("list", result);
	}

	@POST
	@Path("/new")
	public void newEventTemplate(@Context HttpServletRequest request,
			@FormParam("title") String title,
			@FormParam("location") String location,
			@FormParam("organiser") String organiser,
			@FormParam("tags") String tags, @FormParam("term") int term,
			@FormParam("day") int day, @FormParam("hourOfDay") int hourOfDay,
			@FormParam("minute") int minute) {
		RelativeDate startTime = new RelativeDate(term, day, hourOfDay, minute);
		Period period = Period.hours(1);
		EventTemplate template = new EventTemplate(title, location, organiser,
				tags, startTime, period);
		Session session = DBUtil.getSession(request);
		session.beginTransaction();
		session.save(template);
		session.getTransaction().commit();
		session.close();
		throw new RedirectException("/template");
	}

}
