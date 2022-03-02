insert into city (id, area_code, name) values (1, 324, 'Mersin');
insert into city (id, area_code, name) values (2, 312, 'Ankara');
insert into district(id, name, city_id) values (1, 'Tarsus', 1);
insert into district(id, name, city_id) values (2, 'Mut', 1);
insert into district(id, name, city_id) values (3, 'Akdeniz', 1);
insert into district(id, name, city_id) values (4, 'Yenimahalle', 2);

insert into permission(id, name) values (1, 'USER_READ');
insert into permission(id, name) values (2, 'USER_WRITE');
insert into permission(id, name) values (3, 'MEMBER_READ');
insert into permission(id, name) values (4, 'MEMBER_WRITE');
insert into permission(id, name) values (5, 'MANAGER_WRITE');
insert into permission(id, name) values (6, 'MANAGER_READ');
insert into permission(id, name) values (7, 'ADMIN_READ');
insert into permission(id, name) values (8, 'ADMIN_WRITE');
insert into permission(id, name) values (9, 'BRANCH_READ');
insert into permission(id, name) values (10, 'BRANCH_WRITE');