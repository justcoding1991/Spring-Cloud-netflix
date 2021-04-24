package com.springcloud.learn.example.userserviceprovider.ProfileDemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProfileMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("dev");
        context.register(ProfileConfiguration.class);
        context.refresh();
        System.out.println(context.getBean(ProfileService.class));

    }

}
