package com.dopaming.www.file;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dopaming.www.common.ImgExtract;

@Service("fileService")
public class FileServiceImpl implements FileService {

	@Autowired
	FileDAOmybatis dao;

	@Override
	public void insertFile(FileVO vo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateFile(FileVO vo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteFile(FileVO vo) {
		// TODO Auto-generated method stub

	}

	@Override
	public FileVO getFile(FileVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FileVO> getFileList(FileVO vo) {
		List<FileVO> list1 = dao.getFileList(vo);
		for (int i = 0; i < list1.size(); i++) {
			String text1 = "/dopaming/resources/images/logo2.jpg";

			String text = list1.get(i).getBoardImg();
			if (text != null) {
				String result = ImgExtract.getfirstimage(text);
				if (!result.isEmpty()) {
					list1.get(i).setBoardImg(result);
					System.out.println(result);
				}else
				{
					list1.get(i).setBoardImg(text1);
				}
			}else
			{
				list1.get(i).setBoardImg(text1);
			}
		}
		return list1;
	}

	@Override
	public int fileList_cnt(FileVO vo) {
		return dao.fileList_cnt(vo);
	}
	/*
	 * @Override public List<FileVO> getFileList_k() {
	 * System.out.println("getFileList_k"); return dao.getFileList_k(); }
	 * 
	 * @Override public List<FileVO> getFileList_f() {
	 * System.out.println("getFileList_f"); return dao.getFileList_f(); }
	 */

	@Override
	public int getFileCount(FileVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
