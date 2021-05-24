package jpabook.jpashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jpabook.jpashop.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	// 회원 가입 
	
	// 회원 전체 조회
}
