package hello.hellospring.repository;


import hello.hellospring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class JdbcMemberRepository implements MemberRepository{
    private final EntityManager em;

    public JdbcMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
      em.persist(member);
      return member;
    }

    @Override
    public Optional<Member> findById(long id) {
       Member member = em.find(Member.class, id);
       return Optional.ofNullable(member);
    }



    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();

    }




    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em
                .createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }





}