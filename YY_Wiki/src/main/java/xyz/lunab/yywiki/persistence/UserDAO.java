package xyz.lunab.yywiki.persistence;

import xyz.lunab.yywiki.domain.UserVO;

public interface UserDAO {
	public UserVO selectUserId(String id);
	public void insertUser(UserVO vo);
}
