package RajaYonandroRuslitoJBusAF;

public class Voucher
{
    public String name;
    private boolean used;
    public double minimum;
    public double cut;
    public int code;
    public Type type;
    
    public Voucher(String name, int code, Type type, double minimum, double cut){
        this.name = name;
        this.minimum = minimum;
        this.cut = cut;
        this.type = type;
        this.code = code;
    }
    
    public double apply(Price price){
        this.used = true;
        if(this.type == Type.DISCOUNT){
            if(cut >= 100){
                cut = 100;
            }    
            return price.price * (int)(100 - cut) / 100;
        } else{
            if(cut > price.price){
                return 0;
            }
            return price.price - cut;   
        }
    }
    public boolean isUsed(){
        return this.used;
    }
    public boolean canApply(Price price){
        if(price.price > this.minimum && !this.used){
            return true;
        } else { 
            return false; 
        }
    }
}