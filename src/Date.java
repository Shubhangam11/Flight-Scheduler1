
public class Date {
    private static Date date;
    
    public Date (Date date){
        this.date = date;
    }
    
    public static Date getDate(){
        return date;
    }
    
    public void setDate(Date date){
        this.date = date;
    }
}
