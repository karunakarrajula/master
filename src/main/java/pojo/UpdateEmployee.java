package pojo;

import java.util.List;

public class UpdateEmployee {



	private String name;
	private int salary;
	private int age;

	public UpdateEmployee(){
		this.name= "default";
		this.age=0;
		this.salary=0;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}





}
