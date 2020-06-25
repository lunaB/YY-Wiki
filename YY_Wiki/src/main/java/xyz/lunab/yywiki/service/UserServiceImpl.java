package xyz.lunab.yywiki.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import xyz.lunab.yywiki.domain.UserVO;
import xyz.lunab.yywiki.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO dao;

	@Override
	public UserVO selectUserById(String id) {
		return dao.selectUserId(id);
	}

	@Override
	public void insertUser(UserVO vo) {
		dao.insertUser(vo);
	}

}
