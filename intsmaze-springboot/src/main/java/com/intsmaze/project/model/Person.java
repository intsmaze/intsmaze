package com.intsmaze.project.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class Person  implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
  //对应数据库的字段名称,不加默认就是字段名称
    @Column(name = "seg")
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