package xyz.lunab.yywiki.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import xyz.lunab.yywiki.domain.WikiVO;
import xyz.lunab.yywiki.dto.WikiHeadDTO;

@Repository
public class WikiDAOImpl implements WikiDAO {

	@Inject
	private SqlSession sqlSession; 
	
	private static final String NAMESPACE = "xyz.lunab.yywiki.mapper.WikiMapper";
	
	
	@Override
	public WikiVO selectWiki(String title) {
		return sqlSession.selectOne(NAMESPACE+".selectWiki", title);
	}

	@Override
	public void insertWiki(WikiVO vo) {
		sqlSession.insert(NAMESPACE+".insertWiki", vo);
	}

	@Override
	public int selectPermission(int wiki_id) {
		return sqlSession.selectOne(NAMESPACE+".selectPermission", wiki_id);
	}

	@Override
	public List<WikiVO> selectRandomWikiList() {
		return sqlSession.selectList(NAMESPACE+".selectRandomWikiList");
	}

	@Override
	public List<WikiHeadDTO> selectUpdateWikiList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE+".selectUpdateWikiList");
	}

}
