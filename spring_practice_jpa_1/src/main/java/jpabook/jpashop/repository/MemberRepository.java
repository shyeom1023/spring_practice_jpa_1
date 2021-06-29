package jpabook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

//	@PersistenceContext //스프링 부트에서는 이것 대신에 @Autowired로 인젝션이 사용이 가능하다.
	private final EntityManager em;

	// 이것 대신에 final로 @RequiredArgsConstructor로 사용 가능
//	@Autowired
//	public MemberRepository(EntityManager em) {
//		super();
//		this.em = em;
//	}

	public void save(Member member) {
		em.persist(member);
	}

	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}

	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class).getResultList();
	}

	public List<Member> findByName(String name) {
		return em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name)
				.getResultList();
	}

}
