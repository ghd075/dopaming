<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div style="width:90%; align-self: center;">

	<h3 align=left><u>고객센터</u></h3>
	
	유형: ${ComplainVO.complain_type}<br>
	신고게시판 : ${ComplainVO.board_no}
	<table width="100%" height = "40px" border="1">
        <tr>        
            <td bgcolor="" width="80p"  align="center">제목</td>
            <td align="center">${ComplainVO.complain_title}</td>
            <td width="80p"  align="center">글쓴이</td>
            <td width="200p"  align="center">${ComplainVO.member_id}</td>
            <td width="80p"  align="center">날짜</td>
            <td width="200p"  align="center">${ComplainVO.complain_date}</td>     
        </tr>
     </table>
	
	내용 : 
	<table width="100%" height = "500px" border="1">
	<tr><td valign="top">
	${ComplainVO.complain_content}
	</td></tr>
	</table><br>
	
</div>	
</body>
</html>