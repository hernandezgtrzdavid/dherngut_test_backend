CREATE TABLE TASK (
                      id SERIAL PRIMARY KEY,
                      creation_user varchar,
                      description varchar,
                      creation_date timestamp,
                      active boolean
);
