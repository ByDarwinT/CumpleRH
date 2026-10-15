# CumpleRH — Employee Birthday Calendar

A small Spring MVC classroom application with Thymeleaf, JPA, H2, and a bilingual interface.

## Open in Apache NetBeans

1. Open the `CumpleRH` folder as a Maven project (it contains `pom.xml`).
2. Use Java 17 or a compatible later version.
3. Run `CumpleRhApplication.java`.
4. Open http://localhost:8080.

English is the default interface language. Use the **EN / ES** control in the top bar to switch to Spanish. Your choice is retained in this browser.

The DataLoader provides up to 220 sample employees in a new database, preserving existing saved employees.

The project retains the existing database field names and stored department/gender values for compatibility with previous installations. Identifiers in the Java code remain in English.

## Notes

Data is stored in the existing local H2 database and does not need PostgreSQL.
