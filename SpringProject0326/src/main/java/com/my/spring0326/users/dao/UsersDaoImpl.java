package com.my.spring0326.users.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.spring0326.users.dto.UsersDto;

@Repository
public class UsersDaoImpl implements UsersDao{
	
	//핵심 의존 객체(DI)
	@Autowired
	private SqlSession session;
	
	@Override
	public void updateProfile(UsersDto dto) {
		/*
		 *  mapper namespace => users
		 *  sql id => updateProfile
		 *  parameterType => UsersDto
		 */
		session.update("users.updateProfile", dto);
	}

	@Override
	public boolean isExist(String id) {
		/*
		 *  mapper namespace => users
		 *  sql id => isExist
		 *  parameterType => String
		 *  resultType => UsersDto
		 */
		// id 가 존재하면(이미등록된아이디) null 이 아니고 존재하지 않으면 null  이다.
		UsersDto dto=session.selectOne("users.isExist", id);
		if(dto==null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean updatePwd(UsersDto dto) {
		/*
		 *  mapper namespace => users
		 *  sql id => updatePwd
		 *  parameterType => UsersDto
		 */
		int count=session.update("users.updatePwd", dto);
		if(count==0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public void update(UsersDto dto) {
		/*
		 *  mapper namespace => users
		 *  sql id => update
		 *  parameterType => UsersDto
		 */
		session.update("users.update", dto);
	}

	@Override
	public void delete(String id) {
		/*
		 *  mapper namespace => users
		 *  sql id => delete
		 *  parameterType => String
		 */
		session.delete("users.delete", id);
	}

	@Override
	public UsersDto getData(String id) {
		/*
		 *  mapper namespace => users
		 *  sql id => getData
		 *  parameterType => String
		 *  resultType => UsersDto
		 */
		UsersDto dto=session.selectOne("users.getData", id);
		return dto;
	}

	@Override
	public void insert(UsersDto dto) {
		/*
		 *  mapper namespace => users
		 *  sql id => insert
		 *  parameterType => UsersDto
		 */
		session.insert("users.insert", dto);
	}

	@Override
	public String getPwd(String id) {
		//아이디를 이용해서 저장된 비밀번호를  SELECT 해서 
		String pwd=session.selectOne("users.getPwd", id);
		//리턴해준다.
		return pwd;
	}

}



