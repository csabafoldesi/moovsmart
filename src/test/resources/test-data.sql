INSERT INTO account (email, full_name, password, role, account_status)
VALUES ('moovsmartproject2020.1@gmail.com',
        'moovsmart',
        '$2a$10$F7YUmYcs5JuHAoRXCk9cz.3mLstMNTxRW3uRR1yZaCis7F8xW2OW.',
        'ROLE_OWNER',
        'ACTIVATED');

INSERT INTO account (account_status, activation_key, email, full_name, password, role)
VALUES ('ACTIVATED', 'btvqnuospwkqrodq', 'test@test.test', 'Test user',
        '$2a$10$v2UH34K5VVvEr2EJ1b9rSuvccdgzbIxf9meog4QSQX68kdiFFko8m', 'ROLE_VISITOR');

INSERT INTO account (account_status, activation_key, email, full_name, password, role)
VALUES ('INACTIVATED', 'asdfghjk', 'test2@test.test', 'Test2 user',
        '$2a$10$v2UH34K5VVvEr2EJ1b9rSuvccdgzbIxf9meog4QSQX68kdiFFko8m', 'ROLE_VISITOR');

INSERT INTO property (name, description, number_of_rooms, price, state, account_id, floor_area, lot_size, balcony_size,
                      year_built,
                      property_type, property_condition, property_heating, property_parking)
VALUES ('Beautiful Cosy Apartment',
        'The Twin Property provides comfort in the form of two single beds along with the standard amenities provided. Providing you with ease and comfort during your stay with the perfect furnishing.',
        2,
        100000,
        'ACTIVE',
        1,
        70,
        70,
        6,
        2015,
        'APARTMENT',
        'NOVEL',
        'COMBINATION_BOILER',
        'UNDERGROUND_PARKING_SPACE');

INSERT INTO property (name, description, number_of_rooms, price, state, account_id, floor_area, lot_size, balcony_size,
                      year_built,
                      property_type, property_condition, property_heating, property_parking)
VALUES ('Pleasure Cave',
        'tea and coffee-making facilities, a hairdryer and a private bathroom are included in this property.',
        2,
        342000,
        'ACTIVE',
        1,
        120,
        120,
        10,
        2017,
        'APARTMENT',
        'NEWLY_BUILT',
        'COMBINATION_BOILER_FLOOR_HEATING',
        'UNDERGROUND_PARKING_SPACE');

INSERT INTO property (name, description, number_of_rooms, price, state, account_id, floor_area, lot_size, balcony_size,
                      year_built,
                      property_type, property_condition, property_heating, property_parking)
VALUES ('Bachelors''s paradise',
        'a 3-minute walk from Trafalgar Square, 275 m from Arts Theatre and a 4-minute walk from Savoy Theatre. Guests staying at this apartment can use the fully equipped kitchen.',
        2,
        500000,
        'ACTIVE',
        1,
        150,
        150,
        10,
        2019,
        'APARTMENT',
        'NEWLY_BUILT',
        'COMBINATION_BOILER_FLOOR_HEATING',
        'STREET');

/*INSERT INTO upload(id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                   upload_datetime, property_id, state)
VALUES (1, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1599513718/moovsmart/rimbun-sanctuary-apartment-fb_vi0eex.jpg',
        220007, 'image/jpeg', 'rimbun-sanctuary-apartment-fb.jpg', 'moovsmart/rimbun-sanctuary-apartment-fb_vi0eex',
        NULL, '2020-09-07 21:21:59.041060', 1, 'ACTIVE');
INSERT INTO upload(id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                   upload_datetime, property_id, state)
VALUES (2, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1599513803/moovsmart/hbz-ariana-grande-pete-davidson-apartment-01-1529527439_xzi2pv.jpg',
        26763, 'image/jpeg', 'hbz-ariana-grande-pete-davidson-apartment-01-1529527439.jpg',
        'moovsmart/hbz-ariana-grande-pete-davidson-apartment-01-1529527439_xzi2pv', NULL, '2020-09-07 21:23:23.783071',
        2, 'ACTIVE');
INSERT INTO upload(id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                   upload_datetime, property_id, state)
VALUES (3, 'moovsmart', 'https://res.cloudinary.com/diruti3zy/image/upload/v1599513851/moovsmart/og_b9la3u.jpg', 672690,
        'image/jpeg', 'og.jpg', 'moovsmart/og_b9la3u', NULL, '2020-09-07 21:24:12.006836', 3, 'ACTIVE');
*/
