<%@page import="modelado.Usuario"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<img id="icono" src="imagenesWeb/icono.png" />
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Menu</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" id="titulo" href="/ALotOfCupcakes/index.jsp?accion=index">A Lot of cupcakes</a>
		</div>
		
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<%
			//si en sesión no hay ningún usuario eso quiere decir que no hay un login por lo que imprime la cabecera de invitado.
			if(request.getSession().getAttribute("usuario")==null){ %>
			<ul class="nav navbar-nav">
				<li><a href="/ALotOfCupcakes/index.jsp?accion=index">Inicio</a></li>
				<li><a href="/ALotOfCupcakes/index.jsp?accion=login">Iniciar Sesión </a></li>
				<li><a href="/ALotOfCupcakes/index.jsp?accion=registrar">Registrarse </a></li>
			</ul>
			<%}else{
				//comprobacion del nivel de privilegios del usuario para saber si es admin o no lo es e imprime
				//una cabecera u otra segun sea.
				if(!((Usuario)request.getSession().getAttribute("usuario")).getPrivilegio().equals("cliente")){%>
					<ul class="nav navbar-nav">
						<li><a href="/ALotOfCupcakes/index.jsp?accion=index">Inicio</a></li>
						<li><a href="/ALotOfCupcakes/index.jsp?accion=carrito">Carrito
						(<%=request.getCookies()==null?0:request.getCookies().length-1%>)</a></li>
						<li><a href="/ALotOfCupcakes/index.jsp?accion=config">Mi Perfil</a></li>
						<li><a href="/ALotOfCupcakes/index.jsp?accion=admin">Panel Administración</a></li>
						<li><a href="/ALotOfCupcakes/index.jsp?accion=salir">Cerrar Sesion</a></li>
					</ul>
			<%}else{%>
					<ul class="nav navbar-nav">
						<li><a href="/ALotOfCupcakes/index.jsp?accion=index">Inicio</a></li>
						<li><a href="/ALotOfCupcakes/index.jsp?accion=carrito">Carrito
						(<%=request.getCookies()==null?0:request.getCookies().length-1%>)</a></li>
						<li><a href="/ALotOfCupcakes/index.jsp?accion=config">Mi perfil</a></li>
						<li><a href="/ALotOfCupcakes/index.jsp?accion=salir">Cerrar Sesion</a></li>
					</ul>
			<%}%>
			<%}%>
		</div>
	</div>
	</nav>
	
	<div class="container">
		<div class="row">
		
		<%  //Comprueba que se está en index antes de decidir si imprime o no el slider o no
		String estilo_menu="";
		if(request.getParameter("accion")==null || request.getParameter("accion").equals("index")){ 
		estilo_menu="style=\"{width=100%}\"";%>
		
		      <div id="slider" class="col-md-9">
                <div class="row carousel-holder">
                    <div class="col-md-12">
                        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            </ol>
                            <div class="carousel-inner">
                                <div class="item active">
                                    <img class="slide-image" src="/ALotOfCupcakes/imagenesProductos/1.jpg" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="/ALotOfCupcakes/imagenesProductos/2.jpg" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="/ALotOfCupcakes/imagenesProductos/3.jpg" alt="">
                                </div>
                            </div>
                            <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </a>
                            <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                            </a>
                        </div>
                    </div>
                </div>
	<%}%>
		<%	
		//comprueba que la pagina en la que esta no es la del panel.
		if(request.getParameter("accion")==null || !request.getParameter("accion").equals("admin") 
		&& !request.getParameter("accion").equals("gestorProductos") && !request.getParameter("accion").equals("gestorFotos")){%>

			<div class="col-md-3">
				<p class="lead" style="margin-top: 2%;">Productos:</p>
				<div class="list-group" id="categorias" >
					<a
						href="/ALotOfCupcakes/index.jsp?accion=productos&productos=Cupcakes"
						class="list-group-item">Cupcakes</a> <a
						href="/ALotOfCupcakes/index.jsp?accion=productos&productos=Empapados"
						class="list-group-item">Empapados</a> <a
						href="/ALotOfCupcakes/index.jsp?accion=productos&productos=Pasteles"
						class="list-group-item">Pasteles</a> <a
						href="/ALotOfCupcakes/index.jsp?accion=productos&productos=Brownies"
						class="list-group-item">Brownies</a><a
						href="/ALotOfCupcakes/index.jsp?accion=productos&productos=Pays"
						class="list-group-item">Pays</a><a
						href="/ALotOfCupcakes/index.jsp?accion=productos&productos=Postres de la semana"
						class="list-group-item">Postres de la semana</a>
				</div>
			</div>
		<%}%>
			
			