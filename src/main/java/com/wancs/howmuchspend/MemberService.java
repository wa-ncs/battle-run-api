package com.wancs.howmuchspend;

import java.util.List;

public interface MemberService {

    Member find(Long id);
    List<Member> findAll();

    Long save(MemberDTO member);

    Member update(Long id, MemberDTO member);

    void delete(Long id);
}
