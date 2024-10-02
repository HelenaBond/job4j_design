CREATE TABLE department (
    id SERIAL CONSTRAINT department_pkey PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL CONSTRAINT department_department_name_key UNIQUE
);

CREATE TABLE employee (
    id SERIAL CONSTRAINT employee_pkey PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL CONSTRAINT employee_employee_name_key UNIQUE,
    department_id INTEGER CONSTRAINT employee_department_id_fkey REFERENCES department(id)
);

CREATE TABLE teens (
    id SERIAL CONSTRAINT teens_pkey PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL CONSTRAINT teens_teens_name_key UNIQUE,
    gender VARCHAR(100) NOT NULL
);
