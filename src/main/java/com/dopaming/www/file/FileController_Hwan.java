package com.dopaming.www.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dopaming.www.common.FileRenamePolicy;
import com.dopaming.www.common.Paging;
import com.dopaming.www.login.MemberVO;

@Controller
public class FileController_Hwan {
	private static final Logger logger = LoggerFactory.getLogger(FileController_Hwan.class);

	@Autowired
	FileService_Hwan service;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/fileUploadForm_Hwan", method = RequestMethod.GET)
	public String upload_hwan(Locale locale, Model model) {

		return "hwan/upload_hwan";
	}

	// 파일 업로드 처리
	@RequestMapping(value = "/request_upload", method = RequestMethod.POST)
	public String requestUpload_hwan(MultipartHttpServletRequest request, FileBoardVO_Hwan bvo)
			throws IllegalStateException, IOException {
		List<MultipartFile> files = request.getFiles("fileName");
		String filePath = request.getSession().getServletContext().getRealPath("./resources/upload");
		File file;
		List<FileUploadVO_Hwan> fvolist = new ArrayList<FileUploadVO_Hwan>();
		FileUploadVO_Hwan fvo;
		for (int i = 0; i < files.size(); i++) {
			// 파일이 null인지 체크
			if (!files.get(i).isEmpty() && files.get(i).getSize() > 0) {
				System.out.println(files.get(i).getOriginalFilename() + "업로드");
				// 파일 업로드 소스 여기에 삽입
				file = new FileRenamePolicy().rename(new File(filePath, files.get(i).getOriginalFilename()));
				files.get(i).transferTo(file);
				fvo = new FileUploadVO_Hwan();
				fvo.setFileName(file.getName());
				fvo.setFileStorage(Math.ceil(((double) file.length() / 1024 / 1024) * 100) / 100);
				fvolist.add(fvo);
			}
		}
		service.board_file_upload(bvo, fvolist);

		return "redirect:/";
	}

	// 파일 다운로드
	@RequestMapping(value = "/download_hwan", method = RequestMethod.GET)
	public String download_hwan(FileDownloadVO_Hwan fdvo, Model model) {
		model.addAttribute("downPost_List", service.select_downloadList(fdvo));
		model.addAttribute("downPost", service.select_downloadOne(fdvo));

		return "hwan/download_hwan";
	}

	// 파일 다운로드
	@RequestMapping(value = "/download_cancel", method = RequestMethod.GET)
	public String download_cancel_hwan() {
		return "redirect:/";
	}

	// 파일 다운로드 처리
	@RequestMapping(value = "/request_download")	
	public String requestDownload_hwan(FileDownloadVO_Hwan fbvo, FileDownloadVO_Hwan fdvo, HttpSession session,
			Model model, HttpServletRequest request, HttpServletResponse response, MemberVO mvo,
			@RequestParam("seller") String id) throws Exception {
		model.addAttribute("downPost", service.select_downloadOne(fdvo));
		String filePath = request.getSession().getServletContext().getRealPath("./resources/upload");
		String fileCom = System.currentTimeMillis() + "files.zip";
		FileOutputStream zipFileOutputStream = new FileOutputStream(filePath + "/" + fileCom);
		ZipOutputStream zipOutputStream = new ZipOutputStream(zipFileOutputStream);
		List<FileDownloadVO_Hwan> result = service.select_downloadList(fbvo);
		double storage = 0.0;
		FileDownloadVO_Hwan f;
		for (int i = 0; i < result.size(); i++) {
			f = result.get(i);
			ZipEntry zipEntry = new ZipEntry(f.getFile_name());
			zipOutputStream.putNextEntry(zipEntry);
			FileInputStream fis = new FileInputStream(filePath + "/" + f.getFile_name());
			System.out.println(fis.toString());
			int data = 0;
			while ((data = fis.read()) != -1) {
				zipOutputStream.write(data);
			}
			System.out.println("각 파일 용량" + f.getFile_storage());
			storage += f.getFile_storage();
			fis.close();
		}
		zipOutputStream.close();
		zipFileOutputStream.close();
		System.out.println("해당 게시글 총 용량 : " + storage);
		model.addAttribute("fileStorage", storage);
		// File uFile = new File(filePath,result.get(0).getFile_name());
		// long fSize=uFile.length();
		if (storage > 0) {
			// String mimetype = "application/x-msdownload";

			// 아래 두 문구는 같은 의미
			// response.setContentType(mimetype);
			setDisposition(fileCom, request, response);

			BufferedInputStream in = null;
			BufferedOutputStream out = null;

			try {
				in = new BufferedInputStream(new FileInputStream(filePath + "/" + fileCom));
				out = new BufferedOutputStream(response.getOutputStream());

				FileCopyUtils.copy(in, out);
				out.flush();
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				in.close();
				out.close();
			}

		} else {
			response.setContentType("application/x-msdownload");

			PrintWriter printwriter = response.getWriter();

			printwriter.println("<html>");
			// printwriter.println("<br><br><br><h2>Could not get file name:<br>" +
			// result.getFilename() + "</h2>");
			printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
			printwriter.println("<br><br><br>&copy; webAccess");
			printwriter.println("</html>");

			printwriter.flush();
			printwriter.close();
		}
		FileDownloadVO_Hwan fdchk2 = service.download_check_hwan2(fdvo);
		mvo.setMember_id((String) session.getAttribute("Id"));
		System.out.println("세션 아이디값 : " + mvo.getMember_id());
		System.out.println("판매자 아이디값 : " + request.getParameter("seller"));
		if (fdchk2 == null) {
			if ((service.download_check_hwan(fdvo) == 0) && (!mvo.getMember_id().equals(id))) {
				for (int i = 0; i < result.size(); i++) {
					f = result.get(i);
					fdvo.setMember_id(mvo.getMember_id());
					fdvo.setDownload_acorn(f.getBoard_acorn());
					fdvo.setFile_no(f.getFile_no());
					fdvo.setGroup_no(f.getGroup_no());
					service.download_insert_hwan(fdvo);
					System.out.println("다운로드 DB 삽입 성공");
				}
			} else {
				System.out.println("아이디값이 같으므로 DB 삽입 실패 => 다운로드만 될 것임");
			}
		} else {
			System.out.println("다운로드 DB 삽입 실패 => 다운로드만 될 것임");
		}
		System.out.println("현재 아이디 체크 : " + session.getAttribute("Id"));
		return "hwan/download_hwan";
	}

