package xyz.lunab.yywiki.persistence;

import java.util.List;

import xyz.lunab.yywiki.domain.WikiVO;
import xyz.lunab.yywiki.dto.WikiHeadDTO;

public interface WikiDAO {
	public WikiVO selectWiki(String title);
	public void insertWiki(WikiVO vo);
	public int selectPermission(int wiki_id);
	public List<WikiVO> selectRandomWikiList();
	public List<WikiHeadDTO> selectUpdateWikiList();
}
