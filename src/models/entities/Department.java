package models.entities;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;

	
	
	//construtor
	public Department() {
	}
	
	public Department(
			Integer id, String name) {
		this.id = id;
		this.name = name;
	
	}


	//getters e setters
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	//HashCode e equals
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	//Método toString
	@Override
	public String toString() {
		return "Department [name=" + name + ", id=" + id + "]";
	}
	
	
	
	
}
