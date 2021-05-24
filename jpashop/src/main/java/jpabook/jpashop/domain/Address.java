package jpabook.jpashop.domain;

import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable // jpa 내장타입 선언 
@Getter
public class Address {

	private String city;
	private String street;
	private String zipcode;
	
	/**
	 * 값의 변경은 유지보수성의 저하를 발생시키기 때문에 setter를 생성하지않는다.
	 * 오로지 생성자를 통해서만 세팅이 가능하도록 설계한다. 
	 * 다만 JPA에서 기본생성자를 통한 활용로직이 있기때문에 기본생성자를 만들어야하는데
	 * JPA에서는 proteced까지 허용한다. (public, protected)로 생성해야햔다. 
	 */
	protected Address() {
		
	}
	public Address(String city, String street, String zipcode) {
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	
	
	
}
