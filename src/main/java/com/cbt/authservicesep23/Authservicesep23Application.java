package com.cbt.authservicesep23;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Authservicesep23Application
{
    public static void main(String[] args)
    {
        new SpringApplicationBuilder()
                .profiles("dev")
                .sources(Authservicesep23Application.class)
                .run(args);
    }

}
