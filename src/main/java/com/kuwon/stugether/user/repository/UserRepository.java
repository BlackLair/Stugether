package com.kuwon.stugether.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kuwon.stugether.user.domain.User;

@Mapper
public interface UserRepository {
	public int selectExistByLoginId(@Param("loginId") String loginId);
	public int selectExistByNickname(@Param("nickname") String nickname);
	public int insertUser(@Param("loginId") String loginId
						, @Param("password") String password
						, @Param("nickname") String nickname
						, @Param("email") String email);
	public User selectByLoginIdAndPassword(@Param("loginId") String loginId
										, @Param("password") String password);
}