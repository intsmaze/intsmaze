package cn.intsmaze.project.model;

import java.io.Serializable;

import javax.persistence.*;

public class Person implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String seg;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return seg
     */
    public String getSeg() {
        return seg;
    }

    /**
     * @param seg
     */
    public void setSeg(String seg) {
        this.seg = seg;
    }
}