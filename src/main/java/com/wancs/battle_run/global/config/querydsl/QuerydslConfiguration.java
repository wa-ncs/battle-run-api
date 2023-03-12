package com.wancs.battle_run.global.config.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// == 스프링 부트 3.0 이상 ==
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

// == 스프링 부트 3.0 이하 ==
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;



@Configuration
public class QuerydslConfiguration {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() { // jpaQueryFactory 빈을 Repository에서 사용
        return new JPAQueryFactory(entityManager);
    }
}
