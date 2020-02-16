package com.example.dianshangxiangmu.db;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

@Entity
public class CollectVo {

    @Id(autoincrement = true)
    public Long id;

    @NotNull
    public String title;

    @NotNull
    public String date;

    @Generated(hash = 1302214208)
    public CollectVo(Long id, @NotNull String title, @NotNull String date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    @Generated(hash = 1198231914)
    public CollectVo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
