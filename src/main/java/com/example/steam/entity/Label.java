package com.example.steam.entity;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-25
 * @time: 10:55
 */
@Component
public class Label {

    private Long id;

    private String name;

    private int hotNum;

    public Label(){}

    public Label(Long id,String name,Integer hotNum){
        this.id=id;
        this.name=name;
        this.hotNum=hotNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHotNum() {
        return hotNum;
    }

    public void setHotNum(int hotNum) {
        this.hotNum = hotNum;
    }

    @Override
    public String toString(){
        return "[name="+name+",hot="+hotNum+"]";
    }
}
