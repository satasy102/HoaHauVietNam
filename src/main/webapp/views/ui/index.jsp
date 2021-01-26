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
            src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
    <script src="../../ui-assets/owlcarousel/owl.carousel.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="../../ui-assets/js/scripts.js"></script>
    <script src="../../ui-assets/js/validate.js"></script>
</head>

<body id="page-top">
<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="#home" onclick="animateScroll(this)">
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
                                        href="#home">Trang Chủ</a></li>
                <li class="nav-item"><a class="nav-link js-scroll-trigger" onclick="animateScroll(this)"
                                        href="#tintuc">Tin Tức</a></li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Danh sách
                    </a>
                    <div class="dropdown-menu js-scroll-trigger" aria-labelledby="navbarDropdown">
                        <a class="nav-link js-scroll-trigger" onclick="animateScroll(this)" href="#dadangky">Đã đăng
                            ký</a>
                        <a class="nav-link js-scroll-trigger" onclick="animateScroll(this)" href="#daduyet">Đã
                            duyết</a>
                    </div>
                </li>
                <li class="nav-item"><a class="nav-link js-scroll-trigger" onclick="animateScroll(this)"
                                        href="#dangky">Đăng ký</a></li>
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
<section class="page-section bg-light" id="tintuc">
    <div class="container">
        <div class="text-center">
            <h2 class="section-heading text-uppercase">Tin tức</h2>
            <h3 class="section-subheading text-muted"><a href="tintuc"> Tin tức </a>về cuộc thi Hoa Hậu Việt Nam
            </h3>
        </div>
        <div class="owl-carousel owl-theme">
            <c:forEach items="${danhSachBaiViet}" var="baiViet">
                <div>
                    <div class="card" style="width: 100%; height: 26rem">
                        <a href="chitietbaiviet?id=${baiViet.maBaiViet}">
                            <img class="card-img-top" src="<c:url value="${baiViet.anhTieuDe}"/>" alt="Card image cap">
                        </a>
                        <div class="card-body" style="overflow: hidden; text-overflow: ellipsis; ">
                            <a href="chitietbaiviet?id=${baiViet.maBaiViet}">
                                <h6 class="card-title">${baiViet.tieuDe}</h6>
                            </a>
                            <p class="card-text">${baiViet.noiDungTomTat}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>
<section class=" page-section bg-light" id="dadangky">
    <div class="container">
        <div class="text-center">
            <h2 class="section-heading text-uppercase">Danh sách thí sinh đã tham gia đăng ký</h2>
            <h3 class="section-subheading text-danger">Xem danh sách chi tiết <a href="danhsachthamgia">tại đây</a></h3>
        </div>
        <c:choose>
            <c:when test="${danhSachThiSinh.size()==0}">
                <h5 class="text-danger text-align-center">Chưa có thí sinh đăng ký!</h5>
            </c:when>
            <c:when test="${danhSachThiSinh.size()!=0}">
                <div class="owl-carousel owl-theme">
                    <c:forEach items="${danhSachThiSinh}" var="thiSinh">
                        <div class="item">
                            <div class="portfolio-item">
                                <a class="portfolio-link" href="thongtincanhan?id=${thiSinh.idThiSinh}">
                                    <div class="portfolio-hover">
                                        <div class="portfolio-hover-content"></div>
                                    </div>
                                    <img class="img-fluid" src="<c:url value='${thiSinh.anhCaNhan}'/>"
                                         alt="${thiSinh.hoTen}"/>
                                </a>
                                <div class="portfolio-caption">
                                    <div class="portfolio-caption-heading">${thiSinh.hoTen}</div>
                                    <div class="portfolio-caption-subheading text-danger">${thiSinh.tinhThanh.tenTinh}</div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
        </c:choose>
    </div>
</section>

