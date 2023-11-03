package RajaYonandroRuslitoJBusAF;

public class Account extends Serializable
{
    public String email;
    public String name;
    public String password;
    
    public Account(int id, String name, String email, String password){
        super();
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public String toString(){
        return "\n Account ID : " + id + "\n Name : " + name + "\n Email : " + email + "\n Password : " + password;
    }
    public boolean read(String obj){
        return false;        
    }
    public Object write(){
        return null;
    }
}
