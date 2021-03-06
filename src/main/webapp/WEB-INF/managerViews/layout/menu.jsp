<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="sidebar pe-4 pb-3">
    <nav class="navbar bg-light navbar-light">
        <a href="main.do" class="navbar-brand mx-4 mb-3">
            <h3 class="text-primary"><i class="fa fa-hashtag me-2"></i>EATGO</h3>
        </a>
        <div class="d-flex align-items-center ms-4 mb-4">
            <div class="position-relative">
                <img class="rounded-circle" src="asset/manager/img/user.jpg" alt=""
                     style="width: 40px; height: 40px;">
                <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
            </div>
            <div class="ms-3">
                <h6 class="mb-0">${loginManager.loginId}</h6>
                <span>Admin</span>
            </div>
        </div>
        <div class="navbar-nav w-100">
            <a href="main.do" class="nav-item nav-link active"><i
                    class="fa fa-tachometer-alt me-2"></i>메인페이지</a>
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i
                        class="fa fa-laptop me-2"></i>식당 관리</a>
                <div class="dropdown-menu bg-transparent border-0">
                    <a href="addRestaurantForm.do" class="dropdown-item">식당 추가</a>
                    <a href="updateRestaurantForm.do" class="dropdown-item">식당 수정</a>
                </div>
            </div>
            <a href="addMenuForm.do" class="nav-item nav-link"><i class="fa fa-th me-2"></i>메뉴
                추가</a>
            <a href="menuList.do" class="nav-item nav-link"><i class="fa fa-keyboard me-2"></i>메뉴 목록</a>
        </div>
    </nav>
</div>
