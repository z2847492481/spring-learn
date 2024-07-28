package com.zhq.learn.aop;

import org.springframework.stereotype.Service;

/**
 * @author zhq123
 * @date 2024-07-22
 */
@Service
public class MyService {

    public static void save(){
        System.out.println("save");
    }

    public void update(){
        System.out.println("update");
    }
}
