package hu.norty.projects;


import hu.norty.Project;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

@ManagedBean(name = "projectList", eager = true)
@SessionScoped
public class MBProjectList extends MBAbstractList<Project> implements Serializable {

    private static final long serialVersionUID = 1L;

    public MBProjectList() {
        //System.out.println("created");
    }

    @Override
    protected Project createListItem(ResultSet rs) throws SQLException {
        Project r = new Project();
        r.setId(rs.getInt(1));
        r.setName(rs.getString(2));
        return r;
    }

    @Override
    protected String tableName() {
        return "projects";
    }

    @Override
    public String getDetailsFields() {
        return "project_id,name";
    }
    @Override
    public String getListFields() {
        return "project_id,name";
    }
}