package com.dopaming.www.member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dopaming.www.acorn.AcornVO;
import com.dopaming.www.admin.recash.ReCashVO;

@Repository
public class MyBoardDAO {

	//SqlSessionTemplate객체가 모든 JDBC처리를 대신 해줌
	@Autowired
	SqlSessionTemplate mybatis;
	
	//내가 받은 자료 조회
	public List<MyBoardVO> getDownList(MyBoardVO vo){
		System.out.println("===> Mybatis로  getDownList() 기능 처리");
		return mybatis.selectList("MyBoardDAO.getDownList", vo);
	}
	
	//건수 조회(내가 받은 자료)
	public Integer getDownCount(MyBoardVO vo) {
		System.out.println("===> Mybatis로 getDownCount() 기능 처리");
		return mybatis.selectOne("MyBoardDAO.getDownCount", vo);
	}
	
	//업로드 관리
	public List<FileUploadVO> getUploadList(FileUploadVO vo){
		System.out.println("===> Mybatis로 getUploadList() 기능 처리");
		return mybatis.selectList("MyBoardDAO.getUploadList", vo);
	}
	
	//건수 조회(업로드 관리)
	public Integer getUploadCount(FileUploadVO vo) {
		System.out.println("===> Mybatis로 getDownCount() 기능 처리");
		return mybatis.selectOne("MyBoardDAO.getUploadCount", vo);
	}
	
	//게시글 삭제
	public void deleteBoard(FileUploadVO vo) {
		System.out.println("===> Mybatis로 deleteBoard() 기능 처리");
		mybatis.delete("MyBoardDAO.deleteBoard",vo);
	}
	
	//업로드 파일 삭제
	public void deleteUpload(FileUploadVO vo) {
		System.out.println("===> Mybatis로 deleteUpload() 기능 처리");
		mybatis.delete("MyBoardDAO.deleteUpload", vo);
	}
	
	//댓글 삭제
	public void deleteComments(FileUploadVO vo) {
		System.out.println("===> Mybatis로 deleteComments() 기능 처리");
		mybatis.delete("MyBoardDAO.deleteComments", vo);
	}
	
	//캐시 사용 내역 조회
	public List<AcornVO> getAcornList(MyAcornVO vo){
		System.out.println("===> Mybatis로 getAcornList() 기능 처리");
		return mybatis.selectList("MyBoardDAO.getAcornList", vo);
	}
	
	//건수 조회(캐시 사용 내역)
	public Integer getAcornCount(MyAcornVO vo) {
		System.out.println("===> Mybatis로 getAcornCount() 기능 처리");
		return mybatis.selectOne("MyBoardDAO.getAcornCount", vo);
	}
	
	//내 캐시 조회
	public Integer getAcorn(MyAcornVO vo) {
		System.out.println("===> Mybatis로 getAcorn() 기능 처리");
		return mybatis.selectOne("MyBoardDAO.getAcorn", vo);
	}
	
	//내 캐시 내역 삭제
	public void deleteAcorn(MyAcornVO vo) {
		System.out.println("===> Mybatis로 deleteAcorn() 기능 처리");
		mybatis.delete("MyBoardDAO.deleteAcorn", vo);
	}
	
	//건수 조회(회원별 환급목록)
	public Integer recashCount(ReCashVO vo) {
		System.out.println("===> Mybatis로 recashCount() 기능 처리");
		return mybatis.selectOne("MyBoardDAO.recashCount", vo);
	}
	
	//환급조회
	public List<ReCashVO> recashList(ReCashVO vo) {
		System.out.println("===> Mybatis로 recashList() 기능 처리");
		return mybatis.selectList("MyBoardDAO.recashList",vo);
	}
	
	//환급신청 번호 부여
	public int recashNo(ReCashVO vo) {
		System.out.println("===> Mybatis로 recashNo() 기능 처리");
		return mybatis.selectOne("MyBoardDAO.recashNo",vo);
	}
	
	//환급신청
	public void recashIns(ReCashVO vo) {
		System.out.println("===> Mybatis로 recashIns() 기능 처리");
		mybatis.insert("MyBoardDAO.recashIns",vo);
	}

}
