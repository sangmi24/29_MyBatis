package com.kh.mybatis.member.model.service;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.common.template.Template;
import com.kh.mybatis.member.model.dao.MemberDao;
import com.kh.mybatis.member.model.vo.Member;

public class MemberServiceImpl implements MemberService{

	//MemberDao에 공통적으로 수행할 코드가 딱히 없으므로
	// 매번 객체를 생성할 필요가 없음 => MemberDao객체를 전역변수로 빼줄 것
	private MemberDao memberDao = new MemberDao();
	
	@Override
	public int insertMember(Member m) {
		
		/*
		 * 기존 JDBC
		 * Connection conn = JDBCTemplate.getConnection();
		 * int result = new MemberDao().insertMember(conn,m);
		 * 
		 * if(result >0 ) {
		 *    JDBCTemplate.commit();
		 * }
		 * else{
		 *    JDBCTemplate.rollback();
		 * }
		 * JDBCTemplate.close(conn);
		 * 
		 * return result;
		 * 
		 */
		SqlSession sqlSession = Template.getSqlSession();
		// Template 클래스에서 SqlSession 객체를 만들 때
		// mybatis-config.xml 파일을 읽어들이면서 만들어줌
		//=> 설정파일 자체에서 오류가 발생하면 실행이 안됨!
		
		int result = new MemberDao().insertMember(sqlSession,m);
		
		// 결과에 따른 트랜잭션 처리
		if(result >0 ) {
			
			sqlSession.commit();
		}
		// 이 시점에서 result 가 0보다 작거나 같다면 : insert된  행의 갯수가 0이라는 뜻
		// => 트랜잭션1개에 쿼리문1개만 들어있는 상황 == 단일프로세스
		// => 이 경우에만 else블럭 (rollback처리 ) 을 생략해도 됨
		//=> 단, 한 트랜잭션 안에 쿼리문이 여러개 들어있을 경우에는 쿼리문 한개당 롤백처리 꼭 해줘야 함!!
		
		sqlSession.close();
		
		return result;
		
	}

	@Override
	public Member loginMember(Member m) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		Member loginUser = new MemberDao().loginMember(sqlSession,m);
		
		sqlSession.close();
		return loginUser;
	}

	@Override
	public int updateMember(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

}
