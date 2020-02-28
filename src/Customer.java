
public class Customer {
    private static String name;
    
    public Customer (String name){
        this.name = name;
    }
    
    public static String getCustomerName(){
        return name;
    }
    
    public void setCustomerName(String name){
        this.name = name;
    }
}
