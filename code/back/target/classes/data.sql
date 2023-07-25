/* Roles */
INSERT INTO public.user_role(name) VALUES
            ('ADMIN'),
            ('USER'),
            ('VISITOR')
            ;

/* Privilege */
INSERT INTO public.user_privileges(name, description) VALUES
     ('admin','admin'),
     ('export','export'),
     ('write','write'),
     ('read','read')
      ;

/* Roles_privileges */
INSERT INTO public.roles_privileges (role_id, privilege_id)VALUES
                                        (1,1),
                                        (1,2),
                                        (1,3),
                                        (2,2),
                                        (2,3)
                                        ;

/* USERS */
INSERT INTO public.users(email, first_name, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_deleted, is_enabled, last_name, password, username, id_role) VALUES
                        ('r.fuentes@itso.fr', 'romain', true, true, true, false, true, 'Fuentes', '$2a$10$ZUUo1.cbkpMBgLx8Fwr5MubQRf0UnXPMxkwbyImCBAoFA/gMDUEye', 'r.fuentes', 1),
                        ('n.aissat@itso.fr', 'Nabil', true, true, true, false, true, 'aissatna', '$2a$10$ZUUo1.cbkpMBgLx8Fwr5MubQRf0UnXPMxkwbyImCBAoFA/gMDUEye', 'aissatna', 2),
                        ('sio', 'sio', true, true, true, false, true, 'sio', '$2a$10$sVyuz4vkfZVhTCLk2Jna1em7SMgUF4OiHnzo9LFAC5zDSGFEpFaTa', 'sio', 1),
                        ('siou', 'siou', true, true, true, false, true, 'siou', '$2a$10$0P4Z2Zx9ERpNdmEbjyCO.eRSQER6FTChGhXpnECic8e3KvJpMiu8O', 'siou', 2)
                        ;


/* CLIENT */
INSERT INTO public.client(name) VALUES
     ('Wedeal'),
     ('Close to clothes')
      ;

/* PROJECT */
INSERT INTO public.project(name, id_client) VALUES
     ('api kiosk', 1),
     ('jpi', 2)
      ;

/* Job */
INSERT INTO public.job(code, name) VALUES
    (1,'Backend'),
    (2,'Frontend')
    ;

/* Tag */
INSERT INTO public.tag(code, name) VALUES
         (1, 'Debug'),
         (2, 'Communication MOA'),
         (3, 'Entraide developpement'),
         (4, 'Refact/Opti'),
         (5, 'Améliorations interne'),
         (6, 'Veille Tech'),
         (7, 'GLPI Facturable'),
         (8, 'GLPI Non Facturable'),
         (9, 'Réunion'),
         (10, 'Autres'),
         (11, 'CP/Maladie')
         ;

/* Feature */
INSERT INTO public.feature(code, name, id_project,temps_E,date_E) VALUES
    (1, 'Afficher toutes les kiosks', 1 ,10, '2023-03-10T00:00:00.000+00:00'),
    (1, 'Afficher toutes les jpi', 2 ,16, '2023-03-10T00:00:00.000+00:00'),
    (2, 'Créer des utilisateurs', 2, 9 , '2023-03-10T00:00:00.000+00:00')
    ;