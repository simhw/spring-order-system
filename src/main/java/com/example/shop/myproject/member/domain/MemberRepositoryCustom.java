package com.example.shop.myproject.member.domain;

import com.example.shop.myproject.member.query.MemberDetail;

public interface MemberRepositoryCustom {
    MemberDetail findMemberDetail(Long id);

}
