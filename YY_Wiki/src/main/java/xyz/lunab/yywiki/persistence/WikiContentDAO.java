package xyz.lunab.yywiki.persistence;

import xyz.lunab.yywiki.domain.WikiContentVO;

public interface WikiContentDAO {
	
	public WikiContentVO selectCurrentContent(int wiki_id);
	public void insertContent(WikiContentVO vo);
	
}
