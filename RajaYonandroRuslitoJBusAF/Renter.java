package RajaYonandroRuslitoJBusAF;

public class Renter extends Serializable
{
    String address;
    String companyName;
    int phoneNumber;
    
    public Renter(int id, String companyName){
        super(id);
    }
    public Renter(int id, String companyName, int phoneNumber, String address){
        super(id);
        this.address = " ";
        this.phoneNumber = 0;
    }
    public Renter(int id, String companyName, int phoneNumber){
        super(id);
        this.phoneNumber = 0;
    }
    public Renter(int id, String companyName, String address){
        super(id);
        this.address = " ";
    }
}
