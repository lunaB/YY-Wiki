package xyz.lunab.yywiki.sequrity.service;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import xyz.lunab.yywiki.domain.UserDetailsVO;
import xyz.lunab.yywiki.domain.UserVO;

public class UserDetailsServiceImpl implements UserDetailsService {

	private SqlSessionTemplate sqlSession;
	
	private static final String NAMESPACE = "xyz.lunab.yywiki.mapper.UserMapper";
	
	public UserDetailsServiceImpl(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		
		UserVO vo = sqlSession.selectOne(NAMESPACE + ".selectUserId", id);
		if(vo == null) { 
			throw new UsernameNotFoundException(id);
		}
		List<GrantedAuthority> role = new ArrayList<GrantedAuthority>();
		role.add(new SimpleGrantedAuthority(vo.getRole()));
		
		UserDetails user = new User(id, vo.getPass(), role);
		
		// http://niees.tistory.com/18
		return new UserDetailsVO(vo.getId(), vo.getPass(), true, true, true, true, role, vo.getName());
	}

}
