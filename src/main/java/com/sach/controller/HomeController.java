package com.sach.controller;

import com.sach.entity.book;
import com.sach.service.ImpBook;
import com.sach.service.ImpReading;
import com.sach.service.ImpThich;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeController extends HttpServlet {
    private ImpBook bookService;
    private ImpReading readingService;
    private ImpThich favoriteService;

    @Override
    public void init() {
        bookService = new ImpBook();
        readingService = new ImpReading();
        favoriteService = new ImpThich();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";

        int userId = 1; // giả lập user

        switch (action) {
            case "search":
                String keyword = req.getParameter("keyword");
                if(keyword == null || keyword.trim().isEmpty()) {
                    // input rỗng -> show tất cả sách
                    showHome(req, resp, userId);
                } else {
                    searchBook(req, resp, userId, keyword);
                }
                break;
            default:
                showHome(req, resp, userId);
                break;
        }
    }

    private void showHome(HttpServletRequest req, HttpServletResponse resp, int userId) throws ServletException, IOException {
        List<book> books = bookService.getAllBooks();
        List<book> favoriteBooks = favoriteService.getFavorites(userId);
        List<book> readBooks = readingService.getReadingByUser(userId);

        req.setAttribute("books", books);
        req.setAttribute("favoriteBooks", favoriteBooks);
        req.setAttribute("readBooks", readBooks);

        RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
        dispatcher.forward(req, resp);
    }

    private void searchBook(HttpServletRequest req, HttpServletResponse resp, int userId, String keyword) throws ServletException, IOException {
        List<book> searchResults = bookService.searchBooks(keyword);

        req.setAttribute("books", searchResults);
        req.setAttribute("favoriteBooks", favoriteService.getFavorites(userId));
        req.setAttribute("readBooks", readingService.getReadingByUser(userId));

        RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int userId = Integer.parseInt(req.getParameter("id_user"));
        int bookId = Integer.parseInt(req.getParameter("id_book"));

        if ("favorite".equals(action)) {
            boolean favorite = Boolean.parseBoolean(req.getParameter("favorite"));
            if (favorite) favoriteService.addFavorite(userId, bookId);
            else favoriteService.removeFavorite(userId, bookId);
            resp.getWriter().write("OK");
        } else if ("read".equals(action)) {
            readingService.markAsRead(userId, bookId);
            resp.getWriter().write("OK");
        }
    }
}


