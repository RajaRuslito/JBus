package RajaYonandroRuslitoJBusAF;

public class Review extends Serializable
{
    public String date;
    public String desc;
    
    public Review(int id, String date, String desc){
        super(id);
        this.date = date;
        this.desc = desc;
    }
    public String toString(){
        return " ID : " + id + "\n Date : " + date + "\n Description : " + desc;
    }
}
