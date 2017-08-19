--PostreSql. Client Table:  
CREATE TABLE clients (
  id bigint NOT NULL,                       -- 1, 2, 3 and so on
  name character varying(127) NOT NULL,     -- Lovy Babon, Jim 
  state int NOT NULL                        -- 1 - ACTIVE, 2 - BLOCKED, 3 - DELETED, 4 - LEGEND
);

INSERT INTO clients (id, name, state) VALUES(1, 'Lovy Babon', 1);