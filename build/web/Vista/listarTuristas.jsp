

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Turistas"%>
<%@page import="ModeloDAO.TuristasDAO"%>
<% response.setContentType( "text/html; charset=utf-8" ); %>
<%@page contentType="text/html" pageEncoding="utf-16"%>

<!DOCTYPE html>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="CSS/estilos.css">
    <title>Turistas</title>
    </head>
    <body>
        <section>
            <div class='container'>
                <div class="col-sm">
                    <div class="align-items-center text-center ">
                        <h1> Turistas Registrados</h1>
                    </div><br>
                    <div class="table-responsive">
                        <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th >ID</th>
                                <th class="tablaEncabezado">Nombre completo</th>
                                <th>Fecha de nacimiento</th>
                                <th>Documento</th>
                                <th>Tipo de docuemnto</th>
                                <th>Frecuencia de viaje</th>
                                <th  class="tablaEncabezado" >Acciones</th>
                            </tr>
                        </thead>
                        <%
                            TuristasDAO dao = new TuristasDAO();
                            List<Turistas>list=dao.listar();
                            Iterator<Turistas>iter=list.iterator();
                            Turistas turista=null;
                            while (iter.hasNext()){
                                turista = iter.next();
                        %>
                        <tbody>
                            <tr>
                                <td><%= turista.getId() %></td>
                                <td><%= turista.getNombreCompleto() %></td>
                                <td><%= turista.getFechaNacimiento()%></td>
                                <td><%= turista.getDocumento() %></td>
                                <td>
                                    <%
                                        if (turista.getTipoDocumento().equals("CC")){
                                    %>
                                        Cédula de ciudadanía
                                    <%
                                        } else if(turista.getTipoDocumento().equals("CE")){
                                    %>
                                        Cédula de extranjería
                                    <%
                                        } else if(turista.getTipoDocumento().equals("P")){
                                    %>
                                        Pasaporte
                                    <%
                                        } else if(turista.getTipoDocumento().equals("TI")){
                                    %>
                                        Tarjeta de identidad
                                    <%
                                        } else if(turista.getTipoDocumento().equals("O")){
                                    %>
                                        Otro
                                    <%                                        
                                        }
                                    %>
                                </td>
                                <td><%= turista.getFrecuenciaViaje() %></td>
                                <td class="tablaEncabezado" >
                                    <a href="Controlador?accion=editTuristas&Id=<%= turista.getId() %>" class="btn btn-primary botonesTabla">&nbsp; Editar&nbsp;</a>
                                    <a href="Controlador?accion=deleteTuristas&Id=<%= turista.getId() %>"class="btn btn-danger botonesTabla">Eliminar</a>
                                    <a href="Controlador?accion=listarAsignacionesTuristas&Id=<%= turista.getId() %>"class="btn btn-warning botonesTabla ">&nbsp; Viajes&nbsp;</a>                                   
                                </td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                    </div>
                    <br>
                    <div>
                        <div class="row">
                            <div class="col-sm-6">
                               <a href="Controlador?accion=addTuristas" class="btn btn-success btn-block">Agregar turista</a>
                            </div>
                            <div class="col-sm-6">
                                <a href="index.jsp" class="btn btn-danger btn-block">Regresar</a><br>
                            </div>
                        </div>
                    </div>
                </div>
               <br>                         
            </div>
        </section>
    </body>
</html>
