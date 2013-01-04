package domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

import java.util.List;

import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Test;

public class BugTest {

	@Test
	public void shouldSelectAServer() {
		@SuppressWarnings({ "deprecation", "unchecked" })
		List<Server> result = new AnnotationConfiguration().configure().buildSessionFactory().openSession().createQuery("from domain.Server").list();
		assertThat(result, not(empty()));
	}
}
