package us.flower.dayary.repository.querydsl;


import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import us.flower.dayary.domain.Academy;

@Repository
public class AcademyRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;
    

    public AcademyRepositorySupport(JPAQueryFactory queryFactory) {
        super(Academy.class);
        this.queryFactory = queryFactory;
    }
//    public List<Academy> findByName(String name) {
//        return queryFactory
//                .selectFrom(academy)
//                .where(academy.name.eq(name))
//                .fetch();
//    }

} 