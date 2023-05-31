import exceptions.WrongLoginException;
import exceptions.WrongPasswordException;

public class Main {

    private static final String VALIDATE_PATTERN = "^[A-z0-9-_]+$";

    public static void main(String[] args) {
        enterLoginAndPassword("login", "qwerty", "qwerty");
        enterLoginAndPassword("login~", "qwerty", "qwerty");
        enterLoginAndPassword("login", "~qwerty~", "qwerty");
        enterLoginAndPassword("login_login_login_login_login_login", "qwerty", "qwerty");
        enterLoginAndPassword("Login", "Qwerty", "Qwerty");
    }

    private static boolean enterLoginAndPassword(String login, String password, String confirmPassword) {
        boolean isValid = true;
        try {
            checkLogin(login);
            checkPassword(password, confirmPassword);
        } catch (WrongLoginException e) {
            System.out.println("Введён не допустимый логин: " + e.getMessage());
            isValid = false;
        } catch (WrongPasswordException e) {
            System.out.println("Введён не допустимый пароль: " + e.getMessage());
            isValid = false;
        }
        return isValid;
    }

    private static void checkLogin(String login) throws WrongLoginException {
        if (!login.matches(VALIDATE_PATTERN)) {
            throw new WrongLoginException("Логин должен содержать в себе только латинские буквы, цифры и знак подчеркивания");
        } else if (login.length() > 20) {
            throw new WrongLoginException("Логи должен быть равен или меньше 20 символов");
        }
    }

    private static void checkPassword(String password, String confirmPassword) throws WrongPasswordException {
        if (!password.matches(VALIDATE_PATTERN)) {
            throw new WrongPasswordException("Пароль должен содержать в себе только латинские буквы, цифры и знак подчеркивания");
        } else if (password.length() > 20) {
            throw new WrongPasswordException("Логи должен быть равен или меньше 20 символов");
        } else if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Пароли не совпадают");
        }
    }
}