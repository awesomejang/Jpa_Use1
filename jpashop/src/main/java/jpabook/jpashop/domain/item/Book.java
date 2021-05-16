package jpabook.jpashop.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("B") //default == class name 싱글테이블에서 구분자 지정 
@Getter @Setter
public class Book extends Item {
	
	private String author;
	private String isbn;

}
