package RajaYonandroRuslitoJBusAF;
import java.util.regex.Pattern;


public class Account extends Serializable
{
    public String email;
    public String name;
    public String password;
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z]+\\\\.(com)$";
    public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\\\d)[A-Za-z\\\\d]{8,}$";
    
    public Account(String name, String email, String password){
        super();
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public String toString(){
        return " Name : " + name + "\n Email : " + email + "\n Password : " + password + "\n";
    }
    public boolean read(String obj){
        return false;        
    }
    public Object write(){
        return null;
    }
    public boolean validate() {
        boolean isEmailValid = Pattern.matches(REGEX_EMAIL, email);
        boolean isPasswordValid = Pattern.matches(REGEX_PASSWORD, password);

        return isEmailValid && isPasswordValid;
    }
}


