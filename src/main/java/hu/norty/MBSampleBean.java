package hu.norty;


import hu.norty.projects.DBData;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

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