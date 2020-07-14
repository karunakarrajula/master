package resources;
//enum is special class in java which has collection of constants or  methods

public enum APIResources {	
	GetAllemployesAPI("/api/v1/employees"),
	CreateNewEmployeeAPI("/api/v1/create"),
	GetEmployeeAPI("api/v1/employee/"),
	UpdateEmployeeAPI("api/v1/update/"),
	DeleteEmployeeAPI("api/v1/delete/");
	
	private String resource;
	
	APIResources(String resource)
	{
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}
	

}
