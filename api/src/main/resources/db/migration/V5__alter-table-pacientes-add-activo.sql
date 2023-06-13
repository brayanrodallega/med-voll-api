alter table pacientes add column activo tinyint not null;
update pacientes set activo = 1;