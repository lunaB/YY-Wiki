package xyz.lunab.yywiki;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xyz.lunab.yywiki.persistence.UserDAO;
import xyz.lunab.yywiki.persistence.WikiDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DaoTest {
	
	@Inject
	UserDAO a;
	
	@Inject
	WikiDAO b;
	
	
	@Test
	public void daoTest() {
		System.out.println(b.selectRandomWikiList());
	}
	
}
