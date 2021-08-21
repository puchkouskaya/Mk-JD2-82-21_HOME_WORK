import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringUtil - класс, который работает со строками, используя методы класса`String'
 */
public class StringUtil {
    //task1

    /**
     * Метод, позволяющий соединить две строки
     * @param str1 - строка номер 1
     * @param str2 - строка номер 2
     * @return возвращает строку, соединенную из двух строк
     */
    public String glue(String str1, String str2) {
        return str1.concat(str2);
    }

    //task2

    /**
     * Метод, позволяющий найти индекс вводимого символа в строке
     * @param str - строка, в которой выполняется поиск
     * @param symbol - символ вводимый пользователем
     * @return возвращает индекс искомого символа в строке, если такого символа нет, то возвращает " -1`
     */
    public int indexSearch(String str, char symbol) {
        return str.indexOf(symbol);
    }

    //task3

    /**
     * Метод, позволяющий определить, являются ли две строки одинаковыми, учитывая регистр
     * @param str1 - строка номер 1
     * @param str2 - строка номер 2
     * @return возвращает true - если две строки являются одинаковыми / false - если не являются
     */
    public boolean comparingString(String str1, String str2) {
        return str1.equals(str2);
    }

    //task4

    /**
     * Метод, который удаляет пробелы в начале и конце строки, а затем делает всю строку состоящей из прописных букв
     * @param str - строка
     * @return возвращает измененную строку без пробелов и состоящую из прописных букв
     */
    public String modificationString(String str) {
        return str.trim().toUpperCase();
    }

    //task5

    /**
     * Метод, который извлекает подстроку из строки, начиная с n-го символа и до m-го символа
     * @param str - строка
     * @param begin - индекс, с которого начинают извлекаться символы
     * @param end - индекс, до которого извлекаются символы
     * @return возвращает полученную подстроку
     */
    public String getSubstring(String str, int begin, int end) {
        return str.substring(begin, end);
    }

    //task6

    /**
     * Метод, который заменяет в строке все грустные *emoji* `:(` на веселые `:)`
     * @param str - строка
     * @param symbol1 - грустные *emoji* `:(`
     * @param symbol2 - веселые *emoji* `:)`
     * @return возвращает измененную строку
     */
    public String replaceEmoji(String str, String symbol1, String symbol2) {
        return str.replace(symbol1, symbol2);
    }

    //task7

    /**
     * Метод, позволяющий определить, начинается ли  и заканчивается ли строка определенным словом
     * @param text - строка
     * @param word - слово, с которого должна начинаться и заканчиваться строка
     * @return возвращает true - если строка начинается и заканчивается определенным словом / false - если нет
     */
    public boolean startEndWith(String text, String word) {
        return text.startsWith(word) && text.endsWith(word);
    }

    //task8

    /**
     * Метод, позволяющий определить количество английских гласных букв в предложении
     * @param str - строка
     * @return возвращает количество английских гласных букв
     */
    public int countEnglishVowelLetters(String str) {
        int countEnglishVowelLetters = 0;

        Pattern pattern = Pattern.compile("[aAeEiIoOuUyY]");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            countEnglishVowelLetters++;
        }
        return countEnglishVowelLetters;
    }

    //task9

    /**
     * Метод, позволяющий подсчитать общее количество знаков препинания
     * (точек, запятых, вопросительных и восклицательных знаков) в строке
     * @param str - строка
     * @return возвращает количество знаков препинания
     */
    public int countPunctuationMarks(String str) {
        int countPunctuationMarks = 0;

        Pattern pattern = Pattern.compile("[.,!?]");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            countPunctuationMarks++;
        }
        return countPunctuationMarks;
    }

    //task10

    /**
     * Метод, позволяющий проверить, является ли введенная строка палиндромом
     * NOTE: Палиндром - это слово, фраза или число, которое одинаково читается слева направо и справа налево
     * Например: `deed`, `Do geese see God?`, `1991`
     * @param str - строка
     * @return возвращает true - если строка является палиндромом / false - если нет
     */
    public boolean palindromeSearch(String str) {
        str = str.toLowerCase();
        str = str.replaceAll("[.,!?;:\\s]","");
        return str.equals((new StringBuilder(str)).reverse().toString());
    }
}
