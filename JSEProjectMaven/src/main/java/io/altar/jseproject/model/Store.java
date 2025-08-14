package io.altar.jseproject.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

//public class Store extends MyEntity{
@Entity
@Table(name = "stores")
public class Store implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String location;

	@ElementCollection
	@CollectionTable(name = "store_users", joinColumns = @JoinColumn(name = "store_id"))
	@Column(name = "user_id")
	private Set<Long> userIds = new HashSet<>();

	@ElementCollection
	@CollectionTable(name = "store_shelves", joinColumns = @JoinColumn(name = "store_id"))
	@MapKeyColumn(name = "shelf_id")
	@Column(name = "quantity")
	private Map<Long, Integer> shelves = new HashMap<>();

	public Store() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Map<Long, Integer> getShelves() {
		return shelves;
	}

	public void setShelves(Map<Long, Integer> shelves) {
		this.shelves = shelves;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", name=" + name + ", location=" + location + ", userIds=" + userIds + ", shelves="
				+ shelves + "]";
	}

}
