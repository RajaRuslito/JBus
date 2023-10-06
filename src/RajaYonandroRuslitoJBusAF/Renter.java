package RajaYonandroRuslitoJBusAF;

public class Renter extends Serializable
{
    public String address;
    public String companyName;
    public int phoneNumber;
    
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
}
