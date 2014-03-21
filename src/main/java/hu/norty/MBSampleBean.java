package hu.norty;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "sampleBean", eager = true)
@SessionScoped
public class MBSampleBean extends DBData implements Serializable {

    private static final long serialVersionUID = 1L;

    private int counter = 1;

    private String text;


    public MBSampleBean() {
        System.out.println("created");
    }

    public void clickedOne() {
        System.out.println("clicked");
        this.counter ++;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}