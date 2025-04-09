CREATE TABLE IF NOT EXISTS empleados (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    sueldo REAL NOT NULL,
    contrato TEXT NOT NULL,
    departamento TEXT NOT NULL
);