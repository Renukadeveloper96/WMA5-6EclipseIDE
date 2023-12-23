
INSERT INTO `mintondb`.`users`
(`id`,
`created_on`,
`delete_flag`,
`email`,
`email_verified`,
`is_active`,
`is_authenticated_from_social_media`,
`name`,
`password`,
`phone_number`,
`provider`
)
VALUES
(1 ,
'2023-06-30 13:45:42',
0,
'testadmin@gmail.com',
1,
1,
0,
'superadmin',
'$2a$10$tLGMbiFGTzQS9TDTwpUZOeXyzBI20Cxni4uozClLv071uGZuXvkem',0,'local');

INSERT INTO `role` VALUES (1,NULL,NULL,1,'ADMIN',NULL),(2,NULL,NULL,1,'USER',NULL);

INSERT INTO `user_role` VALUES (101,1);
INSERT INTO `user_role` VALUES (1,2);
