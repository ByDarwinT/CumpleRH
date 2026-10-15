package uce.edu.cumplerh.service;
import uce.edu.cumplerh.model.Employee;
import uce.edu.cumplerh.repository.EmployeeRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class EmployeeService {
    private final EmployeeRepository repository;
    public static final List<String> DEPARTMENTS=List.of("Administración","Recursos Humanos","Contabilidad","Ventas","Marketing","Tecnología","Logística");
    public static final List<String> GENDERS=List.of("Masculino","Femenino");
    public EmployeeService(EmployeeRepository repository){this.repository=repository;}
    public List<Employee> search(String query){
        if(query==null||query.isBlank())return repository.findAllByOrderByLastNamesAscFirstNamesAsc();
        String q=query.trim();return repository.findByFirstNamesContainingIgnoreCaseOrLastNamesContainingIgnoreCaseOrNationalIdContainingIgnoreCaseOrderByLastNamesAsc(q,q,q);
    }
    public List<Employee> findAll(){return repository.findAllByOrderByLastNamesAscFirstNamesAsc();}
    public Employee findByNationalId(String nationalId){return repository.findById(nationalId).orElseThrow(()->new IllegalArgumentException("Employee not found"));}
    public boolean exists(String nationalId){return repository.existsById(nationalId);}
    public Employee save(Employee employee){return repository.save(employee);}
    public void delete(String nationalId){repository.deleteById(nationalId);}
    public boolean isValid(Employee e){return e.getHireDate()!=null&&!e.getHireDate().isAfter(LocalDate.now())&&
        e.getBirthDate()!=null&&!e.getHireDate().isBefore(e.getBirthDate())&&
        DEPARTMENTS.contains(e.getDepartment())&&GENDERS.contains(e.getGender());}
}
