package koroler.cascade.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cascade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name = "person")
@NoArgsConstructor
@ToString
public class Person {
	@Id
	@Column (name = "personid")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private Integer person_id;
	
	@Column (name = "full_name")
	@Getter
	@Setter
	private String full_name;
	
	@Column (name = "age")
	@Getter
	@Setter
	private Integer age;
	
	//@OneToMany(mappedBy = "customer")
	// @Cascade (org.hibernate.annotations.CascadeType.SAVE_UPDATE) - с использованием метода save
	@OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
	@Getter
	private List <Product> products;
	
	public Person(String full_name, Integer age)
	{
		products = new ArrayList<>();
		this.full_name = full_name;
		this.age = age;
	}
	
	public void addProduct(Product product)
	{
		products.add(product); 
		product.setCustomer(this); // обновление кэша
	}
}
