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
    <!-- CSS owlcarousel -->
    <link rel="stylesheet" href="../../ui-assets/owlcarousel/assets/owl.carousel.min.css">
    <link rel="stylesheet" href="../../ui-assets/owlcarousel/assets/owl.theme.default.min.css">
    <link rel="shortcut icon" href="">
    <!-- JS owlcarousel -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script type="text/javascript"
            src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
    <script src="../../ui-assets/owlcarousel/owl.carousel.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="../../ui-assets/js/scripts.js"></script>
    <script src="../../ui-assets/js/validate.js"></script>
</head>

<body id="page-top">
<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="home#home" onclick="animateScroll(this)">
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
                                        href="home#home">Trang Chủ</a></li>
                <li class="nav-item"><a class="nav-link js-scroll-trigger" onclick="animateScroll(this)"
                                        href="home#tintuc">Tin Tức</a></li>
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

<section class="container">
    <div class="row">
        <div class="col-xs-12 col-md-8 col-sm-8">
            <c:forEach items="${danhSachBaiViet}" begin="" end="5" var="baiViet">
                <div class="row">
                    <div class="col-md-5">
                        <a href="chitietbaiviet?id=${baiViet.maBaiViet}">
                            <img src="<c:url value="${baiViet.anhTieuDe}"/>" alt="anhTieuDe" style="object-fit: cover;
    width: 100%; margin-bottom: 0.5rem">
                        </a>
                    </div>
                    <div class="col-md-7">
                        <a href="chitietbaiviet?id=${baiViet.maBaiViet}"><h6>${baiViet.tieuDe}</h6></a>
                        <p>${baiViet.noiDungTomTat}</p>
                    </div>
                </div>
            </c:forEach>
            <div class="row">
                <ul class="pagination">
                    <c:forEach begin="1" end="${soTrang}" var="soThuTuTrang">
                        <li class="page-item">
                            <a href="tintuc?idTrang=${soThuTuTrang}" class="page-link">${soThuTuTrang}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="content-sidebar col-xs-12 col-md-4 col-sm-4">
            <h2 class="block-title">ĐƠN VỊ TỔ CHỨC</h2>
            <div class="text-align-center">
                <a href="https://tienphong.vn/" target="_blank">
                    <img src="../../ui-assets/assets/img/logos/logo_tienphong.png" alt="">
                </a>
            </div>
            <div class="text-align-center">
                <a href="https://senvangvn.com/" target="_blank">
                    <img src="../../ui-assets/assets/img/logos/logo_senvang.png" alt="">
                </a>
            </div>
        </div>
    </div>
</section>
</body>
</html>
