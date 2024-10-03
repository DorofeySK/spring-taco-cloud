package tacos.datas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Table("tacoorder")
public class TacoOrder implements Serializable, Persistable<Long> {
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	@Column("placedat")
	private Date placedAt;
	
	@Column("deliveryname")
	@NotBlank(message="Delivary name is required")
	private String deliveryName;
	@Column("deliverystreet")
	@NotBlank(message="Street is required")
	private String deliveryStreet;
	@Column("deliverycity")
	@NotBlank(message="City is required")
	private String deliveryCity;
	@Column("deliverystate")
	@NotBlank(message="State is required")
	private String deliveryState;
	@Column("deliveryzip")
	@NotBlank(message="Zip code is required")
	private String deliveryZip;
	@Column("ccnumber")
	@CreditCardNumber(message="Not a valid credit card number")
	private String ccNumber;
	@Column("ccexpiration")
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\\\/])([2-9][0-9])$", message="Must be formatted MM/YY")
	private String ccExpiration;
	@Column("cccvv")
	@Digits(integer=3, fraction=0, message="Invalid CVV")
	private String ccCVV;
	
	@MappedCollection(idColumn = "tacoorderid")
	private List<Taco> tacos = new ArrayList<>();
	public void addTaco(Taco taco) {
		tacos.add(taco);
	}
	
	@Override
	public boolean isNew() {
		return true;
	}
}
