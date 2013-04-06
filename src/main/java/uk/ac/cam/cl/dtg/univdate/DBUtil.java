package uk.ac.cam.cl.dtg.univdate;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

public class DBUtil {

	public static Session getSession(HttpServletRequest request) {
		return HibernateSessionRequestFilter.openSession(request);
	}

	public static <T> List<T> selectAll(Session session, Class<T> clazz) {
		@SuppressWarnings("unchecked")
		List<T> result = (List<T>) session.createQuery(
				"from " + clazz.getSimpleName()).list();
		return result;

	}
}
