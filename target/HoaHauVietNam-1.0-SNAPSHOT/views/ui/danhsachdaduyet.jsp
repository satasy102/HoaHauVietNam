<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>Hoa Hậu Việt Nam 2020</title>
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css"/>
    <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet"
          type="text/css"/>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css"/>
    <!-- CSS (includes Bootstrap)-->
    <link href="../../ui-assets/css/styles.css" rel="stylesheet"/>
    <link rel="stylesheet" href="../../ui-assets/css/crud.css">
    <link rel="shortcut icon" href="">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="../../ui-assets/js/scripts.js"></script>
</head>

<body id="page-top">
<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="home" onclick="animateScroll(this)">
            <img src="../../ui-assets/assets/img/logos/logo.png" alt=""/></a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
                data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
                aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars ml-1"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav text-uppercase ml-auto">
                <li class="nav-item"><a class="nav-link js-scroll-trigger" onclick="animateScroll(this)"
                                        href="home">Trang Chủ</a></li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Danh sách
                    </a>
                    <div class="dropdown-menu js-scroll-trigger" aria-labelledby="navbarDropdown">
                        <a class="nav-link js-scroll-trigger" onclick="animateScroll(this)" href="home#dadangky">Đã đăng
                            ký</a>
                        <a class="nav-link js-scroll-trigger" onclick="animateScroll(this)" href="home#daduyet">Đã
                            duyết</a>
                    </div>
                </li>
                <li class="nav-item"><a class="nav-link js-scroll-trigger" onclick="animateScroll(this)"
                                        href="home#tintuc">Tin Tức</a></li>
                <li class="nav-item"><a class="nav-link js-scroll-trigger" onclick="animateScroll(this)"
                                        href="home#dangky">Đăng ký</a></li>
            </ul>
        </div>
    </div>
</nav>

<header class="masthead" id="home">
    <div class="container">
    </div>
</header>
<h2 class="block-title">
    Cuộc thi tìm kiếm <span>Hoa Hậu Việt Nam 2020</span>
</h2>
<c:forEach items="${message}" var="message">
    <c:if test='${message != null}'>
        <h3 class="text-danger" style="text-align: center;">Không tìm thấy</h3>
    </c:if>
</c:forEach>
<section class="content">
    <div class="container-xl">
        <div class="table-responsive">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-5">
                            <h2>Danh sách <b>Đã duyệt</b></h2>
                        </div>
                        <div class="col-sm-7">
                            <a href="home" class="btn btn-secondary">
                                <span>Trở lại</span></a>
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>Họ tên</th>
                        <th>Tỉnh thành</th>
                        <th>Dân tộc</th>
                        <th>Trạng thái hồ sơ</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${danhSachThiSinh}" var="danhSachThiSinh">
                        <tr>
                            <td>
                                <a href="thongtincanhan?id=${danhSachThiSinh.idThiSinh}">
                                    <img src="<c:url value="${danhSachThiSinh.anhCaNhan}"/>" class="avatar"
                                         alt="Avatar">
                                        ${danhSachThiSinh.hoTen}
                                </a>
                            </td>
                            <td>${danhSachThiSinh.tinhThanh.getTenTinh()}</td>
                            <td>${danhSachThiSinh.danToc.getTenDanToc()}</td>
                            <td>
                                <span class="status text-success ">&bull;</span>Đã duyệt
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <%--          <div class="clearfix">
                                <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
                                <ul class="pagination">
                                  <li class="page-item disabled"><a href="#">Previous</a></li>
                                  <li class="page-item"><a href="#" class="page-link">1</a></li>
                                  <li class="page-item"><a href="#" class="page-link">2</a></li>
                                  <li class="page-item active"><a href="#" class="page-link">3</a></li>
                                  <li class="page-item"><a href="#" class="page-link">4</a></li>
                                  <li class="page-item"><a href="#" class="page-link">5</a></li>
                                  <li class="page-item"><a href="#" class="page-link">Next</a></li>
                                </ul>
                            </div>--%>
            </div>
        </div>
    </div>
    <!-- /.container-fluid -->
</section>
<footer class="main-footer">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-lg-6 col-sm-6 col-xs-12">
                <h2 class="logo-footer">
                    <a href="home" title="">
                        <img class="img-responsive footer-logo" src="../../ui-assets/assets/img/logos/logo.png"
                             alt="logo">
                    </a>
                </h2>
                <ul class="list-unstyled list-inline list-logos pull-left no-margin">
                    <li>
                        <a href="https://tienphong.vn/" target="_blank">
                            <img src="../../ui-assets/assets/img/logos/logo_tienphong.png" alt="">
                        </a>
                    </li>
                    <li>
                        <a href="https://senvangvn.com/" target="_blank">
                            <img src="../../ui-assets/assets/img/logos/logo_senvang.png" alt="">
                        </a>
                    </li>
                </ul>
            </div>
            <div class="col-md-6 col-lg-6 col-sm-6 col-xs-12">
                <div class="copyright">
                    <p><strong>Đỗ Chánh Tín</strong></p>
                    <p>Điện thoại: <strong>0966-257 540</strong></p>
                    <p>Địa chỉ: <strong>CodeGym Huế, 28 Nguyễn Tri Phương, thành phố Huế</strong></p>
                </div>
            </div>
        </div>
    </div>
</footer>
</body>
</html>
