package hu.norty;

/**
 * Created by Norbert_Farkas on 3/19/14.
 */
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "ajaxData", eager = true)
@SessionScoped
public class AjaxData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getWelcomeMessage(){
        return (name == null || name.trim().length() == 0) ? "" : "Hello " + name;
    }
}