<section class="page-section bg-light" id="daduyet">
    <div class="container">
        <div class="text-center">
            <h2 class="section-heading text-uppercase">Danh sách thí sinh đã được duyệt</h2>
            <h3 class="section-subheading text-danger">Xem danh sách chi tiết <a href="danhsachdaduyet">tại đây</a></h3>
        </div>
        <c:choose>
            <c:when test="${danhSachDaDuyet.size()==0}">
                <h5 class="text-danger text-align-center">Chưa có thí sinh được duyệt!</h5>
            </c:when>
            <c:when test="${danhSachDaDuyet.size()!=0}">
                <div class="owl-carousel owl-theme">
                    <c:forEach items="${danhSachDaDuyet}" var="thiSinh">
                        <div class="item">
                            <div class="portfolio-item">
                                <a class="portfolio-link" href="thongtincanhan?id=${thiSinh.idThiSinh}">
                                    <div class="portfolio-hover">
                                        <div class="portfolio-hover-content"></div>
                                    </div>
                                    <img class="img-fluid" src="<c:url value='${thiSinh.anhCaNhan}'/>"
                                         alt="${thiSinh.hoTen}"/>
                                </a>
                                <div class="portfolio-caption">
                                    <div class="portfolio-caption-heading">${thiSinh.hoTen}</div>
                                    <div class="portfolio-caption-subheading text-danger">${thiSinh.tinhThanh.tenTinh}</div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
        </c:choose>
    </div>
</section>

