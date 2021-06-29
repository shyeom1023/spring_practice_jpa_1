package jpabook.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // final한 필드만 생성자로 만든다.
public class MemberService {

	// 필드 인젝션 수정하기 어려움
	// @Autowired
	private final MemberRepository memberRepository;

	// setter 인젝션 수정하기 쉬움
//	@Autowired
//	public void setMemberRepository(MemberRepository memberRepository) {
//		this.memberRepository = memberRepository;
//	}

	// @RequiredArgsConstructor로 대체
//	@Autowired 
//	public MemberService(MemberRepository memberRepository) {
//		super();
//		this.memberRepository = memberRepository;
//	}

	/**
	 * 회원가입
	 * 
	 * @param member
	 * @return
	 */
	@Transactional
	public Long join(Member member) {
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {

		List<Member> findMembers = memberRepository.findByName(member.getName());
		if (!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}

	}

	// 회원 전체 조회

	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	// 회원 단건 조회
	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}

}
