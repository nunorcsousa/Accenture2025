package io.altar.jseproject.model;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product extends MyEntity {
    private String name;
    private double price;
    private int discount;
    private int iva;
    private double pvp;
    private int quantity;
	private Set<Long> shelfIds = new HashSet<>(); 
    
    public Product() {};

    public Product(String name, double price, int discount, int iva, int quantity, Set<Long> shelfIds ) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.iva = iva;
        this.pvp = calculatePVP();
        this.quantity = quantity;
        this.shelfIds = shelfIds;
    }

    private double calculatePVP() {
    	double discountedPrice = price * (1 - discount / 100.0);
        return discountedPrice * (1 + iva / 100.0);
    }

    public void updatePVP() {
        this.pvp = calculatePVP();
    }

    public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getIva() {
		switch (iva) {
		case 6:
		case 11:
		case 23:
			break;
		default:
			throw new NumberFormatException();
		}
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public double getPvp() {
		return pvp;
	}

	public void setPvp(double pvp) {
		this.pvp = pvp;
	}

	public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public Set<Long> getShelfIds() { 
    	return shelfIds; 
    }
    
    public void addShelfId(Long shelfId) { 
    	this.shelfIds.add(shelfId); 
    }
    
    public void removeShelfId(Long shelfId) { 
    	this.shelfIds.remove(shelfId); 
    }
    
	public void setShelfIds(Set<Long> shelfIds) {
		this.shelfIds = shelfIds;
	}

	@Override
	public String toString() {
		return "Produto [name=" + name + ", Preço=" + price + ", Desconto=" + discount + ", IVA=" + iva + ", PVP=" + pvp
				+ ", Quantidade=" + quantity + ", Ids de Prateleiras=" + shelfIds + "]";
	}
}
