INSERT INTO sys_user (username, password, roles) VALUES ('admin', '{bcrypt}$2a$10$GSMz1Trk/rLEgFKHoOcgJOuzuddvdsmEm2JHxbkT.ccgeHCdfUfny', 'ROLE_USER, ROLE_POST_CREATE, ROLE_POST_DELETE, ROLE_POST_UPDATE, ROLE_POST_QUERY');
INSERT INTO sys_user (username, password, roles) VALUES ('demo', '{noop}123456', 'ROLE_USER');
