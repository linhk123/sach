package com.sach.controller;

import com.sach.service.ImpBook;
import com.sach.service.ImpReading;
import com.sach.service.ImpThich;
import com.sach.service.ImpUser;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "book", urlPatterns = "/book")
public class UserController extends HttpServlet {

    private final ImpUser impUser = new ImpUser();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "login";
        switch (action) {
            case "register":
                req.getRequestDispatcher("register.jsp").forward(req, resp);
                break;
            case "forget":
                req.getRequestDispatcher("forget.jsp").forward(req, resp);
                break;
            default:
                req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "login";
        switch (action) {
            case "login":
                handLogin(req,resp);
                break;
            case "register":
                handRegister(req,resp);
                break;
            case "forget":
                handForget(req,resp);
                break;
        }
    }

    private void handForget(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password= impUser.forgetPassword(email);
        if(password != null) {
            req.setAttribute("password",password);

        }else{
            req.setAttribute("error","Email không tồn tại");

        }
        req.getRequestDispatcher("forget.jsp").forward(req,resp);
    }

    private void handRegister(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        impUser.register(name, email, password);

        req.getSession().setAttribute("user", impUser);
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void handLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if(impUser.login(name, password))
        {
            resp.sendRedirect("/home");
        }else{
            req.setAttribute("error", "Sai tên hoặc mật khẩu");
            resp.sendRedirect("login.jsp");
        }
    }

}
