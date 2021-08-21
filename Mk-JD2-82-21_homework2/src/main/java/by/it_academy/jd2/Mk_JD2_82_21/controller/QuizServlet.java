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
import java.util.*;
import java.util.stream.Stream;

@WebServlet(name = "QuizServlet", urlPatterns = "/quiz")
public class QuizServlet extends HttpServlet {

    private final VoteService service;

    public QuizServlet() {
        this.service = VoteService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        writer.write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "\t<meta charset=\"UTF-8\">\n" +
                "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "\t<title>Квиз</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\t<form action=\"/Mk-JD2-82-21-0.0.0-SNAPSHOT/\" method=\"POST\">\n" +
                "\t\t<label for=\"artist\">Группа</label>\n" +
                "\t\t<select name=\"artist\">\n" +
                "\t\t\t<option value=\"1\">Григорий Лепс</option>\n" +
                "\t\t\t<option value=\"2\">Каста</option>\n" +
                "\t\t\t<option value=\"3\">Руки Вверх</option>\n" +
                "\t\t\t<option value=\"4\">Иванушки</option>\n" +
                "\t\t</select><br/>\n" +
                "\t\t<label for=\"genre\">Жанр</label><br/>\n" +
                "\t\t<input type=\"checkbox\" name=\"genre\" value=\"1\"/> Рок <br/>\n" +
                "\t\t<input type=\"checkbox\" name=\"genre\" value=\"2\"/> Поп <br/>\n" +
                "\t\t<input type=\"checkbox\" name=\"genre\" value=\"3\"/> Фолк <br/>\n" +
                "\t\t<input type=\"checkbox\" name=\"genre\" value=\"4\"/> Альт <br/>\n" +
                "\t\t<input type=\"checkbox\" name=\"genre\" value=\"5\"/> Классика <br/>\n" +
                "\t\t<input type=\"checkbox\" name=\"genre\" value=\"6\"/> Джаз <br/>\n" +
                "\t\t<input type=\"checkbox\" name=\"genre\" value=\"7\"/> Тиктоник <br/>\n" +
                "\t\t<input type=\"checkbox\" name=\"genre\" value=\"8\"/> Панк-рок <br/>\n" +
                "\t\t<input type=\"checkbox\" name=\"genre\" value=\"9\"/> Рэп <br/>\n" +
                "\t\t<input type=\"checkbox\" name=\"genre\" value=\"10\"/> Диско <br/>\n" +
                "\t\t<label for=\"about\">О себе</label><br/>\n" +
                "\t\t<textarea name=\"about\"></textarea>\n" +
                "\t\t<input type=\"submit\" name=\"Отправить\">\n" +
                "\t</form>\n" +
                "</body>\n" +
                "</html>");
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
            Map<String, Integer> resultArtist = new LinkedHashMap<>();
            Stream <Map.Entry<String, Integer>> streamArtist = artistResult.entrySet().stream();
            streamArtist.sorted (new VoteComparator ())
                    .forEach (e -> resultArtist.put (e.getKey (), e.getValue ()));
            for (Map.Entry<String, Integer> entry : resultArtist.entrySet()) {
                switch (entry.getKey()) {
                    case "1":
                        writer.write("<ol><li>Григорий Лепс ");
                        break;
                    case "2":
                        writer.write("<ol><li>Каста ");
                        break;
                    case "3":
                        writer.write("<ol><li>Руки Вверх ");
                        break;
                    case "4":
                        writer.write("<ol><li>Иванушки ");
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
                        writer.write("<li>Рок ");
                        break;
                    case "2":
                        writer.write("<li>Поп ");
                        break;
                    case "3":
                        writer.write("<li>Фолк ");
                        break;
                    case "4":
                        writer.write("<li>Альт ");
                        break;
                    case "5":
                        writer.write("<li>Классика ");
                        break;
                    case "6":
                        writer.write("<li>Джаз ");
                        break;
                    case "7":
                        writer.write("<li>Тиктоник ");
                        break;
                    case "8":
                        writer.write("<li>Панк-рок ");
                        break;
                    case "9":
                        writer.write("<li>Рэп ");
                        break;
                    case "10":
                        writer.write("<li>Диско ");
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
