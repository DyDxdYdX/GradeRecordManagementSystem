import java.util.ArrayList;

public class student{
    String name;
    String id;
    int sem;
    ArrayList<Double> gpa = new ArrayList<>();

    public student(String name, String id, int sem) {
        this.name = name;
        this.id = id;
        this.sem = sem;
    }
    public void initialize(){
        gpa.removeAll(gpa);
    }
    public void setGpa(double a){
        gpa.add(a);
    }
    public String getName(){
        return this.name;
    }
    public String getId(){
        return this.id;
    }
    public int getSem(){
        return this.sem;
    }
    public ArrayList<Double> getGpa(){
        return this.gpa;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setSem(int sem){
        this.sem = sem;
    }
}
