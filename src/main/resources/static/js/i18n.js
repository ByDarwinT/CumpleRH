(() => {
  'use strict';
  const spanish = {
  "Birthday management": "Gestión de cumpleaños",
  "MAIN MENU": "MENÚ PRINCIPAL",
  "Calendar": "Calendario",
  "Employees": "Empleados",
  "New employee": "Nuevo empleado",
  "Academic project": "Proyecto académico",
  "Human Resources": "Recursos Humanos",
  "Local system": "Sistema local",
  "MAIN DASHBOARD": "PANEL PRINCIPAL",
  "Birthday calendar 🎉": "Calendario de cumpleaños 🎉",
  "See upcoming birthdays and celebrate with your team.": "Consulta quién cumple años cada mes y celebra con tu equipo.",
  "Add employee": "Agregar empleado",
  "Registered employees": "Empleados registrados",
  "People in the system": "Personal en el sistema",
  "Birthdays this month": "Cumpleaños del mes",
  "In the selected month": "En el mes seleccionado",
  "Next birthday": "Próximo cumpleaños",
  "No birthdays": "Sin cumpleaños",
  "Today": "Hoy",
  "Previous month": "Mes anterior",
  "Next month": "Mes siguiente",
  "Birthday date": "Día con cumpleaños",
  "Select a birthday to see details.": "Selecciona una celebración para ver los detalles.",
  "Close": "Cerrar",
  "BIRTHDAY": "CUMPLEAÑOS",
  "Department": "Departamento",
  "View employee": "Ver empleado",
  "Academic Spring MVC application": "Aplicación académica con Spring MVC",
  "Employee details": "Detalle del empleado",
  "Details": "Detalle",
  "EMPLOYEE PROFILE": "FICHA DEL PERSONAL",
  "Information saved in the system.": "Información registrada en el sistema.",
  "Back to list": "Volver al listado",
  "National ID": "Cédula",
  "Gender": "Género",
  "Date of birth": "Fecha de nacimiento",
  "Hire date": "Fecha de ingreso",
  "Back": "Volver",
  "Edit employee": "Editar empleado",
  "EMPLOYEE MANAGEMENT": "GESTIÓN DE EMPLEADOS",
  "Register employee": "Registrar empleado",
  "Registration and editing": "Registro y edición",
  "Fill in the information and save.": "Completa los datos y guarda el registro.",
  "Personal information": "Información personal",
  "Select gender": "Seleccionar género",
  "First names": "Nombres",
  "Last names": "Apellidos",
  "Select department": "Seleccionar departamento",
  "Cancel": "Cancelar",
  "Save changes": "Guardar cambios",
  "10 digits": "10 dígitos",
  "e.g. Mary Anne": "Ej. María Fernanda",
  "e.g. Smith Jones": "Ej. López García",
  "Search, view, and manage your team records.": "Busca, consulta y administra los datos de tu equipo.",
  "Employee list": "Listado de empleados",
  "result(s)": "resultado(s)",
  "Search by first name, last name, or ID": "Buscar por nombre, apellido o cédula",
  "Search employees": "Buscar empleados",
  "Search": "Buscar",
  "Clear": "Limpiar",
  "EMPLOYEE": "EMPLEADO",
  "NATIONAL ID": "CÉDULA",
  "DEPARTMENT": "DEPARTAMENTO",
  "HIRE DATE": "FECHA DE INGRESO",
  "ACTIONS": "ACCIONES",
  "View": "Ver",
  "Edit": "Editar",
  "Delete": "Eliminar",
  "No employees found. Try another search or add an employee.": "No hay empleados para mostrar. Prueba otra búsqueda o registra uno nuevo.",
  "Delete this employee? This action cannot be undone.": "¿Eliminar este empleado? Esta acción no se puede deshacer.",
  "Administration": "Administración",
  "Accounting": "Contabilidad",
  "Sales": "Ventas",
  "Technology": "Tecnología",
  "Logistics": "Logística",
  "Male": "Masculino",
  "Female": "Femenino",
  "Employee registered successfully": "Empleado registrado correctamente",
  "Employee updated successfully": "Empleado actualizado correctamente",
  "Employee deleted successfully": "Empleado eliminado correctamente",
  "The requested employee could not be found": "No se encontró el empleado solicitado",
  "An employee with this ID already exists": "Ya existe un empleado con esta cédula",
  "Employee not found": "Empleado no encontrado",
  "Check the dates, department, and gender": "Verifica las fechas, el departamento y el género",
  "National ID is required": "La cédula es obligatoria",
  "National ID must contain exactly 10 digits": "La cédula debe tener exactamente 10 dígitos",
  "Enter first names": "Ingresa los nombres",
  "Enter last names": "Ingresa los apellidos",
  "Select a date of birth": "Selecciona la fecha de nacimiento",
  "Date of birth must be in the past": "La fecha de nacimiento debe ser pasada",
  "Select a hire date": "Selecciona la fecha de ingreso",
  "Select a department": "Selecciona un departamento",
  "Select a gender": "Selecciona un género",
  "Form": "Formulario",
  "October 2026": "Octubre 2026",
  "Mon": "Lun",
  "Tue": "Mar",
  "Wed": "Mié",
  "Thu": "Jue",
  "Fri": "Vie",
  "Sat": "Sáb",
  "Sun": "Dom",
  "Academic projects": "Proyectos académicos",
  "Staff": "Personal",
  "Manage employees": "Administrar empleados",
  "results": "resultados",
  "Employees registered in the system.": "Empleados registrados en el sistema."
};
  const english = Object.fromEntries(Object.entries(spanish).map(([en, es]) => [es,en]));
  const languageKey = 'cumplerh.language';
  let language = localStorage.getItem(languageKey) === 'es' ? 'es' : 'en';
  const original = new WeakMap();
  const originalAttrs = new WeakMap();
  function translateText(text, dictionary) {
    const trimmed = text.trim();
    if (!trimmed) return text;
    const match = dictionary[trimmed];
    if (match !== undefined) return text.replace(trimmed, match);
    // flash messages and mixed descriptions containing translated text
    let translated = trimmed;
    for (const [from, to] of Object.entries(dictionary).sort((a,b)=>b[0].length-a[0].length)) {
      translated = translated.split(from).join(to);
    }
    return text.replace(trimmed, translated);
  }
  function applyTranslations() {
    document.documentElement.lang = language;
    document.querySelectorAll('[data-language]').forEach(button => {
      button.classList.toggle('active', button.dataset.language === language);
      button.setAttribute('aria-pressed', String(button.dataset.language === language));
    });
    const walker = document.createTreeWalker(document.body, NodeFilter.SHOW_TEXT);
    let node;
    while ((node = walker.nextNode())) {
      if (!node.parentElement || node.parentElement.closest('script,style,[data-language],#peopleData')) continue;
      if (!original.has(node)) original.set(node, node.nodeValue);
      const source = original.get(node);
      node.nodeValue = language === 'es' ? translateText(source, spanish) : translateText(source, english);
    }
    document.querySelectorAll('[placeholder],[aria-label],[title]').forEach(element => {
      if (!originalAttrs.has(element)) originalAttrs.set(element, {});
      const originals = originalAttrs.get(element);
      for (const attr of ['placeholder', 'aria-label', 'title']) {
        if (!element.hasAttribute(attr)) continue;
        if (!(attr in originals)) originals[attr] = element.getAttribute(attr);
        element.setAttribute(attr, language === 'es' ? translateText(originals[attr], spanish) : translateText(originals[attr], english));
      }
    });
    if (typeof window.renderBirthdayCalendar === 'function') window.renderBirthdayCalendar();
  }
  window.cumpleRhLanguage = () => language;
  window.cumpleRhTranslate = (englishText) => language === 'es' ? (spanish[englishText] ?? englishText) : englishText;
  document.querySelectorAll('[data-language]').forEach(button => button.addEventListener('click', () => {
    language = button.dataset.language;
    localStorage.setItem(languageKey, language);
    applyTranslations();
  }));
  applyTranslations();
})();