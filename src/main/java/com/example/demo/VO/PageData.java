package com.example.demo.VO;

import lombok.Data;

import java.util.List;


@Data
public class PageData<T> {

    List<T> list;

    Integer count;


    public PageData(List<T> list){
        this(list,list.size());
    }

    public PageData(List<T> list, Integer count){
        this.list = list;
        this.count = count;
    }

    public static <T> PageData<T> build(List<T> list){
        return build(list,list.size());
    }


    public static <T> PageData<T> build(List<T> list, Integer count){
        return new PageData<T>(list,count);
    }
}
