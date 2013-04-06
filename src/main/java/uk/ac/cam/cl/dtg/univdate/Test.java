package uk.ac.cam.cl.dtg.univdate;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.hibernate.Session;
import org.joda.time.Period;

import uk.ac.cam.cl.dtg.univdate.model.EventTemplate;
import uk.ac.cam.cl.dtg.univdate.model.RelativeDate;

import com.googlecode.htmleasy.ViewWith;

@Path("/test")
public class Test {

	@Context
	HttpServletRequest servletRequest;

	class SimpleThing {
		public String foo;

		public SimpleThing(String foo) {
			super();
			this.foo = foo;
		}

	}

	@GET
	@Path("/raw-text")
	@ViewWith("/soy/test.view")
	public SimpleThing test() {
		Session session = HibernateSessionRequestFilter
				.openSession(servletRequest);
		session.beginTransaction();
		RelativeDate start = new RelativeDate(RelativeDate.TERM_MICHAELMAS, 4,
				9, 15);
		Period period = Period.hours(1);

		EventTemplate template = new EventTemplate("Test Title",
				"Test location", "Test organiser", "tag1,tag2", start, period);

		session.save(template);
		session.getTransaction().commit();
		session.close();

		return new SimpleThing("ff");

	}

}
