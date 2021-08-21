package by.it_academy.jd2.Mk_JD2_82_21.controller;

import by.it_academy.jd2.Mk_JD2_82_21.comparator.VoteComparator;
import by.it_academy.jd2.Mk_JD2_82_21.exception.GenresException;
import by.it_academy.jd2.Mk_JD2_82_21.service.VoteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {
    private final VoteService service;

    public VoteServlet() {
        this.service = VoteService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException
    {
        req.getRequestDispatcher("/vote.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String artist = req.getParameter("artist");
        String[] genres = req.getParameterValues("genre");
        String about = req.getParameter("about");

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        try {
            this.service.addVote(artist, genres, about);

            Map<String, Integer> artistResult = this.service.getArtistResult();
            Map<String, Integer> genreResult = this.service.getGenreResult();
            List<String> aboutResult = this.service.getAboutResult();

            writer.write("<h2>Результаты голосования</h2>");
            writer.write("<h3>Лучший исполнитель :</h3>");
            Map<String, Integer> resultArtist = new LinkedHashMap<> ();
            Stream<Map.Entry<String, Integer>> streamArtist = artistResult.entrySet().stream();
            streamArtist.sorted (new VoteComparator ())
                    .forEach (e -> resultArtist.put (e.getKey (), e.getValue ()));
            for (Map.Entry<String, Integer> entry : resultArtist.entrySet()) {
                switch (entry.getKey()) {
                    case "1":
                        writer.write("<ol><li>Zivert ");
                        break;
                    case "2":
                        writer.write("<ol><li>INNA ");
                        break;
                    case "3":
                        writer.write("<ol><li>Minelli ");
                        break;
                    case "4":
                        writer.write("<ol><li>Asti ");
                        break;
                }
                writer.write(entry.getValue() + "</li></ol>");
            }
            writer.write("<p></p>");

            writer.write("<h3>Любимые жанры музыки :</h3>");
            Map<String, Integer> resultGenres = new LinkedHashMap<>();
            Stream <Map.Entry<String, Integer>> streamGenres = genreResult.entrySet().stream();
            streamGenres.sorted (new VoteComparator ())
                    .forEach (e -> resultGenres.put (e.getKey (), e.getValue ()));
            for (Map.Entry<String, Integer> entry : resultGenres.entrySet()) {
                switch (entry.getKey()){
                    case "1":
                        writer.write("<li>Classic ");
                        break;
                    case "2":
                        writer.write("<li>Jazz ");
                        break;
                    case "3":
                        writer.write("<li>Opera ");
                        break;
                    case "4":
                        writer.write("<li>Rock ");
                        break;
                    case "5":
                        writer.write("<li>Techno ");
                        break;
                    case "6":
                        writer.write("<li>Pop ");
                        break;
                    case "7":
                        writer.write("<li>Hard-Rock ");
                        break;
                    case "8":
                        writer.write("<li>Disco ");
                        break;
                    case "9":
                        writer.write("<li>Rap ");
                        break;
                    case "10":
                        writer.write("<li>Folk ");
                        break;
                }
                writer.write(entry.getValue() + "</li>");
            }
            writer.write("<p></p>");

            writer.write("<h3>Информация о проголосовавших лицах :</h3>");
            for (String s : aboutResult) {
                writer.println("<ol><li>" + s + "</li></ol>");
            }
            writer.write("<p></p>");
        } catch (GenresException exception) {
            writer.println(exception.getMessage ());
        }
    }
}
