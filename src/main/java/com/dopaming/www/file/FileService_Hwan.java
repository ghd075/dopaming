package com.dopaming.www.file;

import java.util.List;

public interface FileService_Hwan {

	//파일 업로드
	public void board_file_upload(FileBoardVO_Hwan bvo, List<FileUploadVO_Hwan> fvolist);
	//게시글 조회
	public FilePostVO_Hwan select_post_hwan(FilePostVO_Hwan fpvo);
	//게시글 수정
	public void board_update_hwan(FileBoardVO_Hwan fbvo);
	//게시글 수정 열람
	public FileBoardVO_Hwan board_update_select_hwan(FileBoardVO_Hwan fbvo);
	//게시글 파일 리스트
	public List<FilePostVO_Hwan> select_post_fileList(FilePostVO_Hwan fpvo);
	//게시글에 게시글 리스트
	public List<FilePostVO_Hwan> select_board_boardList(FilePostVO_Hwan fpvo);
	//페이징
	public int board_Paging(FilePostVO_Hwan fpvo);
	//다운로드 등록
	public void download_insert_hwan(FileDownloadVO_Hwan fdvo);
	//다운로드 등록 유무 확인 
	public int download_check_hwan(FileDownloadVO_Hwan fdvo);	
	//다운로드 등록 유무 확인2 	
	public FileDownloadVO_Hwan download_check_hwan2(FileDownloadVO_Hwan fdvo);
	//다운로드 게시글리스트 열람
	public List<FileDownloadVO_Hwan> select_downloadList(FileDownloadVO_Hwan fdvo);
	//다운로드 게시글 열람
	public FileDownloadVO_Hwan select_downloadOne(FileDownloadVO_Hwan fdvo);
	//댓글 등록
	public void comment_insert_hwan(FileCommentsVO_Hwan fcvo);
	//댓글 단건 조회
	public FileCommentsVO_Hwan comment_selectOne_hwan(FileCommentsVO_Hwan fcvo);
	//댓글 리스트 조회 
	public List<FileCommentsVO_Hwan> comment_selectList_hwan(FileCommentsVO_Hwan fcvo);
}
