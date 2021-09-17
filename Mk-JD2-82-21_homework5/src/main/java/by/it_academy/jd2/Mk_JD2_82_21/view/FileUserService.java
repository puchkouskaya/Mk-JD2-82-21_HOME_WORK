package by.it_academy.jd2.Mk_JD2_82_21.view;

import by.it_academy.jd2.Mk_JD2_82_21.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.view.api.UserWebApp;
import by.it_academy.jd2.Mk_JD2_82_21.view.storage.UserStorage;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class FileUserService implements UserWebApp {
    private final static FileUserService instance = new FileUserService ();
    private static final String FILE_USERS = "users.ser";
    Map<String, User> newUser = new HashMap<> ();

    private FileUserService() {

    }

    @Override
    public void registration(User user) {
        File file = new File(FILE_USERS);
        String login = user.getLogin();
        String password = user.getPassword();
        String fio = user.getFio();
        String dateOfBirth = user.getDateOfBirth ();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))){
            String userFile = login + " " + password + " " + fio + " " + dateOfBirth + " " + "\n";
            bufferedWriter.write(userFile);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }


    public User getUser(String login) {
        String line = null;
        User user = null;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_USERS))){
            while ((line = bufferedReader.readLine()) != null) {

                String[] array = line.split(" ");
                login = array[0];
                String password = array[1];
                String fio = array[2];
                String dateOfBirth = array[3];

                user = new User();
                user.setLogin(login);
                user.setPassword (password);
                user.setFio (fio);
                user.setDateOfBirth (dateOfBirth);
                break;
            }
        } catch (IOException ex) {
        }
        return user;
    }


    @Override
    public User authentication(String login, String password) {
        User user;
        user = getUser (login);
        if(user == null) {
            return null;
        }
        if(!Objects.equals(user.getPassword(), password)){
            return null;
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<> ();
        String line = null;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_USERS))){
            while ((line = bufferedReader.readLine()) != null){
                String[] array = line.split(" ");
                String login = array[0];
                String password = array[1];
                String fio = array[2];
                String dateOfBirth = array[3];

                User user = new User();
                user.setLogin(login);
                user.setPassword (password);
                user.setFio (fio);
                user.setDateOfBirth (dateOfBirth);
                allUsers.add(user);
            }
        } catch (IOException ex){
        }
        return allUsers;
    }

    public static FileUserService getInstance() {
        return instance;
    }
}
