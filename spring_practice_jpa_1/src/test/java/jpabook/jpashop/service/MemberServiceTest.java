package jpabook.jpashop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;

@SpringBootTest
@Transactional
public class MemberServiceTest {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Test
	void 회원가입() throws Exception {

		//given
		Member member = new Member();
		member.setName("kim");

		//when
		Long savedId = memberService.join(member);

		//then
		assertEquals(member, memberRepository.findOne(savedId));
		
	}
	
	@Test()
	void 중복_회원_예외() throws Exception {

		//given
		Member member1 = new Member();
		member1.setName("kim");
		
		Member member2 = new Member();
		member2.setName("kim");
		

		//when
		memberService.join(member1);
		try {
			memberService.join(member2); //예외가 발생해야한다.
			
		}catch (IllegalStateException e) {
			// TODO: handle exception
			return;
		}

		//then
		fail("예외가 발생해야 한다.");

	}

}
