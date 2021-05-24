package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Category {

	@Id @GeneratedValue
	@Column(name = "category_id")
	private Long id;
	
	private String name;
	
	@ManyToMany
	@JoinTable(name = "category_item",
			joinColumns = @JoinColumn(name = "category_id"),
			inverseJoinColumns = @JoinColumn(name = "item_id")) /** 다대다 관계의 중간테이블간에 맵핑을위해 사용 -- 실무에서는 다대다 관계를 거의 사용하지 않기때문에 중요도낮음 */
	private List<Item> items = new ArrayList<>();
	
	/**
	 * 카테고리 계층구조
	 * 나의 부모 카테고리  
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Category parent;
	
	/**
	 * 자식 카테고리 
	 */
	@OneToMany(mappedBy = "parent")
	private List<Category> child = new ArrayList<>();
	
	//==연관관계 메서드==//
	public void addChildCategory(Category child) {
		this.child.add(child);
		child.setParent(this);
	}
}
