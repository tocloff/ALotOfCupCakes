<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//ES" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="La mejor tienda online de Ubuntu">
<meta name="author" content="Santiago Hoyos">

<title>A Lot of cupcakes</title>


<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/shop-homepage.css" rel="stylesheet">

<!-- REGLAS CSS -->
<style type="text/css">
@media ( min-width :768px) {#ficha {width: 99%}}
@media ( min-width : 992px) {#ficha{width: 66.6666%}.col-md-4{margin-top: 2%}#slider{margin-top:5%;width:100%}#categorias{margin-left:1%;margin-right:2%}}
#login{margin-top: 10%;margin-bottom: 10%;height: 100%;}
</style>

<style type="text/css">
.tg  {border-collapse:collapse;border-spacing:0;border-color:#aaa; width: 98%;}
.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aaa;color:#333;background-color:#fff;}
.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aaa;color:#fff;background-color:#f38630;}
.tg .tg-lpja{font-family:"Lucida Sans Unicode", "Lucida Grande", sans-serif !important;;background-color:#dd5323;color:#343300;text-align:center;vertical-align:top}
.tg .tg-txeu{font-family:"Lucida Sans Unicode", "Lucida Grande", sans-serif !important;;background-color:#ffffff;color:#ffffff;text-align:center;vertical-align:top}
.tg .tg-myfg{font-weight:bold;font-family:"Lucida Sans Unicode", "Lucida Grande", sans-serif !important;;background-color:#dd4814;text-align:center;vertical-align:top}
.tg .tg-krq5{font-family:"Lucida Sans Unicode", "Lucida Grande", sans-serif !important;;background-color:#343434;color:#ffffff;text-align:center;vertical-align:top}
.tg .tg-k9ij{font-family:"Lucida Sans Unicode", "Lucida Grande", sans-serif !important;;text-align:center;vertical-align:top}
</style>
<style type="text/css">
body {background: #f1f1f1;}
.tg6  {border-collapse:collapse;border-spacing:0;border-color:#aaa; width: 100%;}
.tg6 td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:0px;overflow:hidden;word-break:normal;border-color:#aaa;color:#333;background-color:#fff;border-top-width:1px;border-bottom-width:1px;}
.tg6 th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:0px;overflow:hidden;word-break:normal;border-color:#aaa;color:#fff;background-color:#f38630;border-top-width:1px;border-bottom-width:1px;}
.tg6 .tg-baqh{text-align:center;vertical-align:top}
.tg6 .tg-yw4l{vertical-align:top; text-align: center;}
</style>

</head>
<body>

<% 	//Este response parece ser el unico que puede escribir cookies en el cliente los otros de los servlets no.(aunque se supone es el mismo)
	request.getSession().setAttribute("response", response); 
%>
		<!-- INICIO DE  CABECERA -->
		<jsp:include page="/WEB-INF/Cabecera.jsp"/>
		<!-- FIN DE CABECERA -->

		<!-- INICIO  LLAMADA AL SELECTOR PARA CONTENIDO -->
			<jsp:include page="/Selector"/>
		<!-- FIN DEL LA LLAMADA AL SELECTOR PARA CONTENIDO -->

		<!-- INICIO DE  PIE -->
		<jsp:include page="/WEB-INF/Pie.jsp"/>
		<!-- FIN DE PIE -->
</body>

</html>
