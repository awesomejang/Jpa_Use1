package jpabook.jpashop;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;

// Junit아 spring과 관련된 테스트하려구!
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberRepositoryTest {

	@Autowired MemberRepository memberRepository;
	
	@Test
	@Transactional
	@Rollback(false)
	public void testMember() {		
		
		// given
		Member member = new Member();
		member.setUsername("memberA");
		
		//when
		Long savedId = memberRepository.save(member);
		Member findMember = memberRepository.find(savedId);
		
		//then 
		Assertions.assertThat(findMember.getId()).isEqualTo(savedId);
		Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
		Assertions.assertThat(findMember).isEqualTo(member); 
		System.out.println("findMember == member: " + (findMember == member));
	}
	
}