<section class="page-section" id="dangky">
    <div class="container">
        <div class="text-center">
            <h2 class="section-heading text-uppercase">Đăng ký online</h2>
            <h3 class="section-subheading text-muted">Vui lòng nhập thông tin đầy đủ và chính xác</h3>
        </div>
        <c:forEach items="${message}" var="message">
            <c:if test='${message != null}'>
                <c:choose>
                    <c:when test='${message!="200"}'>
                        <span class="alert alert-danger" role="alert">${message}</span>
                    </c:when>
                    <c:when test="${message == '200'}">
                        <div class="alert alert-success" role="alert">Đăng ký thành công</div>
                    </c:when>
                </c:choose>
            </c:if>
        </c:forEach>
        <section class="content">
            <div class="container-xl">
                <div class="table-responsive">
                    <div class="table-wrapper">
                        <div class="table-title">
                            <div class="row">
                                <div class="col-sm-5">
                                    <h2>Form <b>Đăng ký dự thi</b></h2>
                                </div>
                            </div>
                        </div>
                    </div>
                    <form method="post" action="dangkyduthi" id="formDemo">
                        <fieldset>
                            <legend>Thông tin đăng ký</legend>
                            <table class="table table-striped table-hover">
                                <tr>
                                    <td>Họ tên:</td>
                                    <td><input type="text" name="ten" class="form-control" value="${thiSinh.hoTen}"
                                               required></td>
                                </tr>
                                <tr>
                                    <td>Ngày sinh:</td>
                                    <td><input type="date" name="ngaySinh" class="form-control"
                                               value="${thiSinh.ngaySinh}" required>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Địa chỉ cụ thể:</td>
                                    <td><input type="text" name="diaChiCuTru" class="form-control"
                                               value="${thiSinh.diaChiCuTru}"
                                               required></td>
                                </tr>
                                <tr>
                                    <td>Số điện thoại :</td>
                                    <td><input type="text" name="sdt" class="form-control" value="${thiSinh.sdt}"
                                               required></td>
                                </tr>
                                <tr>
                                    <td>Email:</td>
                                    <td><input type="email" name="email" class="form-control" value="${thiSinh.email}"
                                               required></td>
                                </tr>
                                <tr>
                                    <td>Chứng minh thư:</td>
                                    <td><input type="text" name="cmt" class="form-control"
                                               value="${thiSinh.chungMinhNhanDan}" required>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Nghề nghiệp:</td>
                                    <td><input type="text" name="ngheNghiep" class="form-control"
                                               value="${thiSinh.ngheNghiep}" required>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Trình độ văn hóa:</td>
                                    <td>
                                        <select name="trinhDoVH" class="form-control">
                                            <c:choose>
                                                <c:when test="${thiSinh.idTrinhDoVH==null}">
                                                    <c:forEach items="${danhSachTDVH}" var="danhSachTDVH">
                                                        <option value="${danhSachTDVH.idTrinhDoVanHoa}">${danhSachTDVH.tenTrinhDoVanHoa}</option>
                                                    </c:forEach>
                                                </c:when>

                                                <c:when test="${thiSinh.idTrinhDoVH!=null}">
                                                    <option value="${thiSinh.idTrinhDoVH}">${thiSinh.trinhDoVanHoa.tenTrinhDoVanHoa}</option>
                                                    <c:forEach items="${danhSachTDVH}" var="danhSachTDVH">
                                                        <option value="${danhSachTDVH.idTrinhDoVanHoa}">${danhSachTDVH.tenTrinhDoVanHoa}</option>
                                                    </c:forEach>
                                                </c:when>
                                            </c:choose>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Dân tộc:</td>
                                    <td>
                                        <select name="danToc" class="form-control">
                                            <c:choose>
                                                <c:when test="${thiSinh.idDanToc==null}">
                                                    <c:forEach items="${danhSachDanToc}" var="danhSachDanToc">
                                                        <option value="${danhSachDanToc.idDanToc}">${danhSachDanToc.tenDanToc}</option>
                                                    </c:forEach>
                                                </c:when>
                                                <c:when test="${thiSinh.idDanToc!=null}">
                                                    <option value="${thiSinh.idDanToc}">${thiSinh.danToc.tenDanToc}</option>
                                                    <c:forEach items="${danhSachDanToc}" var="danhSachDanToc">
                                                        <option value="${danhSachDanToc.idDanToc}">${danhSachDanToc.tenDanToc}</option>
                                                    </c:forEach>
                                                </c:when>
                                            </c:choose>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Đơn vị công tác:</td>
                                    <td><input type="text" name="donViCongTac" class="form-control"
                                               value="${thiSinh.donViCongTac}"
                                               required></td>
                                </tr>
                                <tr>
                                    <td>Chiều cao:</td>
                                    <td>
                                        <input type="number" name="chieuCao" class="form-control"
                                               value="${thiSinh.chieuCao}" required>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Cân nặng:</td>
                                    <td><input type="number" name="canNang" class="form-control"
                                               value="${thiSinh.canNang}" required></td>
                                </tr>
                                <tr>
                                    <td>Năng khiếu khác:</td>
                                    <td><input type="text" name="nangKieuKhac" class="form-control"
                                               value="${thiSinh.nangKhieuKhac}"
                                               required></td>
                                </tr>
                                <tr>
                                    <td>Ảnh cá nhân:</td>
                                    <td><input type="text" name="anhCaNhan" class="form-control"
                                               value="${thiSinh.anhCaNhan}" required>
                                    </td>

                                </tr>
                                <tr>
                                    <td>Đại diện tỉnh thành:</td>
                                    <td>
                                        <select name="daiDienTinhThanh" class="form-control">
                                            <c:choose>
                                                <c:when test="${thiSinh.idTinh==null}">
                                                    <c:forEach items="${danhSachTinhThanh}" var="danhSachTinhThanh">
                                                        <option value="${danhSachTinhThanh.idTinh}">${danhSachTinhThanh.tenTinh}</option>
                                                    </c:forEach>
                                                </c:when>

                                                <c:when test="${thiSinh.idTinh!=null}">
                                                    <option value="${thiSinh.idTinh}">${thiSinh.tinhThanh.tenTinh}</option>
                                                    <c:forEach items="${danhSachTinhThanh}" var="danhSachTinhThanh">
                                                        <option value="${danhSachTinhThanh.idTinh}">${danhSachTinhThanh.tenTinh}</option>
                                                    </c:forEach>
                                                </c:when>
                                            </c:choose>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td><input type="submit" value="Xong" class="btn btn-primary"></td>
                                </tr>
                            </table>
                        </fieldset>
                    </form>
                </div>
            </div>
            <!-- /.container-fluid -->
        </section>
        <div class="d-flex justify-content-center h-100">

        </div>
    </div>
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
                        <a href="http://tienphong.vn/" target="_blank">
                            <img src="../../ui-assets/assets/img/logos/logo_tienphong.png" alt="">
                        </a>
                    </li>
                    <li>
                        <a href="http://senvangvn.com/" target="_blank">
                            <img src="../../ui-assets/assets/img/logos/logo_senvang.png" alt="">
                        </a>
                    </li>
                </ul>
            </div>
            <div class="col-md-6 col-lg-6 col-sm-6 col-xs-12">
                <div class="copyright">
                    <p>Webside thực hiện bởi <strong>Đỗ Chánh Tín</strong></p>
                    <p>Điện thoại: <strong>0966-257 540</strong></p>
                    <p>Địa chỉ: <strong>CodeGym Huế, 28 Nguyễn Tri Phương, thành phố Huế</strong></p>
                </div>
            </div>
        </div>
    </div>
</footer>
<script src="../../ui-assets/js/owlCarousel.js"></script>

</body>

</html>