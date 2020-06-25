package xyz.lunab.yywiki;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
//@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DBConnectionTest {
	
	@Inject 
	private SqlSessionFactory sqlFactory; 
	
	@Inject
	private DataSource ds;
	
//	@Test 
//	public void testFactory(){ 
//		System.out.println(sqlFactory); 
//	} 
//	@Test 
//	public void testSession() throws Exception{ 
//		try(SqlSession session = sqlFactory.openSession()){ 
//			System.out.println(session); 
//		}
//		catch(Exception e){ e.printStackTrace(); } 
//	}
	
	@Test
	public void testDs() {
		try(Connection con = ds.getConnection()){ System.out.println(con); }catch(Exception e){ e.printStackTrace(); }
	}
	

}
