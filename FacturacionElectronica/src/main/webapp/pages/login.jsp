<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<title>Login</title>
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<link rel="icon" type="image/png" href="resources/assets/img/icon.png" />
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
	<link href="resources/assets/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css" rel="stylesheet" />
	<link href="resources/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
	<link href="resources/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
	<link href="resources/assets/css/animate.min.css" rel="stylesheet" />
	<link href="resources/assets/css/style.min.css" rel="stylesheet" />
	<link href="resources/assets/css/style-responsive.min.css" rel="stylesheet" />
</head>
<style>
    .alert {
        margin-bottom: 1px;
        height: 30px;
        line-height:30px;
        padding:0px 15px;
    }
</style>
<body>

<div id="page-loader" class="fade in"><span class="spinner"></span></div>

<div class="login-cover">
	<div class="login-cover-image"><img src="resources/assets/img/login-bg/bg-6.jpg" data-id="login-cover-image" alt="" /></div>
	<div class="login-cover-bg"></div>
</div>
<div id="page-container" class="fade">
	<div class="login login-v2" data-pageload-addclass="animated fadeIn">
		<div class="login-header" style="text-align: center">
			<div >
				<span style="font-size: 14px">SISTEMA INTEGRAL DE GESTION FINANCIERA QUIPUCAMAYOC</span><br/>
				<span style="font-size: 22px">MODULO FACTURACION</span>
			</div>
		</div>
		<div class="login-content" >
			<form role="form" action="<c:url value='${pageContext.request.contextPath}/j_spring_security_check' />" method="POST"	class="margin-bottom-0">
				<div class="input-group input-group-lg m-b-15">
					<input id="sign-in__email" name="username" type="text" class="form-control" placeholder="Usuario" required/>
					<span class="input-group-addon" id="basic-addon">@unmsm.edu.pe</span>
				</div>
				<div class="form-group m-b-15">
					<input id="sign-in__password"  name="password"   type="password" class="form-control input-lg" placeholder="Contraseña" required/>
				</div>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger" style = "display: ${display}">${error}</div>
                </c:if>
                <c:if test="${not empty msg}">
                    <div class="alert alert-info" style = "display: ${display}">${msg}</div>
                </c:if>
				<div class="login-buttons">
					<button type="submit" class="btn btn-success btn-block btn-lg">Ingresar</button>
				</div>
			</form>
		</div>
	</div>
	<ul class="login-bg-list">
	</ul>
</div>
<script src="resources/assets/plugins/pace/pace.min.js"></script>
<script src="resources/assets/plugins/jquery/jquery-1.9.1.min.js"></script>
<script src="resources/assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
<script src="resources/assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
<script src="resources/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="resources/assets/plugins/jquery-cookie/jquery.cookie.js"></script>
<script src="resources/assets/js/login-v2.demo.min.js"></script>
<script src="resources/assets/js/apps.min.js"></script>
<script>
	$(document).ready(function() {
		App.init();
	});
</script>
</body>
</html>