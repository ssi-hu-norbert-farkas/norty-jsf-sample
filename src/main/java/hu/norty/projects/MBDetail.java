package hu.norty.projects;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * 
 */
@ManagedBean(name = "detailData", eager = true)
@RequestScoped
public class MBDetail extends DBData implements Serializable {

    private static final long serialVersionUID = 1L;
    private HashMap<String, Object> detail;

    @ManagedProperty("#{projectList}")
    private MBAbstractList list;
    /*
        @ManagedProperty(value = "#{param.fields}")
        private String fields;
    */
    @ManagedProperty(value = "#{param.entityId}")
    private Long entityId;


    transient
    private Long detailId;

    public String[] getFieldsArray() {
        return fieldsArray;
    }

    private String fieldsArray[];

    public HashMap<String, Object> getDetail() {
        Long id = list.getSelected();
        if (detail == null || detailId == null || !detailId.equals(id)) {
            detail = readDetail(id);
        }
        return detail;
    }

    private HashMap<String, Object> readDetail(Long id) {
        if (id == null) {
            return new HashMap<String, Object>();
        }
        HashMap<String, Object> detail = null;
        Connection con = null;
        try {
            con = getConnection();
            String stm = "Select " + list.getDetailsFields() + " from projects where project_id = ? ";
            System.out.println(stm);

            PreparedStatement pst = con.prepareStatement(stm);
            pst.setLong(1, id);
            pst.execute();
            ResultSet rs = pst.getResultSet();

            if (rs.next()) {
                detail = new HashMap<String, Object>();
                for (int i = 0; i < fieldsArray.length; i++) {
                    detail.put(fieldsArray[i].trim(), rs.getObject(i + 1));
                }
            }
            detailId = id;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return detail;
    }

    /*
        public void setFields(String fields) {
            this.fields = fields;
            this.fieldsArray = fields.split(",");
        }

        public String getFields() {
            return this.fields;
        }
    */
    public void setList(MBAbstractList list) {
        if (list != null && entityId != null) {
            list.setSelected(entityId);
        }
        if (list != null) {
            fieldsArray = list.getDetailsFields().split(",");
        }
        this.list = list;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        if (list != null && entityId != null) {
            list.setSelected(entityId);
        }
        this.entityId = entityId;
    }
}