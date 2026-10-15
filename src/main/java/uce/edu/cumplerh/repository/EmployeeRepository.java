package uce.edu.cumplerh.repository;
import uce.edu.cumplerh.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface EmployeeRepository extends JpaRepository<Employee,String> {
    List<Employee> findByFirstNamesContainingIgnoreCaseOrLastNamesContainingIgnoreCaseOrNationalIdContainingIgnoreCaseOrderByLastNamesAsc(String firstNames,String lastNames,String nationalId);
    List<Employee> findAllByOrderByLastNamesAscFirstNamesAsc();
}
