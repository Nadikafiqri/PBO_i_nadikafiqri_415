package main.modul6.Organization;
import java.util.ArrayList;

//import com.example.praktikum.Model.Item;

public abstract class User {
    
    private String Username;
    private String Password;
    protected static ArrayList<User> Userlist = new ArrayList<>();

    static {
        User.Userlist.add(new Mahasiswa("sadaika","01"));
        User.Userlist.add(new Mahasiswa("sodiq","02"));
        User.Userlist.add(new Mahasiswa("bud","03"));
        User.Userlist.add(new Admin("admin1","10"));
        Userlist.add(new Admin("admin2","20"));
    }

    public User(String username,String password) {
        Username = username;
        Password = password;
    }
    public User(){

    }

    public String getUsername() {
        return Username;
    }
    public void setUsername(String username) {
        Username = username;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }

    public static ArrayList<User> getUserlist() {
        return Userlist;
    }

    public static void setUserlist(ArrayList<User> userlist) {
        Userlist = userlist;
    }

    public abstract boolean inputCheck(String Username, String Password);
    public abstract void DisplayAppMenu();

    @Override
    public String toString(){
        return 
        "========Data User==========\n"
        +"Akses     : " + this.getClass().getSimpleName() + "\n"
        +"Username  : " + this.Username + "\n"
        +"Password  : " + this.Password + "\n";

    }
}
