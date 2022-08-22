<%@page import='Conexion.Test' %>
<!Doctype html>
<html>

<body>
<head>

<%
try{
	Test te=new Test();
	te.main(args);
}catch(Exception e){
	out.write("ya valio");
}


%>


</head>
</body>
</html>