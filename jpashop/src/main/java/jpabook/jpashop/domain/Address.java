package jpabook.jpashop.domain;

import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable // jpa 내장타입 선언 
@Getter
public class Address {

	private String city;
	private String street;
	private String zipcode;
	
}
