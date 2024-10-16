package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		DepartmentDao departmentDao =  DaoFactory.createDepartmentDao();
		
		Department department = departmentDao.findById(2);
		System.out.println(department);
		
		System.out.println();
		
		System.out.println("findAll");
		List <Department> list = departmentDao.findAll();
		list.forEach(System.out::println);
		
		System.out.println();
		
		System.out.println("Insert");
		Department newDp = new Department(null, "Estoquista");
		departmentDao.insert(newDp);
		list.forEach(System.out::println);
	

}
}