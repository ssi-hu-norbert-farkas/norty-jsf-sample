package hu.norty.projects;


import hu.norty.DBData;
import hu.norty.Project;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "projectList", eager = true)
@SessionScoped
public class MBProjectList extends DBData implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long selected;


    public MBProjectList() {
        //System.out.println("created");
    }

    public void setSelected(Long id) {
        selected = id;
        //System.out.println("clicked :"+selected);
    }

    public List<Project> getProjects() {
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = getConnection();
        String stm = "Select top 10 * from projects";
        List<Project> records = new ArrayList<Project>();
        try {
            pst = con.prepareStatement(stm);
            pst.execute();
            rs = pst.getResultSet();

            while (rs.next()) {
                Project author = new Project();
                author.setId(rs.getInt(1));
                author.setName(rs.getString(2));
                records.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    public String getFields() {
        return "project_id,name";
    }

    public Long getSelected() {
        return selected;
    }
}