package com.dopaming.www.file;

import java.util.List;

public interface FileService {
	
	//글 등록
	void insertFile(FileVO vo);
	
	// 글 수정
	void updateFile(FileVO vo);
	
	// 글 삭제
	void deleteFile(FileVO vo);

	// 파일 상세 조회
	FileVO getFile(FileVO vo);

		// 파일 목록 조회
	List<FileVO> getFileList(FileVO vo);
	//List<FileVO> getFileList_k();
	//List<FileVO> getFileList_f();
	
	//파일 페이징 건수 출력
	int fileList_cnt(FileVO vo);
		
	public int getFileCount(FileVO vo);


}
