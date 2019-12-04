package com.dopaming.www.file;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileDAOMybatis_Hwan {
	@Autowired
	SqlSessionTemplate mybatis;
	//게시글 등록
	public void board_insert_hwan(FileBoardVO_Hwan bvo) {		
		mybatis.insert("FileDAOHwan.board_insert_hwan", bvo);
	}
	//파일 등록
	public void file_insert_hwan(FileUploadVO_Hwan fvo) {		
		mybatis.insert("FileDAOHwan.file_insert_hwan" , fvo);
	}	
	//게시글 조회
	public FilePostVO_Hwan select_post_hwan(FilePostVO_Hwan fpvo) {		
		return mybatis.selectOne("FileDAOHwan.filePost_hwan", fpvo);
	}
	//게시글 수정
	public void board_update_hwan(FileBoardVO_Hwan fbvo) {
		mybatis.update("FileDAOHwan.board_update_hwan",fbvo);
	}
	//게시글 수정 열람
	public FileBoardVO_Hwan board_update_select_hwan(FileBoardVO_Hwan fbvo) {
		return mybatis.selectOne("FileDAOHwan.board_update_select_hwan",fbvo);
	}
	
	//게시글 파일 조회
	public List<FilePostVO_Hwan> select_post_fileList(FilePostVO_Hwan fpvo){		
		return mybatis.selectList("FileDAOHwan.Board_FileList", fpvo);		
	}
	//게시글에 게시글 조회
	public List<FilePostVO_Hwan> select_board_boardList(FilePostVO_Hwan fpvo) {		
		return mybatis.selectList("FileDAOHwan.Board_BoardList" , fpvo);
	}	
	//게시글 페이징
	public int board_Paging(FilePostVO_Hwan fpvo) {		
		return mybatis.selectOne("FileDAOHwan.Board_Paging",fpvo);
	}
	//다운로드 등록
	public void download_insert_hwan(FileDownloadVO_Hwan fdvo){
		mybatis.insert("FileDAOHwan.DownloadInsert",fdvo);
	}
	
	//다운로드 등록 유무 확인
	public int download_check_hwan(FileDownloadVO_Hwan fdvo) {
		return mybatis.selectOne("FileDAOHwan.DownloadCheck",fdvo);
	}
	
	//다운로드 등록 유무 확인2
	public FileDownloadVO_Hwan download_check_hwan2(FileDownloadVO_Hwan fdvo) {
		return mybatis.selectOne("FileDAOHwan.DownloadCheck2",fdvo);
	}
	
	//다운로드 게시글리스트 열람
	public List<FileDownloadVO_Hwan> select_download(FileDownloadVO_Hwan fdvo){
		return mybatis.selectList("FileDAOHwan.Download_List", fdvo);
	}
	//다운로드 게시글 열람
	public FileDownloadVO_Hwan select_downloadOne(FileDownloadVO_Hwan fdvo) {
		return mybatis.selectOne("FileDAOHwan.DownloadPost", fdvo);
	}	
	//댓글 등록
	public void comment_insert_hwan(FileCommentsVO_Hwan fcvo) {
		mybatis.insert("FileDAOHwan.CommentInsert",fcvo);
	}
	//댓글 단건 조회
	public FileCommentsVO_Hwan comment_selectOne_hwan(FileCommentsVO_Hwan fcvo) {
		return mybatis.selectOne("FileDAOHwan.CommentSelectOne",fcvo);
	}
	//댓글 리스트 조회 
	public List<FileCommentsVO_Hwan> comment_selectList_hwan(FileCommentsVO_Hwan fcvo){
		return mybatis.selectList("FileDAOHwan.CommentSelectList", fcvo);
	}
}
