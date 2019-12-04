<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <!-- Page Wrapper -->
  <div id="wrapper">
 
    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3 mt-3" >도파밍<p>프로젝트</p></div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
      <li class="nav-item" style="text-align: center;" >
        <a class="nav-link" href="./classForm">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Dopaming</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
      </div>
      
     
      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">
          <i class="fas fa-fw fa-wrench"></i>
          <span>회원관리</span>
        </a>
        <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <a class="collapse-item" href="${pageContext.request.contextPath}/admin/classForm">등급관리</a>
            <a class="collapse-item" href="${pageContext.request.contextPath}/admin/NormalListForm">일반회원관리</a>
            <a class="collapse-item" href="${pageContext.request.contextPath}/admin/blackListForm">블랙회원관리</a>
            <a class="collapse-item" href="${pageContext.request.contextPath}/admin/uploadlistForm">업로드한 리스트 뷰</a>
            <a class="collapse-item" href="${pageContext.request.contextPath}/admin/acornlist">환불처리</a> 
            <a class="collapse-item" href="${pageContext.request.contextPath}/admin/recashSelect">환급관리</a>
            <a class="collapse-item" href="${pageContext.request.contextPath}/admin/chartList">사용통계</a>             
          </div>
        </div>
      </li>

      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities2" aria-expanded="true" aria-controls="collapseUtilities">
          <i class="fas fa-fw fa-wrench"></i>
          <span>게시판관리</span>
        </a>
        <div id="collapseUtilities2" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">게시판관리</h6>
            <a class="collapse-item" href="${pageContext.request.contextPath }/admin/boardList">게시글관리</a>
            <a class="collapse-item" href="${pageContext.request.contextPath }/admin/notice_selectlist">공지사항</a>                    
          </div>
        </div>
      </li>

<!--    Divider
      <hr class="sidebar-divider">

      Heading
      <div class="sidebar-heading">
        Addons
      </div> -->

	  <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities5" aria-expanded="true" aria-controls="collapseUtilities">
          <i class="fas fa-fw fa-wrench"></i>
          <span>신고관리</span>
        </a>
        <div id="collapseUtilities5" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">신고관리</h6>
            <a class="collapse-item" href="${pageContext.request.contextPath }/admin/complain_selectlist?complain_type=qna">QnA</a>
            <a class="collapse-item" href="${pageContext.request.contextPath }/admin/complain_selectlist?complain_type=신고">신고 사항</a>            
         	<a class="collapse-item" href="${pageContext.request.contextPath }/admin/complain_selectlist?complain_type=건의">건의 사항</a>  
          </div>
        </div>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- End of Sidebar -->
    
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div>

        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>

 

          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">

            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
            <li class="nav-item dropdown no-arrow d-sm-none">
              <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-search fa-fw"></i>
              </a>
              <!-- Dropdown - Messages -->
              <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                <form class="form-inline mr-auto w-100 navbar-search">
                  <div class="input-group">
                    <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                      <button class="btn btn-primary" type="button">
                        <i class="fas fa-search fa-sm"></i>
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </li>

            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small"> 
				관리자(${sessionScope.member_id })님 어서오세요.
				</span>
				<!-- 세션제거 --> 
				<input type="button" class="btn btn-success" onclick="location='../logout'" value="로그아웃">	
              </a>
            </li>

          </ul>

        </nav>
        <!-- End of Topbar -->


      </div>
      <!-- End of Main Content -->