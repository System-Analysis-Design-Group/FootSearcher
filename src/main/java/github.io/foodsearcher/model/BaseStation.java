package top.yaoyongdou.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by young on 18-3-17.
 */
@Entity
public class BaseStation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id; // 编号

    private String center;
    private Integer radius;

    @Column(unique = true)
    private String nodename;

    public BaseStation() {
    }

    public BaseStation(Long id, String center, Integer radius, String nodename) {
        this.id = id;
        this.center = center;
        this.radius = radius;
        this.nodename = nodename;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }
}
