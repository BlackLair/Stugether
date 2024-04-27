package com.kuwon.stugether.user.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kuwon.stugether.user.domain.User;

@Mapper
public interface UserRepository {
	public int selectExistByLoginId(@Param("loginId") String loginId);
	public int selectExistByNickname(@Param("nickname") String nickname);
	public int insertUser(User user);
	public User selectByLoginIdAndPassword(@Param("loginId") String loginId
										, @Param("password") String password);
	
	public User selectById(@Param("userId") int userId);
	
	public List<User> selectUserByNickname(@Param("search") String search);
}
