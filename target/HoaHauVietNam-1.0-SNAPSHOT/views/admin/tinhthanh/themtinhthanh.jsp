<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Admin | Hoa hậu Việt Nam</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="../../../admin-assets/plugins/fontawesome-free/css/all.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Tempusdominus Bbootstrap 4 -->
  <link rel="stylesheet" href="../../../admin-assets/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="../../../admin-assets/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  <!-- JQVMap -->
  <link rel="stylesheet" href="../../../admin-assets/plugins/jqvmap/jqvmap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="../../../admin-assets/dist/css/adminlte.min.css">
  <!-- overlayScrollbars -->
  <link rel="stylesheet" href="../../../admin-assets/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="../../../admin-assets/plugins/daterangepicker/daterangepicker.css">
  <!-- summernote -->
  <link rel="stylesheet" href="../../../admin-assets/plugins/summernote/summernote-bs4.css">
  <link rel="stylesheet" href="../../../admin-assets/dist/css/crud.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <!-- JavaScript: -->
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>

<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">

  <!-- Navbar -->
  <nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="admin" class="nav-link">Trang chủ</a>
      </li>
    </ul>
    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">
      <a href="../../login?action=logout" class="btn btn-danger">Log out</a>
    </ul>
  </nav>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="admin" class="brand-link">
      <img src="../../../admin-assets/dist/img/logo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
      <span class="brand-text font-weight-light">Hoa Hậu Việt Nam</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="image">
          <img src="../../../admin-assets/dist/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
        </div>
        <div class="info">
          <a href="#" class="d-block">${username}</a>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
             with font-awesome or any other icon font library -->
          <li class="nav-item has-treeview menu-open">
            <a href="#" class="nav-link ">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                Quản lý hồ sơ thí sinh
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="admin" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Hồ sơ đã đăng ký</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="admin?action=daduyet" class="nav-link ">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Hồ sơ đã duyệt</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="admin?action=chuaduyet" class="nav-link ">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Hồ sơ chưa duyệt</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="admin?action=biloai" class="nav-link ">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Hồ sơ bị loại</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="admin?action=cungtinhthanh" class="nav-link ">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Thí sinh theo tỉnh thành</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="admin?action=cungdantoc" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Thí sinh cùng dân tộc</p>
                </a>
              <li class="nav-item">
                <a href="admin?action=cungtdvh" class="nav-link ">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Thí sinh cùng TDVH</p>
                </a>
            </ul>
          </li>
          <li class="nav-item has-treeview menu-open">
            <a href="showTinhThanh" class="nav-link active">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                Quản lý tỉnh thành
              </p>
            </a>
          </li>
          <li class="nav-item has-treeview menu-open">
            <a href="showdantoc" class="nav-link">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                Quản lý Dân tộc
              </p>
            </a>
          </li>
          <li class="nav-item has-treeview menu-open">
            <a href="showTDVH" class="nav-link">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                Quản lý Trình độ VH
              </p>
            </a>
          </li>
          <li class="nav-item has-treeview menu-open">
            <a href="showbaiviet" class="nav-link">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                Quản lý Bài viết
              </p>
            </a>
          </li>
        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0 text-dark">Quản lý Tỉnh thành phố</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="admin">Trang chủ</a></li>
              <li class="breadcrumb-item active">Thêm Tỉnh thành phố</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->
    <c:forEach items="${message}" var="message">
      <c:if test='${message != null}'>
        <c:choose>
          <c:when test='${message!="200"}'>
            <span class="alert alert-danger" role="alert">${message}</span>
          </c:when>
          <c:when test="${message == '200'}">
            <div class="alert alert-success" role="alert">Thêm thành công</div>
          </c:when>
        </c:choose>
      </c:if>
    </c:forEach>
    <!-- Main content -->
    <section class="content">
      <div class="container-xl">
        <div class="table-responsive">
          <div class="table-wrapper">
            <div class="table-title">
              <div class="row">
                <div class="col-sm-5">
                  <h2>Danh sách <b>Tỉnh thành phố</b></h2>
                </div>
                <div class="col-sm-7">
                  <a href="showTinhThanh" class="btn btn-secondary">
                    <span>Trở lại</span></a>
                </div>
              </div>
            </div>
            <table class="table table-striped table-hover">
              <thead>
              <tr>
                <th>Tên Tỉnh thành</th>
                <th>Thao tác</th>
              </tr>
              </thead>
              <tbody>
              <form action="themtinhthanh" method="post" id="formDemo">
                <tr>
                  <td><input type="text" name="tenTinh" class="form-control">
                  <td><button type="submit" class="btn btn-primary">Thêm</button></td>
                </tr>
              </form>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <strong>Đỗ Chánh Tín</strong>
    C0620i1 CodeGym Huế
  </footer>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->
<!-- Bootstrap 4 -->
<script src="../../../admin-assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- overlayScrollbars -->
<script src="../../../admin-assets/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
<!-- AdminLTE App -->
<script src="../../../admin-assets/dist/js/adminlte.js"></script>
<script src="../../../admin-assets/dist/js/validate.js"></script>
</body>

</html>
