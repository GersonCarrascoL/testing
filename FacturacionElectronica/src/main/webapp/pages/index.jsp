<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="icon" type="image/png" href="resources/assets/img/icon.png" />
    <title>Inicio</title>
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
    <link rel="apple-touch-icon" href="img/apple-touch-icon.png">
    <link rel="apple-touch-icon" sizes="72x72" href="img/apple-touch-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="114x114" href="img/apple-touch-icon-114x114.png">
    <link rel="stylesheet" type="text/css"  href="resources/assets/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="resources/assets/css/font-awesome.css">
    <link href="resources/assets/css/owl.carousel.css" rel="stylesheet" media="screen">
    <link href="resources/assets/css/owl.theme.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" type="text/css"  href="resources/assets/css/style.css">
    <link rel="stylesheet" type="text/css" href="resources/assets/css/responsive.css">
    <link href='http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900,100italic,300italic,400italic,700italic,900italic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,700,300,600,800,400' rel='stylesheet' type='text/css'>
</head>
<body>
    <nav id="tf-menu" class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header"   >
           <img src="resources/assets/img/logo.png"   style="height: 150px; margin-left: -80px"  class="navbar-brand">
        </div>
       <div class="navbar-header pull-right">
           <img src="resources/assets/img/logoSM.png" style="height:150px;margin-right:-190px" class="navbar-brand">
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" style="margin-top: 30px;margin-right:80px;">
          <ul class="nav navbar-nav"  style="margin-left: 100px">
            <li><a href="#tf-home" class="page-scroll" style="font-size: 20px">INICIO</a></li>
            <li><a href="#tf-about" class="page-scroll" style="font-size: 20px">ACERCA DEL MÓDULO</a></li>
            <li><a href="#tf-works" class="page-scroll" style="font-size: 20px">NUESTROS MÓDULOS</a></li>
          </ul>
         </div>
      </div>
    </nav>
    <div id="tf-home" class="text-center">
        <div class="overlay">
            <div class="content">
            	<!--  <h1><span style="font-size: 20px">SISTEMA INTEGRAL DE GESTIÓN FINANCIERA</span>   <strong><span style="font-size: 20px">QUIPUCAMAYOC</span></strong></h1>-->
                <h1><span style="font-size: 40px">BIENVENIDO A </span></h1>
                <h1><strong><span class="color" style="font-size: 40px">FACTURACIÓN ELECTRÓNICA</span></strong></h1>
				<form action="login">
					<input type="submit" value="INGRESAR" class="btn btn-principal"> 
				</form>
				<a href="#tf-about" class="fa fa-angle-down page-scroll"></a>
            </div>
        </div>
    </div>
    <div id="tf-about">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <img src="resources/assets/img/02.jpg" class="img-responsive">
                </div>
                <div class="col-md-6">
                    <div class="about-text">
                        <div class="section-title">
                            <h4>Acerca de:</h4>
                            <h2> <strong>Facturación Electrónica</strong></h2>
                            <hr style="width:420px;">
                            <div class="clearfix"></div>
                        </div>
                        <p class="intro"> La facturación electrónica contempla  actualmente  los   siguientes  alcances:</p>
                        <ul class="about-list">
                            <li>
                                <span class="fa fa-dot-circle-o"></span>
                               <em>Emisión de Comprobantes de Pago.</em>
                            </li>
                            <li>
                                <span class="fa fa-dot-circle-o"></span>
                                <em>Mantenimiento de Bienes y Servicios.</em>
                            </li>
                            <li>
                                <span class="fa fa-dot-circle-o"></span>
                                <em>Mantenimiento y Consultas de Clientes, Conceptos de Pago , Listas de Precios.</em>
                            </li>
                        </ul>
                    </div>
                </div>
                <a href="#tf-works" class="fa fa-angle-down page-scroll"></a>
            </div>
        </div>
    </div>
    <div id="tf-works">
        <div class="container"> <!-- Container -->
            <div class="section-title text-center center">
                <h2>NUESTROS <strong>MÓDULOS</strong></h2>
                <div class="line">
                </div>
            </div>
            <div class="categories">
                <ul class="cat">
                    <li class="pull-left"><h4>FILTRAR POR:</h4></li>
                    <li class="pull-right">
                        <ol class="type">
                            <li><a href="#" data-filter="*" class="active">Todos</a></li>
                            <li><a href="#" data-filter=".contabilidad">Contabilidad y SISG</a></li>
                            <li><a href="#" data-filter=".log_alm">Logística y Almacén</a></li>
                            <li><a href="#" data-filter=".plan_teso" >Planificación y Tesoreria</a></li>
                            <li><a href="#" data-filter=".rrhh" >Recursos Humanos</a></li>
                        </ol>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>

            <div id="lightbox" class="row">

                <div class="col-sm-6 col-md-3 col-lg-3 contabilidad">
                    <div class="portfolio-item">
                        <div class="hover-bg">
                            <a href="http://quipucamayoc.unmsm.edu.pe/SisContabilidad/inicio.xhtml">
                                <div class="hover-text">
                                    <h4>Módulo Contabilidad</h4>
                                    <div class="clearfix"></div>
                                    <i class="fa fa-eye"></i>
                                </div>
                                <img src="resources/assets/img/sistemas/ModuloContabilidad.png" class="img-responsive" alt="...">
                            </a>
                        </div>
                    </div>
                </div>

                <div class="col-sm-6 col-md-3 col-lg-3 log_alm">
                    <div class="portfolio-item">
                        <div class="hover-bg">
                            <a href="http://quipucamayoc.unmsm.edu.pe/QPAlmacenMEF/">
                                <div class="hover-text">
                                    <h4>Módulo Almacén</h4>
                                    <small>2015</small>
									<div class="clearfix"></div>
                                    <i class="fa fa-eye"></i>
                                </div>
                                <img src="resources/assets/img/sistemas/ModuloAlmacen2015.png" class="img-responsive" alt="...">
                            </a>
                        </div>
                    </div>
                </div>

                <div class="col-sm-6 col-md-3 col-lg-3 contabilidad">
                    <div class="portfolio-item">
                        <div class="hover-bg">
                            <a href="#">
                                <div class="hover-text">
                                    <h4>Sistema Seguimiento de Gastos</h4>
                                    <div class="clearfix"></div>
                                    <i class="fa fa-eye"></i>
                                </div>
                                <img src="resources/assets/img/sistemas/SistemaGestionGastos.png" class="img-responsive" alt="...">
                            </a>
                        </div>
                    </div>
                </div>

                <div class="col-sm-6 col-md-3 col-lg-3 log_alm">
                    <div class="portfolio-item">
                        <div class="hover-bg">
                            <a href="http://quipucamayoc.unmsm.edu.pe/descargas/aplicativos/setup-logistica-4.2.47.11.exe">
                                <div class="hover-text">
                                    <h4>Módulo Logistica</h4>
                                    <div class="clearfix"></div>
                                    <i class="fa fa-download"></i>
                                </div>
                                <img src="resources/assets/img/sistemas/ModuloLogistica.png" class="img-responsive" alt="...">
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-3 col-lg-3 log_alm">
                    <div class="portfolio-item">
                        <div class="hover-bg">
                            <a href="http://quipucamayoc.unmsm.edu.pe/QPAlmacen/">
                                <div class="hover-text">
                                    <h4>Módulo Almacén</h4>
                                    <small>2014</small>
									<div class="clearfix"></div>
                                    <i class="fa fa-eye"></i>
                                </div>
                                <img src="resources/assets/img/sistemas/ModuloAlmacen2014.png" class="img-responsive" alt="...">
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-3 col-lg-3 plan_teso">
                    <div class="portfolio-item">
                        <div class="hover-bg">
                            <a href="http://quipucamayoc.unmsm.edu.pe/SisPlani/pages/entrada.jsf">
                                <div class="hover-text">
                                    <h4>Sistema Integral de Planificación</h4>
                                    <div class="clearfix"></div>
                                    <i class="fa fa-eye"></i>
                                </div>
                                <img src="resources/assets/img/sistemas/SistemaPlanificacion y CompEst.png" class="img-responsive" alt="...">
                            </a>
                        </div>
                    </div>
                </div>

                <div class="col-sm-6 col-md-3 col-lg-3 plan_teso">
                    <div class="portfolio-item">
                        <div class="hover-bg">
                            <a href="http://quipucamayoc.unmsm.edu.pe/SisTesoreria/inicio.xhtml">
                                <div class="hover-text">
                                    <h4>Módulo Tesoreria</h4>
                                    <div class="clearfix"></div>
                                    <i class="fa fa-eye"></i>
                                </div>
                                <img src="resources/assets/img/sistemas/ModuloTesoreria.png" class="img-responsive" alt="...">
                            </a>
                        </div>
                    </div>
                </div>

                <div class="col-sm-6 col-md-3 col-lg-3 rrhh">
                    <div class="portfolio-item">
                        <div class="hover-bg">
                            <a href="http://quipucamayoc.unmsm.edu.pe/infer/pages/common/login.jsf">
                                <div class="hover-text">
                                    <h4>Sistema de Información Personal</h4>
                                    <div class="clearfix"></div>
                                    <i class="fa fa-eye"></i>
                                </div>
                                <img src="resources/assets/img/sistemas/SistemaInformacionPersonal.png" class="img-responsive" alt="...">
                            </a>
                        </div>
                    </div>
                </div>

                <div class="col-sm-6 col-md-3 col-lg-3 rrhh">
                    <div class="portfolio-item">
                        <div class="hover-bg">
                            <a href="http://quipucamayoc.unmsm.edu.pe/SoporteUsuarios/pages/login.jsf">
                                <div class="hover-text">
                                    <h4>Soporte a Usuarios</h4>
                                    <div class="clearfix"></div>
                                    <i class="fa fa-eye"></i>
                                </div>
                                <img src="resources/assets/img/sistemas/SoporteUsuarios.png" class="img-responsive" alt="...">
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-3 col-lg-3 rrhh">
                    <div class="portfolio-item">
                        <div class="hover-bg">
                            <a href="http://quipucamayoc.unmsm.edu.pe/SistemaPensiones/">
                                <div class="hover-text">
                                    <h4>Sistema de Pensiones</h4>
                                    <div class="clearfix"></div>
                                    <i class="fa fa-eye"></i>
                                </div>
                                <img src="resources/assets/img/sistemas/SistemaPensiones.png" class="img-responsive" alt="...">
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-3 col-lg-3 rrhh">
                    <div class="portfolio-item">
                        <div class="hover-bg">
                            <a href="http://quipucamayoc.unmsm.edu.pe/rrhh_precompromiso/pages/login.jsf">
                                <div class="hover-text">
                                    <h4>Sistema Integral de RHHH</h4>
                                    <em>PRE-COMPROMISO</em>
                                    <div class="clearfix"></div>
                                    <i class="fa fa-eye"></i>
                                </div>
                                <img src="resources/assets/img/sistemas/SistemaRRHH-PlanillaCAS y PreCompromiso.png" class="img-responsive" alt="...">
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-3 col-lg-3 rrhh">
                    <div class="portfolio-item">
                        <div class="hover-bg">
                            <a href="http://quipucamayoc.unmsm.edu.pe/SisRRHH/pages/login.jsf">
                                <div class="hover-text">
                                    <h4>Sistema Integral de RRHH</h4>
                                    <em>PLANILLA CAS</em>
                                    <div class="clearfix"></div>
                                    <i class="fa fa-eye"></i>
                                </div>
                                <img src="resources/assets/img/sistemas/SistemaRRHH-PlanillaCAS y PreCompromiso.png" class="img-responsive" alt="...">
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-3 col-lg-3 rrhh">
                    <div class="portfolio-item">
                        <div class="hover-bg">
                            <a href="http://quipucamayoc.unmsm.edu.pe/SisRRHH_erp/">
                                <div class="hover-text">
                                    <h4>Sistema Integral de RRHH</h4>
                                    <div class="clearfix"></div>
                                    <i class="fa fa-eye"></i>
                                </div>
                                <img src="resources/assets/img/sistemas/SistemaRRHH-ERP.png" class="img-responsive" alt="...">
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
    <nav id="footer">
        <div class="container">
            <div class="pull-left fnav">
                <p>DISEÑADO Y ELABORADO POR:&nbsp<a href="http://quipucamayoc.unmsm.edu.pe/web/guest/aplicaciones/">SISTEMA INTEGRAL DE GESTION FINANCIERA QUIPUCAMAYOC</a> </p>
            </div>
            <div class="pull-right fnav">
                <ul class="footer-social">
                    <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                    <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                    <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                    <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                </ul>
            </div>
        </div>
    </nav>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="resources/assets/js/jquery.1.11.1.js"></script>
    <script type="text/javascript" src="resources/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resources/assets/js/SmoothScroll.js"></script>
    <script type="text/javascript" src="resources/assets/js/jquery.isotope.js"></script>
    <script type="text/javascript" src="resources/assets/js/owl.carousel.js"></script>
    <script type="text/javascript" src="resources/assets/js/main.js"></script>
    <script type="text/javascript" src="resources/assets/js/modernizr.custom.js"></script>
</body>
</html>