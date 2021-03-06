/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Asignaciones;
import Modelo.Ciudades;
import Modelo.Turistas;
import ModeloDAO.AsignacionesDAO;
import ModeloDAO.CiudadesDAO;
import ModeloDAO.TuristasDAO;
import java.io.PrintWriter; 
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admonsis
 */
public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    String listarTuristas="Vista/listarTuristas.jsp";
    String addTuristas="Vista/addTuristas.jsp";
    String editTuristas="Vista/editTuristas.jsp";
    String deleteTuristas="Vista/deleteTuristas.jsp";
    Turistas turista = new Turistas();
    TuristasDAO turistaDAO= new TuristasDAO();

    String listarCiudades="Vista/listarCiudades.jsp";
    String addCiudades="Vista/addCiudades.jsp";
    String editCiudades="Vista/editCiudades.jsp";
    String deleteCiudades="Vista/deleteCiudades.jsp";
    Ciudades ciudad = new Ciudades();
    CiudadesDAO ciudadDAO= new CiudadesDAO();


    String listarAsignacionesTurista="Vista/listarAsignacionesTurista.jsp";
    String listarAsignacionesCiudad="Vista/listarAsignacionesCiudad.jsp";
    String editAsignaciones="Vista/editAsignaciones.jsp";
    String addAsignaciones="Vista/addAsignaciones.jsp";
    Asignaciones asignacion = new Asignaciones();
    AsignacionesDAO asignacionDAO= new AsignacionesDAO(); 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controlador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controlador at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("listarTuristas")){
            acceso=listarTuristas;
        }
        else if(action.equalsIgnoreCase("addTuristas")){
           acceso=addTuristas; 
        }        
        else if(action.equalsIgnoreCase("editTuristas")){
            request.setAttribute("Id",request.getParameter("Id"));
            acceso=editTuristas; 
        }      
        else if(action.equalsIgnoreCase("deleteTuristas")){
            int Id =Integer.parseInt(request.getParameter("Id"));
            turistaDAO.delete(Id);
            acceso=listarTuristas; 
        }
        else if(action.equalsIgnoreCase("listarCiudades")){
            acceso=listarCiudades;
        }
        else if(action.equalsIgnoreCase("addCiudades")){
           acceso=addCiudades; 
        }        
        else if(action.equalsIgnoreCase("editCiudades")){
            request.setAttribute("Id",request.getParameter("Id"));
            acceso=editCiudades; 
        }      
        else if(action.equalsIgnoreCase("deleteCiudades")){
            int Id =Integer.parseInt(request.getParameter("Id"));
            ciudadDAO.delete(Id);
            acceso=listarCiudades; 
        }  
        else if(action.equalsIgnoreCase("listarAsignacionesTuristas")){
           request.setAttribute("Id",request.getParameter("Id"));
           acceso=listarAsignacionesTurista;
        }
        else if(action.equalsIgnoreCase("addAsignaciones")){           
           request.setAttribute("IdTurista",request.getParameter("IdTurista"));           
           acceso=addAsignaciones; 
        }
        else if(action.equalsIgnoreCase("editAsignaciones")){
           request.setAttribute("Id",request.getParameter("Id"));           
           acceso=editAsignaciones; 
        }
        else if(action.equalsIgnoreCase("deleteAsignaciones")){       
            int Id =Integer.parseInt(request.getParameter("Id"));
            asignacionDAO.delete(Id);
            request.setAttribute("Id",request.getParameter("IdTuristas")); 
            acceso=listarAsignacionesTurista; 
        }
        else if(action.equalsIgnoreCase("listarAsignacionesCiudad")){
           request.setAttribute("Id",request.getParameter("Id"));
           acceso=listarAsignacionesCiudad;
        }        
        RequestDispatcher Vista=request.getRequestDispatcher(acceso);
        Vista.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("Agregar Turista")){
           String NombreCompleto =request.getParameter("NombreCompleto");
           String FechaNacimiento =request.getParameter("FechaNacimiento");
           int Documento =Integer.parseInt(request.getParameter("Documento"));
           String TipoDocumento =request.getParameter("TipoDocumento");
           int FrecuenciaViaje =Integer.parseInt(request.getParameter("FrecuenciaViaje"));
           turista.setNombreCompleto(NombreCompleto);
           turista.setFechaNacimiento(FechaNacimiento);
           turista.setDocumento(Documento);
           turista.setTipoDocumento(TipoDocumento);
           turista.setFrecuenciaViaje(FrecuenciaViaje);
           turistaDAO.add(turista);
           acceso=listarTuristas;
        }        
        else if(action.equalsIgnoreCase("Editar Turista")){
           int Id =Integer.parseInt(request.getParameter("Id")); 
           String NombreCompleto =request.getParameter("NombreCompleto");
           String FechaNacimiento =request.getParameter("FechaNacimiento");
           int Documento =Integer.parseInt(request.getParameter("Documento"));
           String TipoDocumento =request.getParameter("TipoDocumento");
           int FrecuenciaViaje =Integer.parseInt(request.getParameter("FrecuenciaViaje"));
           turista.setId(Id);
           turista.setNombreCompleto(NombreCompleto);
           turista.setFechaNacimiento(FechaNacimiento);
           turista.setDocumento(Documento);
           turista.setTipoDocumento(TipoDocumento);
           turista.setFrecuenciaViaje(FrecuenciaViaje);
           turistaDAO.edit(turista);
           acceso=listarTuristas;
        }
        else if(action.equalsIgnoreCase("Agregar Ciudad")){
           String Nombre =request.getParameter("Nombre");
           int CantidadHabitantes =Integer.parseInt(request.getParameter("CantidadHabitantes"));
           String SitioTuristico =request.getParameter("SitioTuristico");
           String HotelReservado =request.getParameter("HotelReservado");
           ciudad.setNombre(Nombre);
           ciudad.setCantidadHabitantes(CantidadHabitantes);
           ciudad.setSitioTuristico(SitioTuristico);
           ciudad.setHotelReservado(HotelReservado);
           ciudadDAO.add(ciudad);
           acceso=listarCiudades;
        }        
        else if(action.equalsIgnoreCase("Editar Ciudad")){
           int Id=Integer.parseInt(request.getParameter("Id"));
           String Nombre =request.getParameter("Nombre");
           int CantidadHabitantes =Integer.parseInt(request.getParameter("CantidadHabitantes"));
           String SitioTuristico =request.getParameter("SitioTuristico");
           String HotelReservado =request.getParameter("HotelReservado");
           ciudad.setId(Id);
           ciudad.setNombre(Nombre);
           ciudad.setCantidadHabitantes(CantidadHabitantes);
           ciudad.setSitioTuristico(SitioTuristico);
           ciudad.setHotelReservado(HotelReservado);
           ciudadDAO.edit(ciudad);
           acceso=listarCiudades;
        }
        else if(action.equalsIgnoreCase("Agregar viaje")){
           int IdTurista=Integer.parseInt(request.getParameter("IdTurista"));
           int IdCiudad=Integer.parseInt(request.getParameter("IdCiudad"));
           double PresupuestoViaje=Double.parseDouble(request.getParameter("PresupuestoViaje"));
            System.out.println(PresupuestoViaje);
           String Fecha =request.getParameter("Fecha");
           boolean UsaTarjeta =Boolean.parseBoolean(request.getParameter("UsaTarjeta"));
           asignacion.setIdTurista(IdTurista);
           asignacion.setIdCiudad(IdCiudad);
           asignacion.setPresupuestoViaje(PresupuestoViaje);
           asignacion.setFecha(Fecha);
           asignacion.setUsaTarjeta(UsaTarjeta);
           String mensaje=asignacionDAO.add(asignacion);
           request.setAttribute("Id",request.getParameter("IdTurista"));
           request.setAttribute("mensaje",mensaje);
           acceso=listarAsignacionesTurista;
        }
        else if(action.equalsIgnoreCase("Editar viaje")){
           int Id=Integer.parseInt(request.getParameter("Id"));
           int IdTurista=Integer.parseInt(request.getParameter("IdTurista"));
           int IdCiudad=Integer.parseInt(request.getParameter("IdCiudad"));
           double PresupuestoViaje=Double.parseDouble(request.getParameter("PresupuestoViaje"));
           String Fecha =request.getParameter("Fecha");
           boolean UsaTarjeta =Boolean.parseBoolean(request.getParameter("UsaTarjeta"));
           asignacion.setId(Id);
           asignacion.setIdTurista(IdTurista);
           asignacion.setIdCiudad(IdCiudad);
           asignacion.setPresupuestoViaje(PresupuestoViaje);
           asignacion.setFecha(Fecha);
           asignacion.setUsaTarjeta(UsaTarjeta);
           String mensaje=asignacionDAO.edit(asignacion);
           request.setAttribute("Id",request.getParameter("IdTurista"));
           request.setAttribute("mensaje",mensaje);
           acceso=listarAsignacionesTurista;            
        }        
        RequestDispatcher Vista=request.getRequestDispatcher(acceso);
        Vista.forward(request, response);
    }
    


    @Override
    public String getServletInfo() { 
        return "Short description";
    }

}
