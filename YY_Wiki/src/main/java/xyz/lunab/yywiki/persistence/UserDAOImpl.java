package xyz.lunab.yywiki.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import xyz.lunab.yywiki.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Inject
	private SqlSession sqlSession; 
	
	private static final String NAMESPACE = "xyz.lunab.yywiki.mapper.UserMapper";
	
	@Override
	public UserVO selectUserId(String id) {
		UserVO vo = sqlSession.selectOne(NAMESPACE + ".selectUserId", id);
		return vo;
	}

	@Override
	public void insertUser(UserVO vo) {
		sqlSession.insert(NAMESPACE + ".insertUser", vo);
	}
	
}
