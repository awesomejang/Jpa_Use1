package jpabook.jpashop.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
	
	@Id @GeneratedValue
	@Column(name = "order_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY) // xtoOne default 가 EAGER 그래서 수정해야한다.
	@JoinColumn(name = "member_id") //매핑을 뭐로할것인지? FK선언 
	private Member member;
	
	/**
	 * cascade = CascadeType.ALL cascade옵션은 Order가 insert나 delete될때 같이 처리가 되도록 하는건데 
	 * 테이블 끼리의 관계를 보고 넣을지 결정하는듯하다.
	 * ERD를 보면 Member하고도 관계가 있기는하나 Order가 I,D될때 Member처리할게 뭐가있겠나 
	 * 필요에 따라 넣는 옵션이라고 생각하면될듯하다. 
	 */
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "delivery_id")
	private Delivery delivery;
	
	//order_date
	private LocalDateTime orderDate; // 자바 1.8부터 나온 datetime 주문시간
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status; // 주문상태 [ORDER, CANCEL]
	
	
	
	//=연관관계 메서드==// 양방향일때 두개의 테이블에 모두 값을 동기화하는
	// DB에 저장할떄는 FK(joincolumn)만 있으면 되지만 로직을 처리할때 필요한 데이터를 처리하기 위한 동기화
	public void setMember(Member member) { 
		this.member = member;
		member.getOrders().add(this);
	}
	
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}
	
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
		delivery.setOrder(this);
	}
	
	
}	
