package RajaYonandroRuslitoJBusAF;

import java.util.regex.Pattern;

public class Renter extends Serializable
{
    public String address;
    public String companyName;
    public int phoneNumber;
    private final String REGEX_NAME = "^[A-Z][a-zA-Z0-9_]{3,19}$";
    private final String REGEX_PHONE = "^[0-9]{9,12}$";
    
    public Renter(int id, String companyName){
        super();
        this.address = " ";
        this.phoneNumber = 0;
        this.companyName = companyName;
    }
    public Renter(int id, String companyName, int phoneNumber, String address){
        super();
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
    }
    public Renter(int id, String companyName, int phoneNumber){
        super();
        this.address = " ";
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
    }
    public Renter(int id, String companyName, String address){
        super();
        this.address = address;
        this.phoneNumber = 0;
        this.companyName = companyName;
    }

    public boolean validate(){
        boolean name = Pattern.matches(REGEX_NAME, String.valueOf(companyName));
        boolean phone = Pattern.matches(REGEX_PHONE, String.valueOf(phoneNumber));

        if(!name){
            System.out.println("Not Found!");
        }
        if(!phone){
            System.out.println("Not Found!");
        }

        return name && phone;
    }

}
