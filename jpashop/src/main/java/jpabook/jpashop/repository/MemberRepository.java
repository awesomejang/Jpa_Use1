package jpabook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import jpabook.jpashop.domain.Member;

@Repository // spring bean 등록 
public class MemberRepository {

	@PersistenceContext // EntityManager 주입(spring boot)
	private EntityManager em; 
	
	public void save(Member member) {
		em.persist(member); // 영속성 Context에 member엔티티. 이후 트랜잭션 commit때 DB반영 
	}
	
	public Member findOne(Long id) {
		//Member member =  em.find(Member.class, id);
		return em.find(Member.class, id);
	}
	
	public List<Member> findAll() { // JPQL 테이블에 대한이 아닌 엔티티에 대한 쿼리, 두 번째 파라미터는 리턴타입
		//List<Member> list =  em.createQuery("select m from Member m", Member.class).getResultList();
		return em.createQuery("select m from Member m", Member.class)
				.getResultList();
		
	}
	
	public List<Member> findByName(String name) {
		return em.createQuery("select m from Member m where m.name = :name", Member.class) // :param = 바인딩 
				.setParameter("name", name)
				.getResultList();
	}
	
}
