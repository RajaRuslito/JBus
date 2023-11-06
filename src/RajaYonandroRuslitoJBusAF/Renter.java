package RajaYonandroRuslitoJBusAF;

import java.util.regex.Pattern;

public class Renter extends Serializable
{
    public String address;
    public String companyName;
    public String phoneNumber;
    private final String REGEX_NAME = "^[A-Z][a-zA-Z0-9_]{3,19}$";
    private final String REGEX_PHONE = "^[0-9]{9,12}$";
    
    public Renter(String companyName){
        super();
        this.address = " ";
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
    }
    public Renter(String companyName, String phoneNumber, String address){
        super();
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
    }
    public Renter(String companyName, String phoneNumber){
        super();
        this.address = " ";
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
    }
    /*public Renter(int id, String companyName, String address){
        super();
        this.address = address;
        this.phoneNumber = 0;
        this.companyName = companyName;
    }*/

    public boolean validate(){
        return companyName.matches(REGEX_NAME) && phoneNumber.matches(REGEX_PHONE);
    }

}
