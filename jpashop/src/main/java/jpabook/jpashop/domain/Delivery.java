package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

	@Id @GeneratedValue
	@Column(name = "delivery_id")
	private Long id;
	
	@OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY) // Order Entity의 필드에 있는 delivery 클래스의 의해 매핑됬다 선언 
	private Order order;
	
	@Embedded
	private Address address;
	
	@Enumerated(EnumType.STRING) // Enum타입 사용시 선언해줘야하는데 EnumType.ORDINAL 일 경우 값이 Enum에 선언된 필드 순서에 따라
	// 숫자로 들어가는데 REDAY = 0, COMP = 1 일경우 중간에 값이 들어가면 값이 밀리기때문에 망함 그래서 꼭 String을 사용해야한다.
	private DeliveryStatus status; // READY, COMP 
	
}