	// 댓글 리스트 조회
	@RequestMapping(value = "/comment_list_hwan")
	@ResponseBody
	public List<FileCommentsVO_Hwan> comment_selectList_hwan(HttpServletRequest response, FileCommentsVO_Hwan fcvo)
			throws UnsupportedEncodingException {
		response.setCharacterEncoding("utf-8");// JSON 한글 깨짐 해결
		return service.comment_selectList_hwan(fcvo);
	}

	// 댓글 등록
	@RequestMapping(value = "/comment_hwan")
	@ResponseBody
	public FileCommentsVO_Hwan comment_hwan(HttpServletRequest request, HttpServletResponse response,
			FileCommentsVO_Hwan fcvo, MemberVO mvo, HttpSession session, Model model) {
		response.setCharacterEncoding("utf-8");// JSON 한글 깨짐 해결
		mvo = (MemberVO) session.getAttribute("memberSession");
		System.out.println(mvo.getMember_id());
		fcvo.setMember_id(mvo.getMember_id());
		System.out.println("ajax 응답 받음");
		System.out.println(fcvo.getComment_content());
		// System.out.println(m+" 마지막출력");
		model.addAttribute("comments", fcvo);
		service.comment_insert_hwan(fcvo);
		return service.comment_selectOne_hwan(fcvo);
	}

	// 게시글
	@RequestMapping(value = "/filepost", method = RequestMethod.GET)
	public String filepost_hwan(FilePostVO_Hwan fpvo, Model model, Paging paging) {

		FilePostVO_Hwan filePostVO_Hwan = service.select_post_hwan(fpvo);
		model.addAttribute("filePost", filePostVO_Hwan);
		model.addAttribute("Board_FileList", service.select_post_fileList(fpvo));

		// 페이징 처리
		paging.setPageUnit(5); // 개당 출력건수
		// 시작페이지 설정
		if (paging.getPage() == 0) {
			paging.setPage(1);
		}

		// db에서 받은 정보로 페이지마다 시작/마지막 레코드 번호
		fpvo.setFirst(paging.getFirst());
		fpvo.setLast(paging.getLast());
		fpvo.setMember_id(filePostVO_Hwan.getMember_id());

		// 돌려주는 값(전체레코드)이 페이징vo에 셋팅이된다.
		paging.setTotalRecord(service.board_Paging(fpvo));
		// 페이징 VO의 데이터를 paging으로 담아둔다.
		model.addAttribute("paging", paging);
		// 돌려 받은 값들을 list에 받아둔다.

		model.addAttribute("Board_List_Hwan", service.select_board_boardList(fpvo));
		System.out.println(fpvo.getBoard_no() + " 게시판 번호");

		return "hwan/file_post_hwan";
	}

	// 게시글 수정 조회
	@RequestMapping(value = "/filepostUpdate", method = RequestMethod.GET)
	public String filepost_update_hwan(
			FileBoardVO_Hwan fbvo, Model model,FilePostVO_Hwan fpvo) {
		FilePostVO_Hwan filePostVO_Hwan = service.select_post_hwan(fpvo);
		model.addAttribute("filePost", filePostVO_Hwan);
		model.addAttribute("BoardUpSel",service.board_update_select_hwan(fbvo));
		return "hwan/file_post_update_hwan";
	}
	// 게시글 수정 등록
	@RequestMapping(value = "/request_update", method = RequestMethod.GET)
	public String filepost_request_update_hwan(
			FileBoardVO_Hwan fbvo, Model model) {
		service.board_update_hwan(fbvo);
		System.out.println("수정함");
		return "redirect:/";
	}

	/**
	 * Disposition 지정하기.
	 *
	 * @param filename
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String browser = getBrowser(request);

		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}

	/**
	 * 브라우저 구분 얻기.
	 *
	 * @param request
	 * @return
	 */
	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
			return "Trident";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
	}
}
