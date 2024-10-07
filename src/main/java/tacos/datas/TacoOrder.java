package tacos.datas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.CreditCardNumber;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "tacoorder")
@SequenceGenerator(name = "tacoorderseq")
public class TacoOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "tacoorderseq")
	@SequenceGenerator(name = "tacoorderseq", allocationSize = 1)
	private Long id;
	
	@Column(name = "placedat")
	private Date placedAt;
	
	@Column(name = "deliveryname")
	@NotBlank(message="Delivary name is required")
	private String deliveryName;
	
	@Column(name = "deliverystreet")
	@NotBlank(message="Street is required")
	private String deliveryStreet;
	
	@Column(name = "deliverycity")
	@NotBlank(message="City is required")
	private String deliveryCity;
	
	@Column(name = "deliverystate")
	@NotBlank(message="State is required")
	private String deliveryState;
	
	@Column(name = "deliveryzip")
	@NotBlank(message="Zip code is required")
	private String deliveryZip;
	
	@Column(name = "ccnumber")
	@CreditCardNumber(message="Not a valid credit card number")
	private String ccNumber;
	
	@Column(name = "ccexpiration")
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\\\/])([2-9][0-9])$", message="Must be formatted MM/YY")
	private String ccExpiration;
	
	@Column(name = "cccvv")
	@Digits(integer=3, fraction=0, message="Invalid CVV")
	private String ccCVV;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Taco> tacos = new ArrayList<>();
	public void addTaco(Taco taco) {
		tacos.add(taco);
	}
}
