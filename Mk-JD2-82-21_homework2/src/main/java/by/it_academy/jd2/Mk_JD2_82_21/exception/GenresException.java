package by.it_academy.jd2.Mk_JD2_82_21.exception;

public class GenresException extends Exception {
    public GenresException() {
        super("<h2>ОШИБКА!!!</h2>" +
                "<p>Обращаем Ваше внимание на то, что необходимо выбрать от 3-х до 5-ти любимых жанров, </p>" +
                "<p>иначе госование не будет засчитано!</p>" +
                "<p>Проголосуйте, пожалуйста, еще раз с сооблюдением правил!</p>");
    }
}
