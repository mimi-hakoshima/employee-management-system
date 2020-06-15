package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "belongsNum")
@NamedQueries({
    @NamedQuery(
            name = "getAllBelongsNum",
            query = "SELECT e FROM Employee AS e ORDER BY e.id DESC"
            )
})

@Entity
public class BelongsNum {
    @Id

    @Column(name = "belongs_id", nullable = false, unique = true)
    private String belongs_id;

    @Column(name = "belongs_name", nullable = false)
    private String belongs_name;


    public String getBelongs_id(){
        return belongs_id;
    }

    public void setBelongs_id(String belongs_id){
        this.belongs_id = belongs_id;
    }

    public String getBelongs_name(){
        return belongs_name;
    }

    public void setBelongs_name(String belongs_name){
        this.belongs_name = belongs_name;
    }

}


