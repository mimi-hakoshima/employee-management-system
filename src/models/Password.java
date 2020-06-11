package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "password")
@NamedQueries({
    @NamedQuery(
            name = "getAllPassword",
            query = "SELECT p FROM Password AS p ORDER BY p.id DESC"
            ),
    @NamedQuery(
            name = "checkLoginCodeAndPassword",
            query = "SELECT p FROM Employee AS p WHERE e.delete_flg = 0 AND e.code = :code AND p.password = :pass"
            )

})

@Entity
public class Password {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "password_id", nullable = false)
    private String password_id;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

    public String getPassword_id(){
        return password_id;
    }

    public void setPassword_id(String password_id){
        this.password_id = password_id;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

}
