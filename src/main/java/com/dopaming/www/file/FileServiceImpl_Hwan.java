package com.dopaming.www.file;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
	
@Service("FileServiceHwan")
public class FileServiceImpl_Hwan implements FileService_Hwan{
	@Autowired
	private FileDAOMybatis_Hwan dao;

	//파일 업로드
	@Override
	public void board_file_upload(FileBoardVO_Hwan bvo, List<FileUploadVO_Hwan> fvolist) {
		// TODO Auto-generated method stub
		dao.board_insert_hwan(bvo);
		for(int i=0;i<fvolist.size();i++) {
			FileUploadVO_Hwan fvo = fvolist.get(i);
			fvo.setGroupNo(bvo.getBoardNo());
			fvo.setMemberId(bvo.getMemberId());
			dao.file_insert_hwan(fvo);
		}
	}

	//게시글 열람
	@Override
	public FilePostVO_Hwan select_post_hwan(FilePostVO_Hwan fpvo) {
		// TODO Auto-generated method stub		
		return dao.select_post_hwan(fpvo);
	}
	
	//게시글 수정
	public void board_update_hwan(FileBoardVO_Hwan fbvo) {
			dao.board_update_hwan(fbvo);
	}
	
	//게시글 수정 열람
	public FileBoardVO_Hwan board_update_select_hwan(FileBoardVO_Hwan fbvo) {
		return dao.board_update_select_hwan(fbvo);
	}

	//게시글 파일 리스트 열람
	@Override
	public List<FilePostVO_Hwan> select_post_fileList(FilePostVO_Hwan fpvo) {
		// TODO Auto-generated method stub		
		return dao.select_post_fileList(fpvo);
	}

	//게시글에 게시글 리스트 열람
	@Override
	public List<FilePostVO_Hwan> select_board_boardList(FilePostVO_Hwan fpvo) {
		// TODO Auto-generated method stub		
		return dao.select_board_boardList(fpvo);
	}

	//게시글 페이징
	@Override
	public int board_Paging(FilePostVO_Hwan fpvo) {
		// TODO Auto-generated method stub		
		return dao.board_Paging(fpvo);
	}		
	
	//다운로드 등록 
	public void download_insert_hwan(FileDownloadVO_Hwan fdvo) {
			dao.download_insert_hwan(fdvo);
	}
	
	//다운로드 등록 유무 확인
	@Override
	public int download_check_hwan(FileDownloadVO_Hwan fdvo) {
		return dao.download_check_hwan(fdvo);
	}
	
	//다운로드 등록 유무 확인2
	@Override
	public FileDownloadVO_Hwan download_check_hwan2(FileDownloadVO_Hwan fdvo) {
		return dao.download_check_hwan2(fdvo);			
	}
	
	//다운로드 게시글리스트 열람
	public List<FileDownloadVO_Hwan> select_downloadList(FileDownloadVO_Hwan fdvo){
		return dao.select_download(fdvo);
	}
	
	//다운로드 게시글 열람
	public FileDownloadVO_Hwan select_downloadOne(FileDownloadVO_Hwan fdvo) {
		return dao.select_downloadOne(fdvo);
	}	
	
	//댓글 삽입
	public void comment_insert_hwan(FileCommentsVO_Hwan fcvo) {
		dao.comment_insert_hwan(fcvo);
	}
	
	//댓글 단건 조회
	public FileCommentsVO_Hwan comment_selectOne_hwan(FileCommentsVO_Hwan fcvo) {
		return dao.comment_selectOne_hwan(fcvo);
	}
	//댓글 리스트 조회
	public List<FileCommentsVO_Hwan> comment_selectList_hwan(FileCommentsVO_Hwan fcvo){
		return dao.comment_selectList_hwan(fcvo);
	}
}
