package xyz.lunab.yywiki.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import xyz.lunab.yywiki.domain.WikiContentVO;
import xyz.lunab.yywiki.domain.WikiVO;
import xyz.lunab.yywiki.dto.WikiHeadDTO;
import xyz.lunab.yywiki.dto.WikiWrapperDTO;
import xyz.lunab.yywiki.persistence.WikiContentDAO;
import xyz.lunab.yywiki.persistence.WikiDAO;

@Service
public class WikiServiceImpl implements WikiService {

	@Inject
	private WikiContentDAO wikiContentDao;

	@Inject
	private WikiDAO wikiDao;

	@Override
	public WikiWrapperDTO selectWiki(String title) {
		
		WikiVO wikiVO = wikiDao.selectWiki(title);
		if(wikiVO == null) {
			System.out.println(wikiVO);
			return null;
		}
		
		WikiContentVO wikiContentVO = wikiContentDao.selectCurrentContent(wikiVO.getWiki_id());
		System.out.println(wikiContentVO);
		WikiWrapperDTO wikiWrapperDTO = new WikiWrapperDTO();
		
		wikiWrapperDTO.setWiki_id(wikiVO.getWiki_id());
		wikiWrapperDTO.setTitle(wikiVO.getTitle());
		wikiWrapperDTO.setPermission_id(wikiVO.getPermission_id());
		
		if(wikiContentVO != null) {
			wikiWrapperDTO.setContent(wikiContentVO.getContent());
			wikiWrapperDTO.setRegdate(wikiContentVO.getRegdate());
		}
		
		return wikiWrapperDTO;
	}

	@Override
	public void insertContent(WikiContentVO vo) {
		wikiContentDao.insertContent(vo);
	}

	@Override
	public int selectPermission(int wiki_id) {
		return wikiDao.selectPermission(wiki_id);
	}

	@Override
	public void insertWiki(WikiVO vo) {
		wikiDao.insertWiki(vo);
	}

	@Override
	public List<WikiVO> selectRandomWiki() {
		return wikiDao.selectRandomWikiList();
	}

	@Override
	public List<WikiHeadDTO> selectUpdateWikiList() {
		// TODO Auto-generated method stub
		return wikiDao.selectUpdateWikiList();
	}
	
}
