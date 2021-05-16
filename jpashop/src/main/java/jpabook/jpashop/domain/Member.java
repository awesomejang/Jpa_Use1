package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {
	
	@Id @GeneratedValue
	@Column(name = "member_id") // 따로 지정 안해주면 id로 올라감 
	private Long id;
	
	private String username;
	
	@Embedded // 내장타입을 포함했다. 한쪽만 있어도 무관함 
	private Address address;
	
	@OneToMany(mappedBy = "member") //(order테이블에 있는 member필드에 의해 매핑) 연관관계의 거울 order의 member필드에 의해 매핑됐을뿐
	// mappedBy 적으면 매핑하는애가 아니고 저 값에의해 매핑된 거울일뿐이야 선언 
	// 여기에 값을 변경해도 Order테이블의 FK값이 변경되지 않음 
	private List<Order> orders = new ArrayList<>();
	
	

}
