insert into city (id, area_code, name)
values (1, 324, 'Mersin'),
       (2, 312, 'Ankara');

insert into district(id, name, city_id)
values (1, 'Tarsus', 1),
       (2, 'Mut', 1),
       (3, 'Akdeniz', 1),
       (4, 'Yenimahalle', 2);

insert into permission(id, name)
values (1, 'USER_READ'),
       (2, 'USER_WRITE'),
       (3, 'MEMBER_READ'),
       (4, 'MEMBER_WRITE'),
       (5, 'MANAGER_WRITE'),
       (6, 'MANAGER_READ'),
       (7, 'BRANCH_CREATE'),
       (8, 'BRANCH_READ'),
       (9, 'BRANCH_UPDATE'),
       (10, 'TRAINER_WRITE'),
       (11, 'TRAINER_READ'),
       (12, 'EXERCISE_READ'),
       (13, 'EXERCISE_WRITE'),
       (14, 'PLAN_WRITE'),
       (15, 'PLAN_READ'),
       (16, 'MEMBERSHIP_WRITE'),
       (17, 'MEMBERSHIP_READ');

insert into role(id, name)
values (1, 'USER'),
       (2, 'MEMBER'),
       (3, 'MANAGER'),
       (4, 'OWNER'),
       (5, 'TRAINER');

insert into exercise(id, description, name, video_url)
VALUES (1000, 'salutation', 'salutation', 'https://www.youtube.com/watch?v=dQw4w9WgXcQ'),
       (1001, 'plank', 'plank', 'https://www.youtube.com/watch?v=dQw4w9WgXcQ'),
       (1002, 'side-plank', 'side-plank', 'https://www.youtube.com/watch?v=dQw4w9WgXcQ'),
       (1003, 'squat', 'squat', 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');