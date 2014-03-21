package hu.norty.projects;


import hu.norty.Project;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

abstract public class MBAbstractList<T> extends DBData implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long selected;

    public MBAbstractList() {
        //System.out.println("created");
    }

    public void setSelected(Long id) {
        selected = id;
        //System.out.println("clicked :"+selected);
    }

    public List<T> getList() {
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = getConnection();
        String stm = "Select top 10 "+getListFields()+" from "+tableName();
        List<T> records = new ArrayList<T>();
        try {
            pst = con.prepareStatement(stm);
            pst.execute();
            rs = pst.getResultSet();

            while (rs.next()) {
                T i = createListItem(rs);
                records.add(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    protected abstract T createListItem(ResultSet rs) throws SQLException;

    protected abstract String tableName();

    public abstract String getListFields();
    public abstract String getDetailsFields();

    public Long getSelected() {
        return selected;
    }
}