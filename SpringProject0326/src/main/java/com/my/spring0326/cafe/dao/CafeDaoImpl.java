package com.my.spring0326.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.spring0326.cafe.dto.CafeDto;
import com.my.spring0326.exception.DBFailException;

@Repository
public class CafeDaoImpl implements CafeDao{
	//핵심 의존객체 DI
	@Autowired
	private SqlSession session;
	
	@Override
	public void insert(CafeDto dto) {
		session.insert("cafe.insert", dto);
	}

	@Override
	public void update(CafeDto dto) {
		session.update("cafe.update", dto);
	}

	@Override
	public void delete(int num) {
		//삭제된 row 의 갯수를 얻어낸다 
		int count=session.delete("cafe.delete", num);
		if(count==0) {//0 이면 삭제 실패이다.
			throw new DBFailException(num+" 번 글을 삭제 할수가 없습니다.");
		}
	}

	@Override
	public CafeDto getData(int num) {
		CafeDto dto=session.selectOne("cafe.getData", num);
		return dto;
	}

	@Override
	public List<CafeDto> getList(CafeDto dto) {
		/*
		 *  parameterType => CafeDto
		 *  
		 *  parameterType  에는 페이징 처리를 위한 startRowNum 과 endRowNum 이 들어 있고
		 *  
		 *  title  검색이면 title  필드에 검색 키워드가 들어있다.
		 *  writer 검색이면 writer 필드에 검색 키워드가 들어있다.
		 *  title+content 검색이면 title and content 필드에 검색 키워드가 들어 있다.
		 *  검색 키워드가 없으면 title,writer,content 필드는 모두 null 이다.
		 *  
		 *  resultType => CafeDto 
		 */
		List<CafeDto> list=session.selectList("cafe.getList", dto);
		return list;
	}

	@Override
	public int getCount(CafeDto dto) {
		/*
		 *  parameterType => CafeDto
		 *  parameterType  에는 검색키워드가 존재한다면 들어 있다.
		 *  
		 *  resultType => int
		 */
		int count=session.selectOne("cafe.getCount", dto);
		return count;
	}

	@Override
	public void addViewCount(int num) {
		session.update("cafe.addViewCount", num);
	}

}




