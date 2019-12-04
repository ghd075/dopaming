package com.dopaming.www.member;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dopaming.www.acorn.AcornVO;
import com.dopaming.www.admin.recash.ReCashVO;
import com.dopaming.www.file.FileDAOMybatis_Hwan;
import com.dopaming.www.file.FilePostVO_Hwan;



@Service("myBoardService")
public class MyBoardServiceImpl implements MyBoardService {

	@Autowired
	private MyBoardDAO dao;
	
	@Autowired
	private FileDAOMybatis_Hwan filedao;
	
	@Override
	public List<MyBoardVO> getDownList(MyBoardVO vo) {
		return dao.getDownList(vo);
	}

	@Override
	public Integer getDownCount(MyBoardVO vo) {
		return dao.getDownCount(vo);
	}

	@Override
	public List<FileUploadVO> getUploadList(FileUploadVO vo) {
		return dao.getUploadList(vo);
	}

	@Override
	public Integer getUploadCount(FileUploadVO vo) {
		return dao.getUploadCount(vo);
	}

	@Override
	public void deleteBoard(FileUploadVO vo) {
		for(String s :  vo.getSeqs()) {
			FilePostVO_Hwan fvo = new FilePostVO_Hwan();
			fvo.setBoard_no(Integer.parseInt(s));
			List<FilePostVO_Hwan> list = filedao.select_post_fileList(fvo);
			for(int i = 0; i<list.size(); i++) {
				if(list.get(i).getDown_count() == 0) {
					File file = new File(vo.getPath(),list.get(i).getFileName_List());
					file.delete();					
				}
			}
		}
		dao.deleteUpload(vo);
		dao.deleteComments(vo);
		dao.deleteBoard(vo);
	}

	@Override
	public List<AcornVO> getAcornList(MyAcornVO vo) {
		return dao.getAcornList(vo);
	}

	@Override
	public Integer getAcornCount(MyAcornVO vo) {
		return dao.getAcornCount(vo);
	}

	@Override
	public Integer getAcorn(MyAcornVO vo) {
		return dao.getAcorn(vo);
	}

	@Override
	public void deleteAcorn(MyAcornVO vo) {
		dao.deleteAcorn(vo);
	}
	
	@Override
	public Integer recashCount(ReCashVO vo) {
		return dao.recashCount(vo);
	}
	
	@Override
	public List<ReCashVO> recashList(ReCashVO vo) {
		return dao.recashList(vo);
	}
	
	@Override
	public int recashNo(ReCashVO vo) {
		return dao.recashNo(vo);
	}
	
	@Override
	public void recashIns(ReCashVO vo, HttpServletResponse response) throws IOException {
				
		PrintWriter out = response.getWriter();
		
		dao.recashIns(vo);
	
		out.print("<script>");
		out.print("opener.location.href='./myReCash';");
		out.print("window.close();");
		out.print("</script>");
		out.close();	
	}
}
