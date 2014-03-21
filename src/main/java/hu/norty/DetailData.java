package hu.norty;

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
 * Created by Norbert_Farkas on 3/19/14.
 */
@ManagedBean(name = "detailData", eager = true)
@RequestScoped
public class DetailData extends DBData implements Serializable {

    private static final long serialVersionUID = 1L;
    private HashMap<String, Object> detail;

    //this managed property will read value from request parameter pageId
    @ManagedProperty(value = "#{param.entityId}")
    private Long entityId;

    @ManagedProperty(value = "#{param.fields}")
    private String fields;

    public String[] getFieldsArray() {
        return fieldsArray;
    }

    private String fieldsArray[];

    public HashMap<String, Object> getDetail() {
        System.out.println(entityId);
        if (detail == null) {
            detail = readDetail();
        }
        return detail;
    }

    private HashMap<String, Object> readDetail() {
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = getConnection();
        String stm = "Select "+fields+" from projects where project_id = ? ";
        System.out.println(stm);
        HashMap<String, Object> detail = null;
        try {
            pst = con.prepareStatement(stm);
            pst.setLong(1, entityId);
            pst.execute();
            rs = pst.getResultSet();

            if (rs.next()) {
                detail = new HashMap<String, Object>();
                for (int i = 0; i < fieldsArray.length; i++) {
                    detail.put(fieldsArray[i].trim(), rs.getObject(i + 1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detail;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public void setFields(String fields) {
        this.fields = fields;
        this.fieldsArray = fields.split(",");
    }

    public String getFields() {
        return this.fields;
    }
}