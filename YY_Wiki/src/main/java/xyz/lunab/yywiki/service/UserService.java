package xyz.lunab.yywiki.service;

import xyz.lunab.yywiki.domain.UserVO;

public interface UserService {
	public UserVO selectUserById(String id);
	public void insertUser(UserVO vo);
}
