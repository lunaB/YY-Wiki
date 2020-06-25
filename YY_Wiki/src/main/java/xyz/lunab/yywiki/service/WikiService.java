package xyz.lunab.yywiki.service;

import java.util.List;

import xyz.lunab.yywiki.domain.WikiContentVO;
import xyz.lunab.yywiki.domain.WikiVO;
import xyz.lunab.yywiki.dto.WikiHeadDTO;
import xyz.lunab.yywiki.dto.WikiWrapperDTO;

public interface WikiService {
	
	public WikiWrapperDTO selectWiki(String title);
	public void insertContent(WikiContentVO vo);
	public int selectPermission(int wiki_id);
	public void insertWiki(WikiVO vo);
	public List<WikiVO> selectRandomWiki();
	public List<WikiHeadDTO> selectUpdateWikiList();
}
