package xyz.lunab.yywiki.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import xyz.lunab.yywiki.domain.WikiContentVO;

@Repository
public class WikiContentDAOImpl implements WikiContentDAO{

	@Inject
	private SqlSession sqlSession; 
	
	private static final String NAMESPACE = "xyz.lunab.yywiki.mapper.WikiMapper";
	
	
	@Override
	public WikiContentVO selectCurrentContent(int wiki_id) {
		return sqlSession.selectOne(NAMESPACE+".selectCurrentContent", wiki_id);
	}

	@Override
	public void insertContent(WikiContentVO vo) {
		sqlSession.insert(NAMESPACE+".insertContent", vo);
	}

}
