package com.example.servingwebcontent.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)        // создание строки с данными из mysql

    private Integer id;
    private String bsch;
    private String vhodact;
    private String vhodpas;
    private String obordeb;
    private String oborkred;
    private String iskhact;
    private String iskhpas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBsch() {
        return bsch;
    }

    public void setBsch(String bsch) {
        this.bsch = bsch;
    }

    public String getVhodact() {
        return vhodact;
    }

    public void setVhodact(String vhodact) {
        this.vhodact = vhodact;
    }

    public String getVhodpas() {
        return vhodpas;
    }

    public void setVhodpas(String vhodpas) {
        this.vhodpas = vhodpas;
    }

    public String getObordeb() {
        return obordeb;
    }

    public void setObordeb(String obordeb) {
        this.obordeb = obordeb;
    }

    public String getOborkred() {
        return oborkred;
    }

    public void setOborkred(String oborkred) {
        this.oborkred = oborkred;
    }

    public String getIskhact() {
        return iskhact;
    }

    public void setIskhact(String iskhact) {
        this.iskhact = iskhact;
    }

    public String getIskhpas() {
        return iskhpas;
    }

    public void setIskhpas(String iskhpas) {
        this.iskhpas = iskhpas;
    }
}
