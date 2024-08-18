package com.example.shop.myproject.member.infra;

import com.example.shop.myproject.member.domain.MemberRepositoryCustom;
import com.example.shop.myproject.member.query.MemberDetail;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final EntityManager em;

    public MemberDetail findMemberDetail(Long id) {
        /**
         String query = "select new com.example.shop.myproject.member.query.MemberDetail" +
         "(m.id, m.profile.nickname, m.profile.introduce, m.profile.image) " +
         "from Member m " +
         "left join m.followers f1 " +
         "left join m.followees f2 " +
         "left join m.posts p " +
         "where m.id = :id " +
         "group by m.id";

         return em.createQuery(query, MemberDetail.class)
         .setParameter("id", id)
         .getSingleResult();
         */
        return null;
    }


}
