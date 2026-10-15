package uce.edu.cumplerh.config;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uce.edu.cumplerh.model.Employee;
import uce.edu.cumplerh.repository.EmployeeRepository;

@Configuration
public class DataLoader {
    private static final int SAMPLE_COUNT = 220;
    private static final String[] FIRST_NAMES = {
        "María", "Carlos", "Ana", "Luis", "Sofía", "Diego", "Valentina", "Mateo", "Camila", "José",
        "Lucía", "Daniel", "Gabriela", "Jorge", "Paula", "Andrés", "Elena", "Miguel", "Daniela", "Sebastián",
        "Carolina", "David", "Fernanda", "Gabriel", "Natalia", "Pedro", "Isabel", "Ricardo", "Andrea", "Juan"
    };
    private static final String[] LAST_NAMES = {
        "López", "Pérez", "Torres", "Gómez", "Ruiz", "Vega", "Cárdenas", "Morales", "Castro", "Ramírez",
        "Flores", "Ortiz", "Vargas", "Mendoza", "Guerrero", "Salazar", "Reyes", "Paredes", "Romero", "Cruz"
    };
    private static final String[] DEPARTMENTS = {
        "Administración", "Recursos Humanos", "Contabilidad", "Ventas", "Marketing", "Tecnología", "Logística"
    };

    @Bean
    CommandLineRunner loadSampleEmployees(EmployeeRepository repository) {
        return args -> {
            // Preserve existing data and top up small databases from the earlier version.
            // Once at least 200 employees exist, startup never restores individually deleted rows.
            if (repository.count() >= 200) return;
            List<Employee> newEmployees = new ArrayList<>();
            for (int index = 0; index < SAMPLE_COUNT; index++) {
                String nationalId = String.format("09%08d", index + 1);
                if (repository.existsById(nationalId)) continue;
                String firstName = FIRST_NAMES[index % FIRST_NAMES.length];
                String lastName = LAST_NAMES[(index * 7 + index / 20) % LAST_NAMES.length];
                int year = 1975 + (index * 11) % 30;
                int month = 1 + (index * 7) % 12;
                int day = 1 + (index * 13) % 28;
                LocalDate birthDate = LocalDate.of(year, month, day);
                LocalDate hireDate = LocalDate.of(2017 + index % 9, 1 + index % 12, 1 + index % 28);
                String department = DEPARTMENTS[index % DEPARTMENTS.length];
                String gender = index % 2 == 0 ? "Femenino" : "Masculino";
                newEmployees.add(new Employee(nationalId, firstName, lastName, birthDate, hireDate, department, gender));
            }
            repository.saveAll(newEmployees);
        };
    }
}
