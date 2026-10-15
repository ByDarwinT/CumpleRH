package uce.edu.cumplerh.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name="empleados")
public class Employee {
    @Id @NotBlank(message="National ID is required")
    @Pattern(regexp="[0-9]{10}", message="National ID must contain exactly 10 digits")
    @Column(name="cedula",length=10, nullable=false) private String nationalId;
    @NotBlank(message="Enter first names") @Size(max=100)
    @Column(name="nombres",nullable=false,length=100) private String firstNames;
    @NotBlank(message="Enter last names") @Size(max=100)
    @Column(name="apellidos",nullable=false,length=100) private String lastNames;
    @NotNull(message="Select a date of birth") @Past(message="Date of birth must be in the past")
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    @Column(name="fecha_nacimiento",nullable=false) private LocalDate birthDate;
    @NotNull(message="Select a hire date")
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    @Column(name="fecha_ingreso",nullable=false) private LocalDate hireDate;
    @NotBlank(message="Select a department") @Column(name="departamento",nullable=false,length=80) private String department;
    @NotBlank(message="Select a gender") @Column(name="genero",nullable=false,length=20) private String gender;
    public Employee() {}
    public Employee(String nationalId,String firstNames,String lastNames,LocalDate birthDateArg,LocalDate hireDateArg,String department,String gender){
        this.nationalId=nationalId;this.firstNames=firstNames;this.lastNames=lastNames;this.birthDate=birthDateArg;this.hireDate=hireDateArg;this.department=department;this.gender=gender;
    }
    public String getNationalId(){return nationalId;} public void setNationalId(String v){nationalId=v;}
    public String getFirstNames(){return firstNames;} public void setFirstNames(String v){firstNames=v;}
    public String getLastNames(){return lastNames;} public void setLastNames(String v){lastNames=v;}
    public LocalDate getBirthDate(){return birthDate;} public void setBirthDate(LocalDate v){birthDate=v;}
    public LocalDate getHireDate(){return hireDate;} public void setHireDate(LocalDate v){hireDate=v;}
    public String getDepartment(){return department;} public void setDepartment(String v){department=v;}
    public String getGender(){return gender;} public void setGender(String v){gender=v;}
    public String getFullName(){return firstNames+" "+lastNames;}
}
