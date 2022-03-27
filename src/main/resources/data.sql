insert into city (id, area_code, name) values (1, 324, 'Mersin'),
                                              (2, 312, 'Ankara');

insert into district(id, name, city_id) values (1, 'Tarsus', 1),
                                               (2, 'Mut', 1),
                                               (3, 'Akdeniz', 1),
                                               (4, 'Yenimahalle', 2);

insert into permission(id, name) values (1, 'USER_READ'),
                                        (2, 'USER_WRITE'),
                                        (3, 'MEMBER_READ'),
                                        (4, 'MEMBER_WRITE'),
                                        (5, 'MANAGER_WRITE'),
                                        (6, 'MANAGER_READ'),
                                        (7, 'ADMIN_READ'),
                                        (8, 'ADMIN_WRITE'),
                                        (9, 'BRANCH_READ'),
                                        (10, 'BRANCH_WRITE'),
                                        (11, 'TRAINER_WRITE'),
                                        (12, 'TRAINER_READ'),
                                        (13, 'EXERCISE_READ'),
                                        (14, 'EXERCISE_WRITE'),
                                        (15, 'PLAN_WRITE'),
                                        (16, 'PLAN_READ');

insert into exercise(id, description, name, video_url) VALUES (1000, 'salutation', 'salutation', 'https://www.youtube.com/watch?v=dQw4w9WgXcQ'),
                                                              (1001, 'plank', 'plank', 'https://www.youtube.com/watch?v=dQw4w9WgXcQ'),
                                                              (1002, 'side-plank', 'side-plank', 'https://www.youtube.com/watch?v=dQw4w9WgXcQ'),
                                                              (1003, 'squat', 'squat', 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');