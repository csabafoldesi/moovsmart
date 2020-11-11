INSERT INTO account (id, email, full_name, password, role, phone_number, account_status, activation_key)
VALUES (1,
        'moovsmart-admin@moovsmart.com',
        'Moovsmart Admin',
        '$2a$10$g3vEwNnGP.ywlc1hqnkXA.1whyDB.Nyt89aJ6OnuvLqDhQcfy3jJy',
        'ROLE_ADMIN',
        '+36-30-1234567',
        'ACTIVATED',
        '');

INSERT INTO account (id, email, full_name, password, role, phone_number, account_status, activation_key)
VALUES (2,
        'moovsmart-visitor@moovsmart.com',
        'Moovsmart Visitor',
        '$2a$10$kU0cuuyeChhXkO1pvCkJoeMM8vMnC3N1PloHfbJYlWjcGTO0rsgEq',
        'ROLE_VISITOR',
        '+36-20-3214567',
        'ACTIVATED',
        '');

INSERT INTO account (id, email, full_name, password, role, phone_number, account_status, activation_key)
VALUES (3,
        'moovsmart-owner1@moovsmart.com',
        'Elena Mckenzie',
        '$2a$10$mdC8nE9oVqFDu0A4rJd16u8IRLQWTN95LvO0MseqATXd71zGxp7Wu',
        'ROLE_OWNER',
        '+36-70-7654321',
        'ACTIVATED',
        '');

INSERT INTO account (id, email, full_name, password, role, phone_number, account_status, activation_key)
VALUES (4,
        'moovsmart-owner2@moovsmart.com',
        'Christian Lafaille',
        '$2a$10$pRUZEY.1I9OUYnrtFIVH4uokv.dWVJg7XM9WxUVoMV0smJm.tdL3S',
        'ROLE_OWNER',
        '+36-70-7651234',
        'ACTIVATED',
        '');

INSERT INTO account (id, email, full_name, password, role, phone_number, account_status, activation_key)
VALUES (5,
        'moovsmart-owner3@moovsmart.com',
        'Pedro Carretero',
        '$2a$10$eXfA2D6Eu23ruzYD9bU7Ye0QxBDKU1A.lsE3a4e1q5hTLMjJsTMWG',
        'ROLE_OWNER',
        '+36-70-5674321',
        'ACTIVATED',
        '');

INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (1, 5, 'Epsom', 'England',
        'A beautifully presented and improved four bedroom semi detached house situated in a prominent location on the sought\n        after St Andrew\'s Park development, within easy reach of Epsom town centre. This stunning property was constructed\n        in 2017 by St Modwen Homes to a high standard and offers a \"bright and airy\" feel, due to its white painted walls,\n        large windows and high ceilings. Viewing is highly recommended.',
        110, 37.5188, -77.4951, 110, '4 bed semi-detached house, England', 4, 599000, 'NEWLY_BUILT',
        'COMBINATION_BOILER_FLOOR_HEATING', 'GARAGE', 'HOUSE', 'ACTIVE', '73  Boroughbridge Road', 2017, 'UB10', 3);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (2, 6, 'Uxbridge', 'England',
        'A unique house with extensive entertaining space moments from the heart of the Old Town and Layburn Court.\n        The property, which stretches over three floors, really must be seen to be fully appreciated! The special\n        home can be used as either two, or up to four double bedroom house.',
        100, 52.3171, 0.386715, 110, '3 bed terraced house for sale, England', 3, 379000, 'NEWLY_BUILT',
        'COMBINATION_BOILER', 'GARAGE', 'APARTMENT', 'ACTIVE', '52 Layburn Court', 2019, 'CB7 5ND', 3);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (3, 3, 'Dagenham', 'England',
        'Opportunity Knocks! A commuter’s Dream! Desirable 2 bed ground floor maisonette located\n        in a much sought-after area of South Sutton. It is superbly situated within walking distance\n        to Dagenham Train Station & bus network offering links to the City & Central London, and\n        Dagenham Town centre with its vast array of shopping facilities, fine dining, theatres,\n        Parks and excellent amenities. It is also within the catchment of excellent Primary, State & Grammar Schools.',
        70, 51.5414, 0.133956, 80, '2 bed maisonette for sale, England', 2, 325000, 'NOVEL', 'COMBINATION_BOILER',
        'GARAGE', 'HOUSE', 'ACTIVE', '57 Ivy Road', 2016, 'SM2', 3);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (4, 0, 'Sutton', 'England',
        'Steps Estate Agents are pleased to offer the sale of this Two Bedroom Fully Detached House\n        located in the Sutton East Tube area close to Schools & Amenities. Benefitting from a through lounge,\n        fitted kitchen, dining room and a first floor bathroom. Additionally the property has double glazing,\n        gas central heating, off street parking and side gate access to the rear 100 foot garden. Applicants\n        are urged to make an early viewing to avoid disappointment.',
        80, 50.8689, 0.458268, 100, '2 bedroom detached house for sale, England', 2, 369000, 'NOVEL',
        'NATURAL_GAS_BOILER', 'STREET', 'HOUSE', 'ACTIVE', '103 Freezeland Lane', 2015, 'RM10', 3);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (5, 8, 'Paris', 'France',
        'Between the Chaumont Buttes and the Parc de La Villette, in a building of good standing,\n        a 3/4 room apartment of about 71m2 comprising 2 bedrooms (11.6 and 10.3 m2), a stay of about 23.3 m2,\n        a separate kitchen (possibility to open on the living room), a bathroom, separate toilets and 2 balconies.\n        Lots of storage. A basement parking lot completes this property. Transportation (metro lines 5 and 7bis,\n        Tramway, buses), shops and schools in the immediate vicinity.',
        71, 48.8433, 2.32737, 71, 'Sale 3-room apartment, France', 2, 495000, 'NOVEL', 'NATURAL_GAS_BOILER',
        'UNDERGROUND_PARKING_SPACE', 'APARTMENT', 'ACTIVE', '112 place Stanislas', 2017, '44000', 4);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (6, 2, 'Paris', 'France',
        'On the top floor of a small old building without elevator, the Vaneau group offers you\n        a charming 3-room apartment, in the heart of shops and embassies, not far from the Place\n        des Etats-Unis. Completely renovated and with great benefits, this very bright and very good\n        50m² property consists of a living room with an open and equipped kitchen, 2 large bedrooms,\n        a superb shower room with glass roof and toilet. Very bright apartment!',
        50, 48.8566, 2.35222, 50, '3-room apartment, France', 3, 425000, 'RENOVATED', 'NATURAL_GAS_BOILER', 'STREET',
        'APARTMENT', 'ACTIVE', '101 Rue Frédéric Chopin', 2013, '78000', 4);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (7, 0, 'Paris', 'France',
        'Rue du Limas, 4min walk from the Brochant metro and 6 min walk from the Guy Moquet metro,\n        discover this charming 2-room room of about 26m2 in the Rdc on the course of a well-maintained\n        residence of 1870, totally quiet. It has a small entrance with closets, a living room, a shower room\n        with a toilet overlooking a small courtyard, a separate kitchen with a beautiful ceiling height equipped\n        with a mechanical velux and a small office. Close to all the amenities!',
        30, 48.8566, 2.35222, 30, 'Sale 3-piece apartment, France', 2, 275000, 'NOVEL', 'NATURAL_GAS_BOILER', 'STREET',
        'APARTMENT', 'ACTIVE', '90 Rue du Limas', 2014, '20200', 4);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (8, 0, 'Bergerac', 'France',
        'Charming Fully Renovated Spacious 4 Bedroom Maison de Maitre and Double Garage set in 1/4 of an acre\n        of enclosed Gardens located within easy reach of shops and amenities in the Historic market town of Bergerac.\n        This character property offers 280 m² of living space over 3 floors and has been fully and tastefully renovated\n        throughout creating a superb comfortable family home.',
        150, 44.8529, 0.485561, 280, 'Bergerac, Pas-de-Calais, Nord Pas-de-Calais, France', 4, 249000, 'RENOVATED',
        'NATURAL_GAS_BOILER', 'GARAGE', 'HOUSE', 'ACTIVE', '36 Rue du Palais', 2010, '93800', 4);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (9, 8, 'Berlin', 'Germany',
        'The apartment, which is vacant and ready to move into, is on the 3rd floor (with lift!)\n        and so is very bright. The back side (living room/bedroom/balcony) is south-west facing and\n        so is especially sunny long into the summer evenings.',
        63, 52.5428, 13.5003, 63, 'Berlin Wedding apartment for sale, Germany', 2, 319000, 'NEWLY_BUILT',
        'CENTRAL_HEATING', 'UNDERGROUND_PARKING_SPACE', 'APARTMENT', 'ACTIVE', 'Genslerstraße 84', 2020, '13359', 3);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (10, 0, 'Berlin', 'Germany',
        'Constructed in the early 1900s an era of architectural flair. When large windows and high ceilings were\n        the normal expectation. Kaiserin-Augusta-Allee 29 is no exception to this. The classic Alt-Bau built over\n        1831m2 and consists of 25 separate apartments. The buildings division took place in November 2017.\n        The courtyard is lined with greenery. When arriving at the building you will notice how it bares\n        its name proudly, a true statement of its history.',
        53, 52.5028, 13.3343, 53, '1 Bedroom Apartment in Charlottenburg, Germany', 1, 249000, 'RENOVATED',
        'COMBINATION_BOILER', 'STREET', 'APARTMENT', 'ACTIVE', 'Los-Angeles-Platz 28', 1910, '22589', 3);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (11, 7, 'Berlin', 'Germany',
        'Built in 1906 the house was gradually renovated, well maintained and all old building stylistic\n        elements like the staircase, railings,ceiling and apartment doors, have been renovated in a high-quality\n        manner. All stairwells and common areas including the green courtyard are very well maintained.\n        The apartment has the typical old building charm (Parquet floor or wooden floorboards, stucco, etc.),\n        are spacious and bright.',
        67, 52.5054, 13.3024, 67, '1 Bedroom Apartment in Reinickendorf, Germany', 1, 149000, 'RENOVATED',
        'NATURAL_GAS_BOILER', 'STREET', 'APARTMENT', 'ACTIVE', 'Stuttgarter Platz 91', 1906, '79297', 3);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (12, 3, 'Stuttgart', 'Germany',
        'Even from the outside, the house impresses with its harmonious proportions. This house offers\n        a lot of space for the family and immediately impresses with a well thought-out floor plan.\n        On the ground floor there is a spacious entrance area from which both the kitchen and the living /\n        dining area are accessible. The light-flooded, generously proportioned rooms allow a wide range of\n        design options. The fireplace in the living room creates atmosphere.',
        157, 48.7758, 9.18293, 447, 'Single-family house, Germany', 5, 575000, 'NOVEL', 'COMBINATION_BOILER', 'GARAGE',
        'HOUSE', 'ACTIVE', 'An Der Urania 13', 1998, '25508', 3);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (13, 3, 'Madrid', 'Spain',
        'The 85 m² property is located on the fourth floor of a building with a classical facade,\n        built at the beginning of the tenth century, as evident in the stately entrance hall with\n        crown moldings and original carvings. It\'s enviable location, situation on one of the most\n        characteristic streets of the Salamanca district: Ortega and Gasset, is synonymous with luxury\n        and exclusivity.',
        85, 40.6367, -3.99805, 85, 'Renovated Two Bedroom Apartment, Salamanca, Spain', 2, 519000, 'NOVEL',
        'CENTRAL_HEATING', 'GARAGE', 'APARTMENT', 'ACTIVE', 'Extramuros 74', 2005, '28400', 5);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (14, 20, 'Sevilla', 'Spain',
        'This cozy home consists of an entrance hall, kitchen, living room, four bedrooms and two bathrooms.\n        Porcelain floors in perfect condition, heating and individual water by natural gas.This penthouse\n        is located in an area with all services, metro, buses, five minutes walk from the Las Rosas shopping center.',
        130, 37.3891, -5.98446, 130, 'Penthouse for sale in Pueblo Nuevo, Spain', 4, 495000, 'NOVEL',
        'NATURAL_GAS_BOILER', 'GARAGE', 'APARTMENT', 'ACTIVE', 'Rúa do Paseo 30', 1994, '41920', 5);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (15, 12, 'Valdemorillo', 'Spain',
        'You want to live in the Sierra de Madrid, with all the tranquility of the countryside, breathe fresh air,\n        live in relaxation, enjoy the closeness of your neighbors, perfectly communicated ... and with a really\n        attractive price. Do not hesitate, this is your house! Perfect to fully enjoy your family in Valdemorillo,\n        we have this MAGNIFICENT CHALET of 307 m2 on a PLOT of 1025 m2.',
        307, 41.3574, 2.08123, 1025, '4 bedroom house for sale, Spain', 4, 289000, 'NOVEL', 'COMBINATION_BOILER',
        'GARAGE', 'HOUSE', 'ACTIVE', 'Comandante Izarduy 67', 1983, '08940', 5);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (16, 10, 'Valencia', 'Spain',
        'Wonderful Neptuno Deluxe style villa with private swimming pool and walking distance to shops bars etc.\n        The ground floor consists of a large shady, covered veranda, which has been fully glazed to create a light,\n        sunny conservatory. The front door opens into an entrance lobby with built in wardrobe/cupboard. Large lounge with fireplace,\n        central heating and air conditioning. Archway through to dining area with dining room suite. Arch through\n        to fully fitted kitchen. Back door with access to the garden, beautiful outside dining area and private swimming pool.',
        140, 39.4699, -0.376288, 220, 'Detached house, Spain', 3, 217000, 'NOVEL', 'COMBINATION_BOILER', 'GARAGE',
        'HOUSE', 'ACTIVE', 'Canónigo Valiño 98', 2008, '46210', 5);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (17, 4, 'Budapest', 'HUNGARY',
        'The flat is located at the third-floor street view near to the central Synagogue of Budapest. One of the\n        most popular attractions in the city. The building is currently under renovation and the new elevator\n        will be built in fall this year. The courtyard and facade of the building will be renovated by the\n        summer next year. The location is considered \" The heart\" of the tourist area of the city and the flat\n        \" An oasis of tranquillity \" in the party area..',
        79, 47.4482, 19.1439, 79, 'District VII, Budapest, Hungary', 3, 149000, 'RENOVATED', 'COMBINATION_BOILER',
        'STREET', 'APARTMENT', 'ACTIVE', 'Kossuth Lajos utca 80.', 2002, '1076', 4);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (18, 0, 'Budapest', 'HUNGARY',
        'Fabulous two-level Penthouse corner Andrássy and Nagymező Street. Flat has space and the possibility\n        to build a private terrace. The location is to be considered magnificent. The building in the corner\n        with Andrássy and Nagymező Street less than a 3-minute walk from the Budapest Opera and 5 min walk from\n        the main city center attractions such as St. Stephen\'s Basilica and Hero Square and Danube.',
        75, 47.5042, 19.0621, 75, 'District VI, Budapest, Hungary', 3, 155000, 'NOVEL', 'NATURAL_GAS_BOILER',
        'UNDERGROUND_PARKING_SPACE', 'APARTMENT', 'ACTIVE', 'Andrássy út 45.', 2012, '1079', 4);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (19, 8, 'Budapest', 'HUNGARY',
        'Bright stylish welcoming 62 sqm one-bedroom flat in the ENVIABLE CENTRAL LOCATION settled in the\n        MODERN BUILDING of 2007. This flat is an EXCELLENT OPPORTUNITY FOR INVESTMENT, do not lose the chance\n        to move in immediately and enjoy Budapest life.',
        62, 47.4759, 19.0161, 62, 'District XIII, Budapest, Hungary', 2, 165000, 'NOVEL', 'NATURAL_GAS_BOILER',
        'UNDERGROUND_PARKING_SPACE', 'APARTMENT', 'ACTIVE', 'Dayka Gábor utca 19.', 2007, '1132', 4);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (20, 14, 'Budapest', 'HUNGARY',
        'The flat is fully furnished and accommodated with STYLE, the corridor leads to the LUMINOUS and GENEROUS\n        living room with American kitchen, cozy bedroom, a spacious bathroom, and a detached comfortable storage room.\n        The living room and the bedroom have access to a large 14 sqm BALCONY.',
        63, 47.5459, 19.0998, 63, 'District XII, Budapest, Hungary', 2, 175000, 'NOVEL', 'NATURAL_GAS_BOILER',
        'UNDERGROUND_PARKING_SPACE', 'APARTMENT', 'ACTIVE', 'Bécsi utca 18.', 2007, '1127', 4);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (21, 16, 'Solymár', 'HUNGARY',
        'Situated in Solymár, in the picturesque area of Kerek Hill, complete with forever panoramic views.\n        Newly renovated in 2019, two floors, living room, 3 bedrooms. Features of this 88 sq m property include\n        double balcony, fully integrated, high quality appliances bought within a year with warranty, both practical\n        and luxurious elements throughout the apartment, beautifully furnished.',
        88, 47.5091, 19.017, 140, 'Solymár, Hungary', 4, 179000, 'RENOVATED', 'COMBINATION_BOILER_FLOOR_HEATING',
        'GARAGE', 'HOUSE', 'ACTIVE', 'Szilágyi Erzsébet fasor 25.', 2011, '4457', 4);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (22, 9, 'Balatonfüred', 'HUNGARY',
        'In the center of Balatonfüred, in a quiet place, a family house with a high-quality, sunny 180 m2 living space\n        is for sale! The two-storey family house underwent a complete reconstruction and renovation in 1998. The advantage of\n        the house is that it is within walking distance of a school, kindergarten, shopping, restaurants and\n        the shores of Lake Balaton.',
        280, 46.9599, 17.8851, 703, 'Balatonfüred, Hungary', 6, 449000, 'RENOVATED', 'COMBINATION_BOILER', 'GARAGE',
        'HOUSE', 'ACTIVE', 'Tó utca 40.', 1990, '2265', 4);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (23, 4, 'Győr', 'HUNGARY',
        'In Győr, in the Nádorváros area, at Apor Péter utca 6, for sale, located on a 612 m2 plot,\n        built in 2011, two-storey, 199 m2, living room + dining room - kitchen, 4-room, three-bathroom house,\n        added with a 31 m2 double garage and a 35 m2 terrace.',
        199, 47.6769, 17.6357, 612, 'Győr, Hungary', 5, 349000, 'NOVEL', 'COMBINATION_BOILER', 'GARAGE', 'HOUSE',
        'ACTIVE', 'Apor Péter utca 6.', 2011, '9934', 4);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (24, 5, 'Basel', 'Switzerland',
        'The Twin Property provides comfort in the form of two single beds along with the standard amenities provided. Providing you with ease and comfort during your stay with the perfect furnishing.',
        50, 47.5596, 7.58858, 50, 'Beautiful Cosy Apartment, Switzerland', 2, 100000, 'NOVEL', 'COMBINATION_BOILER',
        'UNDERGROUND_PARKING_SPACE', 'APARTMENT', 'ACTIVE', 'Herrenberg 132', 2015, '1982', 3);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (25, 7, 'Paris', 'France',
        'Tea and coffee-making facilities, a hairdryer and a private bathroom are included in this property.', 90,
        48.871, 2.32486, 90, 'Pleasure Cave, France', 4, 242000, 'NEWLY_BUILT', 'COMBINATION_BOILER_FLOOR_HEATING',
        'UNDERGROUND_PARKING_SPACE', 'APARTMENT', 'ACTIVE', '61  Place de la Madeleine', 2017, '75015', 4);
INSERT INTO property(id, balcony_size, city, country, description, floor_area, lat, lng, lot_size, name,
                     number_of_rooms, price, property_condition, property_heating, property_parking, property_type,
                     state, street, year_built, zip_code, account_id)
VALUES (26, 8, 'Torrevieja', 'Spain',
        'Exclusive, Practical and Functional apartment located a few minutes from La Playa de los Locos. Surrounded by all kinds of services, Restaurants, Supermarkets, Pharmacies, etc.',
        120, 37.9847, -0.680823, 120, 'Bachelors\' paradise, Spain', 5, 409000, 'NEWLY_BUILT',
        'COMBINATION_BOILER_FLOOR_HEATING', 'STREET', 'APARTMENT', 'ACTIVE', 'Avenida Cervantes 56', 2019, '48310', 5);

INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (1, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600290891/moovsmart/9b0abe7c1ca0f475b415a8f553a7b42a04be4cdd_b3pgqy.jpg',
        279577, 'image/jpeg', '9b0abe7c1ca0f475b415a8f553a7b42a04be4cdd.jpg',
        'moovsmart/9b0abe7c1ca0f475b415a8f553a7b42a04be4cdd_b3pgqy', null, '2020-09-16 21:14:51.460804000', 1,
        'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (2, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600290906/moovsmart/56106402c82d34bd07c04fa5df21e5f985dfc1f0_l9e7ih.jpg',
        167339, 'image/jpeg', '56106402c82d34bd07c04fa5df21e5f985dfc1f0.jpg',
        'moovsmart/56106402c82d34bd07c04fa5df21e5f985dfc1f0_l9e7ih', null, '2020-09-16 21:15:06.029123000', 1,
        'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (3, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600290915/moovsmart/ae2dc6066ef7be18731a9ee10ddab67571668da3_c7jgjp.jpg',
        122156, 'image/jpeg', 'ae2dc6066ef7be18731a9ee10ddab67571668da3.jpg',
        'moovsmart/ae2dc6066ef7be18731a9ee10ddab67571668da3_c7jgjp', null, '2020-09-16 21:15:14.628692000', 1,
        'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (4, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600290919/moovsmart/e618373b112a138a9749f9399a0e41acc12142c5_azqycv.jpg',
        162269, 'image/jpeg', 'e618373b112a138a9749f9399a0e41acc12142c5.jpg',
        'moovsmart/e618373b112a138a9749f9399a0e41acc12142c5_azqycv', null, '2020-09-16 21:15:19.265071000', 1,
        'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (5, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291025/moovsmart/baad69eb094ffbac9e60040e8537f4fe50464d5f_yrskta.jpg',
        160607, 'image/jpeg', 'baad69eb094ffbac9e60040e8537f4fe50464d5f.jpg',
        'moovsmart/baad69eb094ffbac9e60040e8537f4fe50464d5f_yrskta', null, '2020-09-16 21:17:05.302273000', 2,
        'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (6, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291031/moovsmart/53309d583e50b501f0e1861ed86a0ab7184919bd_o7whje.jpg',
        163465, 'image/jpeg', '53309d583e50b501f0e1861ed86a0ab7184919bd.jpg',
        'moovsmart/53309d583e50b501f0e1861ed86a0ab7184919bd_o7whje', null, '2020-09-16 21:17:11.442318000', 2,
        'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (7, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291039/moovsmart/b11a6db94771ddb54223b954a54cd6e73754427d_jn6fcd.jpg',
        114725, 'image/jpeg', 'b11a6db94771ddb54223b954a54cd6e73754427d.jpg',
        'moovsmart/b11a6db94771ddb54223b954a54cd6e73754427d_jn6fcd', null, '2020-09-16 21:17:18.822513000', 2,
        'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (8, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291055/moovsmart/f51899aab279c2c16ae6a7fa24083fcc56e64643_xwhycy.jpg',
        139195, 'image/jpeg', 'f51899aab279c2c16ae6a7fa24083fcc56e64643.jpg',
        'moovsmart/f51899aab279c2c16ae6a7fa24083fcc56e64643_xwhycy', null, '2020-09-16 21:17:34.876612000', 2,
        'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (9, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291071/moovsmart/ea6b8ee84355ecdc6b8ec9fdaea4f3d022f6784e_dxukux.jpg',
        111296, 'image/jpeg', 'ea6b8ee84355ecdc6b8ec9fdaea4f3d022f6784e.jpg',
        'moovsmart/ea6b8ee84355ecdc6b8ec9fdaea4f3d022f6784e_dxukux', null, '2020-09-16 21:17:51.099454000', 2,
        'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (10, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291076/moovsmart/4e95c64df20735dcb9a91ee6d4b3c40fa794f387_rt0va7.jpg',
        261859, 'image/jpeg', '4e95c64df20735dcb9a91ee6d4b3c40fa794f387.jpg',
        'moovsmart/4e95c64df20735dcb9a91ee6d4b3c40fa794f387_rt0va7', null, '2020-09-16 21:17:55.573960000', 2,
        'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (11, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291162/moovsmart/9ce4d66f242f763e19fd96e92494e5f99e997893_lfn6hi.jpg',
        201109, 'image/jpeg', '9ce4d66f242f763e19fd96e92494e5f99e997893.jpg',
        'moovsmart/9ce4d66f242f763e19fd96e92494e5f99e997893_lfn6hi', null, '2020-09-16 21:19:21.677159000', 3,
        'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (12, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291168/moovsmart/42c15cc50b68866595224a7b31637a232bbebd50_onxvux.jpg',
        135104, 'image/jpeg', '42c15cc50b68866595224a7b31637a232bbebd50.jpg',
        'moovsmart/42c15cc50b68866595224a7b31637a232bbebd50_onxvux', null, '2020-09-16 21:19:27.745184000', 3,
        'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (13, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291172/moovsmart/a1697a52ab22e85f480282e948e1bb92b78492a5_bbbofq.jpg',
        149485, 'image/jpeg', 'a1697a52ab22e85f480282e948e1bb92b78492a5.jpg',
        'moovsmart/a1697a52ab22e85f480282e948e1bb92b78492a5_bbbofq', null, '2020-09-16 21:19:32.759064000', 3,
        'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (14, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291177/moovsmart/f453d98d1e60681164ede1bff471a08941b4ff49_y2opee.jpg',
        220391, 'image/jpeg', 'f453d98d1e60681164ede1bff471a08941b4ff49.jpg',
        'moovsmart/f453d98d1e60681164ede1bff471a08941b4ff49_y2opee', null, '2020-09-16 21:19:37.054477000', 3,
        'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (15, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291223/moovsmart/7492_102363012139_IMG_23_0001_dzwk02.jpg',
        192004, 'image/jpeg', '7492_102363012139_IMG_23_0001.jpg', 'moovsmart/7492_102363012139_IMG_23_0001_dzwk02',
        null, '2020-09-16 21:20:23.361132000', 4, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (16, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291230/moovsmart/7492_102363012139_IMG_24_0001_dx0w1l.jpg',
        167452, 'image/jpeg', '7492_102363012139_IMG_24_0001.jpg', 'moovsmart/7492_102363012139_IMG_24_0001_dx0w1l',
        null, '2020-09-16 21:20:29.637212000', 4, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (17, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291240/moovsmart/7492_102363012139_IMG_32_0000_udu1cu.jpg',
        114876, 'image/jpeg', '7492_102363012139_IMG_32_0000.jpg', 'moovsmart/7492_102363012139_IMG_32_0000_udu1cu',
        null, '2020-09-16 21:20:39.921646000', 4, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (18, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291245/moovsmart/7492_102363012139_IMG_38_0000_vlzkme.jpg',
        104128, 'image/jpeg', '7492_102363012139_IMG_38_0000.jpg', 'moovsmart/7492_102363012139_IMG_38_0000_vlzkme',
        null, '2020-09-16 21:20:44.704572000', 4, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (19, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291318/moovsmart/187660a-744738_1_m16bzr.jpg', 216170,
        'image/jpeg', '187660a-744738_1.jpg', 'moovsmart/187660a-744738_1_m16bzr', null,
        '2020-09-16 21:21:58.293417000', 5, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (20, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291323/moovsmart/187660a-744738_2_1_rnx06s.jpg', 208304,
        'image/jpeg', '187660a-744738_2(1).jpg', 'moovsmart/187660a-744738_2_1_rnx06s', null,
        '2020-09-16 21:22:03.499626000', 5, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (21, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291328/moovsmart/187660a-744738_3_1_bxhm0f.jpg', 233198,
        'image/jpeg', '187660a-744738_3(1).jpg', 'moovsmart/187660a-744738_3_1_bxhm0f', null,
        '2020-09-16 21:22:08.261169000', 5, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (22, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291333/moovsmart/187660a-744738_5_mttdoa.jpg', 192322,
        'image/jpeg', '187660a-744738_5.jpg', 'moovsmart/187660a-744738_5_mttdoa', null,
        '2020-09-16 21:22:12.694235000', 5, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (23, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291405/moovsmart/52651a-4031066_1_smiutv.jpg', 194000,
        'image/jpeg', '52651a-4031066_1.jpg', 'moovsmart/52651a-4031066_1_smiutv', null,
        '2020-09-16 21:23:25.285314000', 6, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (24, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291410/moovsmart/52651a-4031066_2_k0halp.jpg', 197219,
        'image/jpeg', '52651a-4031066_2.jpg', 'moovsmart/52651a-4031066_2_k0halp', null,
        '2020-09-16 21:23:30.024911000', 6, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (25, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291414/moovsmart/52651a-4031066_5_oknfcx.jpg', 140331,
        'image/jpeg', '52651a-4031066_5.jpg', 'moovsmart/52651a-4031066_5_oknfcx', null,
        '2020-09-16 21:23:33.995153000', 6, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (26, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291419/moovsmart/52651a-4031066_7_jiibty.jpg', 192701,
        'image/jpeg', '52651a-4031066_7.jpg', 'moovsmart/52651a-4031066_7_jiibty', null,
        '2020-09-16 21:23:39.070840000', 6, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (27, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291424/moovsmart/52651a-4031066_8_nyckov.jpg', 104505,
        'image/jpeg', '52651a-4031066_8.jpg', 'moovsmart/52651a-4031066_8_nyckov', null,
        '2020-09-16 21:23:43.741772000', 6, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (28, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291513/moovsmart/58590a-804143_1_ialvq8.jpg', 271078,
        'image/jpeg', '58590a-804143_1.jpg', 'moovsmart/58590a-804143_1_ialvq8', null, '2020-09-16 21:25:12.746157000',
        7, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (29, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291518/moovsmart/58590a-804143_2_1_kdtplx.jpg', 217249,
        'image/jpeg', '58590a-804143_2(1).jpg', 'moovsmart/58590a-804143_2_1_kdtplx', null,
        '2020-09-16 21:25:18.237075000', 7, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (30, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291522/moovsmart/58590a-804143_3_1_pkiw5a.jpg', 232070,
        'image/jpeg', '58590a-804143_3(1).jpg', 'moovsmart/58590a-804143_3_1_pkiw5a', null,
        '2020-09-16 21:25:21.711349000', 7, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (31, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291571/moovsmart/38517_38517_8531528_IMG_01_0000_gu3fby.jpg',
        84504, 'image/jpeg', '38517_38517_8531528_IMG_01_0000.jpg', 'moovsmart/38517_38517_8531528_IMG_01_0000_gu3fby',
        null, '2020-09-16 21:26:11.444824000', 8, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (32, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291582/moovsmart/38517_38517_8531528_IMG_03_0000_fkne8v.jpg',
        58906, 'image/jpeg', '38517_38517_8531528_IMG_03_0000.jpg', 'moovsmart/38517_38517_8531528_IMG_03_0000_fkne8v',
        null, '2020-09-16 21:26:21.598434000', 8, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (33, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291586/moovsmart/38517_38517_8531528_IMG_04_0000_jcs2gg.jpg',
        86979, 'image/jpeg', '38517_38517_8531528_IMG_04_0000.jpg', 'moovsmart/38517_38517_8531528_IMG_04_0000_jcs2gg',
        null, '2020-09-16 21:26:25.517879000', 8, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (34, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291590/moovsmart/38517_38517_8531528_IMG_05_0000_li1qzi.jpg',
        63975, 'image/jpeg', '38517_38517_8531528_IMG_05_0000.jpg', 'moovsmart/38517_38517_8531528_IMG_05_0000_li1qzi',
        null, '2020-09-16 21:26:30.166187000', 8, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (35, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291597/moovsmart/38517_38517_8531528_IMG_12_0000_xuujdi.jpg',
        49252, 'image/jpeg', '38517_38517_8531528_IMG_12_0000.jpg', 'moovsmart/38517_38517_8531528_IMG_12_0000_xuujdi',
        null, '2020-09-16 21:26:37.255907000', 8, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (36, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291704/moovsmart/88624_YP-FRAUN_IMG_00_0000_myioxx.jpg',
        79587, 'image/jpeg', '88624_YP-FRAUN_IMG_00_0000.jpg', 'moovsmart/88624_YP-FRAUN_IMG_00_0000_myioxx', null,
        '2020-09-16 21:28:23.476351000', 9, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (37, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291719/moovsmart/88624_YP-FRAUN_IMG_04_0000_vgrwyu.jpg',
        81635, 'image/jpeg', '88624_YP-FRAUN_IMG_04_0000.jpg', 'moovsmart/88624_YP-FRAUN_IMG_04_0000_vgrwyu', null,
        '2020-09-16 21:28:39.451328000', 9, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (38, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291747/moovsmart/88624_YP-FRAUN_IMG_05_0000_ggrfi9.jpg',
        74139, 'image/jpeg', '88624_YP-FRAUN_IMG_05_0000.jpg', 'moovsmart/88624_YP-FRAUN_IMG_05_0000_ggrfi9', null,
        '2020-09-16 21:29:07.131259000', 9, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (39, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291753/moovsmart/88624_YP-FRAUN_IMG_06_0000_mmspfn.jpg',
        59580, 'image/jpeg', '88624_YP-FRAUN_IMG_06_0000.jpg', 'moovsmart/88624_YP-FRAUN_IMG_06_0000_mmspfn', null,
        '2020-09-16 21:29:13.008629000', 9, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (40, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291758/moovsmart/88624_YP-FRAUN_IMG_10_0000_ysfmxs.jpg',
        57509, 'image/jpeg', '88624_YP-FRAUN_IMG_10_0000.jpg', 'moovsmart/88624_YP-FRAUN_IMG_10_0000_ysfmxs', null,
        '2020-09-16 21:29:18.542502000', 9, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (41, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291762/moovsmart/88624_YP-FRAUN_IMG_11_0000_ezuoth.jpg',
        59968, 'image/jpeg', '88624_YP-FRAUN_IMG_11_0000.jpg', 'moovsmart/88624_YP-FRAUN_IMG_11_0000_ezuoth', null,
        '2020-09-16 21:29:22.224071000', 9, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (42, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291833/moovsmart/85178_85178_BB50510_IMG_00_0001_ia9ric.jpg',
        88222, 'image/jpeg', '85178_85178_BB50510_IMG_00_0001.jpg', 'moovsmart/85178_85178_BB50510_IMG_00_0001_ia9ric',
        null, '2020-09-16 21:30:33.054439000', 10, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (43, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291838/moovsmart/85178_85178_BB50510_IMG_01_0001_gakfmt.jpg',
        72927, 'image/jpeg', '85178_85178_BB50510_IMG_01_0001.jpg', 'moovsmart/85178_85178_BB50510_IMG_01_0001_gakfmt',
        null, '2020-09-16 21:30:37.582995000', 10, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (44, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291843/moovsmart/85178_85178_BB50510_IMG_02_0001_z5z9oc.jpg',
        116825, 'image/jpeg', '85178_85178_BB50510_IMG_02_0001.jpg', 'moovsmart/85178_85178_BB50510_IMG_02_0001_z5z9oc',
        null, '2020-09-16 21:30:42.664850000', 10, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (45, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291846/moovsmart/85178_85178_BB50510_IMG_03_0001_bwtqvi.jpg',
        42538, 'image/jpeg', '85178_85178_BB50510_IMG_03_0001.jpg', 'moovsmart/85178_85178_BB50510_IMG_03_0001_bwtqvi',
        null, '2020-09-16 21:30:46.585441000', 10, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (46, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291851/moovsmart/85178_85178_BB50510_IMG_04_0001_zo4ab5.jpg',
        49385, 'image/jpeg', '85178_85178_BB50510_IMG_04_0001.jpg', 'moovsmart/85178_85178_BB50510_IMG_04_0001_zo4ab5',
        null, '2020-09-16 21:30:50.638197000', 10, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (47, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291856/moovsmart/85178_85178_BB50510_IMG_05_0000_bvvliv.jpg',
        33063, 'image/jpeg', '85178_85178_BB50510_IMG_05_0000.jpg', 'moovsmart/85178_85178_BB50510_IMG_05_0000_bvvliv',
        null, '2020-09-16 21:30:56.362712000', 10, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (48, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291860/moovsmart/85178_85178_BB50510_IMG_08_0001_yfqbdu.jpg',
        58992, 'image/jpeg', '85178_85178_BB50510_IMG_08_0001.jpg', 'moovsmart/85178_85178_BB50510_IMG_08_0001_yfqbdu',
        null, '2020-09-16 21:31:00.371480000', 10, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (49, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291955/moovsmart/85178_85178_BB44211_IMG_04_0000_rxnevo.jpg',
        97326, 'image/jpeg', '85178_85178_BB44211_IMG_04_0000.jpg', 'moovsmart/85178_85178_BB44211_IMG_04_0000_rxnevo',
        null, '2020-09-16 21:32:35.425356000', 11, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (50, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291960/moovsmart/85178_85178_BB44211_IMG_05_0000_dmoeby.jpg',
        72973, 'image/jpeg', '85178_85178_BB44211_IMG_05_0000.jpg', 'moovsmart/85178_85178_BB44211_IMG_05_0000_dmoeby',
        null, '2020-09-16 21:32:40.081036000', 11, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (51, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600291964/moovsmart/85178_85178_BB44211_IMG_06_0000_u3cxcn.jpg',
        104276, 'image/jpeg', '85178_85178_BB44211_IMG_06_0000.jpg', 'moovsmart/85178_85178_BB44211_IMG_06_0000_u3cxcn',
        null, '2020-09-16 21:32:44.411843000', 11, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (52, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292011/moovsmart/anschauen-kaufen-einziehen_gdi7mq.jpg',
        273146, 'image/jpeg', 'anschauen-kaufen-einziehen….jpg', 'moovsmart/anschauen-kaufen-einziehen_gdi7mq', null,
        '2020-09-16 21:33:30.603756000', 12, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (53, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292022/moovsmart/anschauen-kaufen-einziehen_-k_che_aau8fs.jpg',
        93468, 'image/jpeg', 'anschauen-kaufen-einziehen…-küche.jpg',
        'moovsmart/anschauen-kaufen-einziehen_-k_che_aau8fs', null, '2020-09-16 21:33:41.504180000', 12, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (54, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292028/moovsmart/anschauen-kaufen-einziehen_-bad_ipm7vo.jpg',
        292702, 'image/jpeg', 'anschauen-kaufen-einziehen…-bad.jpg', 'moovsmart/anschauen-kaufen-einziehen_-bad_ipm7vo',
        null, '2020-09-16 21:33:47.830566000', 12, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (55, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292032/moovsmart/anschauen-kaufen-einziehen_-r_ckansicht_ayi6in.jpg',
        255593, 'image/jpeg', 'anschauen-kaufen-einziehen…-rückansicht.jpg',
        'moovsmart/anschauen-kaufen-einziehen_-r_ckansicht_ayi6in', null, '2020-09-16 21:33:52.336872000', 12,
        'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (56, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292037/moovsmart/anschauen-kaufen-einziehen_-spitzboden_guxndb.jpg',
        252769, 'image/jpeg', 'anschauen-kaufen-einziehen…-spitzboden.jpg',
        'moovsmart/anschauen-kaufen-einziehen_-spitzboden_guxndb', null, '2020-09-16 21:33:56.993535000', 12, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (57, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292043/moovsmart/anschauen-kaufen-einziehen_-wohnbereich_zd3gvv.jpg',
        104667, 'image/jpeg', 'anschauen-kaufen-einziehen…-wohnbereich.jpg',
        'moovsmart/anschauen-kaufen-einziehen_-wohnbereich_zd3gvv', null, '2020-09-16 21:34:02.490118000', 12,
        'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (58, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292103/moovsmart/221852_MAD190417_IMG_02_0000_bhofck.jpg',
        122034, 'image/jpeg', '221852_MAD190417_IMG_02_0000.jpg', 'moovsmart/221852_MAD190417_IMG_02_0000_bhofck', null,
        '2020-09-16 21:35:03.378926000', 13, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (59, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292114/moovsmart/221852_MAD190417_IMG_03_0000_qykzc7.jpg',
        117568, 'image/jpeg', '221852_MAD190417_IMG_03_0000.jpg', 'moovsmart/221852_MAD190417_IMG_03_0000_qykzc7', null,
        '2020-09-16 21:35:13.722767000', 13, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (60, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292118/moovsmart/221852_MAD190417_IMG_05_0000_qyremb.jpg',
        121111, 'image/jpeg', '221852_MAD190417_IMG_05_0000.jpg', 'moovsmart/221852_MAD190417_IMG_05_0000_qyremb', null,
        '2020-09-16 21:35:18.370313000', 13, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (61, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292123/moovsmart/221852_MAD190417_IMG_10_0000_pw8uz8.jpg',
        81437, 'image/jpeg', '221852_MAD190417_IMG_10_0000.jpg', 'moovsmart/221852_MAD190417_IMG_10_0000_pw8uz8', null,
        '2020-09-16 21:35:22.519282000', 13, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (62, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292127/moovsmart/221852_MAD190417_IMG_16_0000_voh7wy.jpg',
        83005, 'image/jpeg', '221852_MAD190417_IMG_16_0000.jpg', 'moovsmart/221852_MAD190417_IMG_16_0000_voh7wy', null,
        '2020-09-16 21:35:26.692338000', 13, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (63, 'moovsmart', 'https://res.cloudinary.com/diruti3zy/image/upload/v1600292211/moovsmart/794716142_jksblb.jpg',
        212984, 'image/jpeg', '794716142.jpg', 'moovsmart/794716142_jksblb', null, '2020-09-16 21:36:51.441434000', 14,
        'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (64, 'moovsmart', 'https://res.cloudinary.com/diruti3zy/image/upload/v1600292224/moovsmart/794716143_f7nxg3.jpg',
        247178, 'image/jpeg', '794716143.jpg', 'moovsmart/794716143_f7nxg3', null, '2020-09-16 21:37:04.456507000', 14,
        'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (65, 'moovsmart', 'https://res.cloudinary.com/diruti3zy/image/upload/v1600292231/moovsmart/794716221_p9krju.jpg',
        182532, 'image/jpeg', '794716221.jpg', 'moovsmart/794716221_p9krju', null, '2020-09-16 21:37:11.059315000', 14,
        'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (66, 'moovsmart', 'https://res.cloudinary.com/diruti3zy/image/upload/v1600292236/moovsmart/794716222_y9niis.jpg',
        254776, 'image/jpeg', '794716222.jpg', 'moovsmart/794716222_y9niis', null, '2020-09-16 21:37:15.700048000', 14,
        'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (67, 'moovsmart', 'https://res.cloudinary.com/diruti3zy/image/upload/v1600292240/moovsmart/794716226_prqd7a.jpg',
        191515, 'image/jpeg', '794716226.jpg', 'moovsmart/794716226_prqd7a', null, '2020-09-16 21:37:20.459465000', 14,
        'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (68, 'moovsmart', 'https://res.cloudinary.com/diruti3zy/image/upload/v1600292246/moovsmart/794716229_luxaug.jpg',
        159254, 'image/jpeg', '794716229.jpg', 'moovsmart/794716229_luxaug', null, '2020-09-16 21:37:26.504003000', 14,
        'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (69, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292318/moovsmart/205436_39658_IMG_01_0000_gmfnws.jpg',
        143851, 'image/jpeg', '205436_39658_IMG_01_0000.jpeg', 'moovsmart/205436_39658_IMG_01_0000_gmfnws', null,
        '2020-09-16 21:38:38.385287000', 15, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (70, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292323/moovsmart/205436_39658_IMG_02_0000_ycgopu.jpg',
        136328, 'image/jpeg', '205436_39658_IMG_02_0000.jpeg', 'moovsmart/205436_39658_IMG_02_0000_ycgopu', null,
        '2020-09-16 21:38:43.686890000', 15, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (71, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292328/moovsmart/205436_39658_IMG_03_0000_lzapm3.jpg',
        133344, 'image/jpeg', '205436_39658_IMG_03_0000.jpeg', 'moovsmart/205436_39658_IMG_03_0000_lzapm3', null,
        '2020-09-16 21:38:48.339658000', 15, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (72, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292333/moovsmart/205436_39658_IMG_09_0000_tczgqc.jpg',
        97414, 'image/jpeg', '205436_39658_IMG_09_0000.jpeg', 'moovsmart/205436_39658_IMG_09_0000_tczgqc', null,
        '2020-09-16 21:38:52.808387000', 15, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (73, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292337/moovsmart/205436_39658_IMG_11_0000_ypjw1u.jpg',
        68290, 'image/jpeg', '205436_39658_IMG_11_0000.jpeg', 'moovsmart/205436_39658_IMG_11_0000_ypjw1u', null,
        '2020-09-16 21:38:57.215130000', 15, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (74, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292409/moovsmart/58486_58486_NEB404i_IMG_00_0001_ogk1rl.jpg',
        113369, 'image/jpeg', '58486_58486_NEB404i_IMG_00_0001.jpg', 'moovsmart/58486_58486_NEB404i_IMG_00_0001_ogk1rl',
        null, '2020-09-16 21:40:09.603436000', 16, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (75, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292421/moovsmart/58486_58486_NEB404i_IMG_02_0001_cq31nn.jpg',
        67519, 'image/jpeg', '58486_58486_NEB404i_IMG_02_0001.jpg', 'moovsmart/58486_58486_NEB404i_IMG_02_0001_cq31nn',
        null, '2020-09-16 21:40:20.544928000', 16, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (76, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292470/moovsmart/58486_58486_NEB404i_IMG_03_0001_ifudzt.jpg',
        109331, 'image/jpeg', '58486_58486_NEB404i_IMG_03_0001.jpg', 'moovsmart/58486_58486_NEB404i_IMG_03_0001_ifudzt',
        null, '2020-09-16 21:41:10.144426000', 16, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (77, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292474/moovsmart/58486_58486_NEB404i_IMG_04_0001_fb8mnr.jpg',
        106801, 'image/jpeg', '58486_58486_NEB404i_IMG_04_0001.jpg', 'moovsmart/58486_58486_NEB404i_IMG_04_0001_fb8mnr',
        null, '2020-09-16 21:41:14.123023000', 16, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (78, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292478/moovsmart/58486_58486_NEB404i_IMG_11_0001_bjlulu.jpg',
        81637, 'image/jpeg', '58486_58486_NEB404i_IMG_11_0001.jpg', 'moovsmart/58486_58486_NEB404i_IMG_11_0001_bjlulu',
        null, '2020-09-16 21:41:18.180475000', 16, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (79, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292482/moovsmart/58486_58486_NEB404i_IMG_13_0001_odgwkq.jpg',
        81310, 'image/jpeg', '58486_58486_NEB404i_IMG_13_0001.jpg', 'moovsmart/58486_58486_NEB404i_IMG_13_0001_odgwkq',
        null, '2020-09-16 21:41:21.939403000', 16, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (80, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292486/moovsmart/58486_58486_NEB404i_IMG_14_0001_cxchd8.jpg',
        93032, 'image/jpeg', '58486_58486_NEB404i_IMG_14_0001.jpg', 'moovsmart/58486_58486_NEB404i_IMG_14_0001_cxchd8',
        null, '2020-09-16 21:41:25.756592000', 16, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (81, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292491/moovsmart/58486_58486_NEB404i_IMG_21_0001_jjd7d4.jpg',
        91120, 'image/jpeg', '58486_58486_NEB404i_IMG_21_0001.jpg', 'moovsmart/58486_58486_NEB404i_IMG_21_0001_jjd7d4',
        null, '2020-09-16 21:41:30.793683000', 16, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (82, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292568/moovsmart/193160_Dohany_IMG_02_0000_ebjxvg.jpg',
        78841, 'image/jpeg', '193160_Dohany_IMG_02_0000.jpg', 'moovsmart/193160_Dohany_IMG_02_0000_ebjxvg', null,
        '2020-09-16 21:42:47.578013000', 17, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (83, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292572/moovsmart/193160_Dohany_IMG_04_0000_szwklo.jpg',
        77453, 'image/jpeg', '193160_Dohany_IMG_04_0000.jpg', 'moovsmart/193160_Dohany_IMG_04_0000_szwklo', null,
        '2020-09-16 21:42:51.719498000', 17, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (84, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292576/moovsmart/193160_Dohany_IMG_05_0000_c9ivle.jpg',
        69722, 'image/jpeg', '193160_Dohany_IMG_05_0000.jpg', 'moovsmart/193160_Dohany_IMG_05_0000_c9ivle', null,
        '2020-09-16 21:42:55.755962000', 17, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (85, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292579/moovsmart/193160_Dohany_IMG_07_0000_dxc5sq.jpg',
        52557, 'image/jpeg', '193160_Dohany_IMG_07_0000.jpg', 'moovsmart/193160_Dohany_IMG_07_0000_dxc5sq', null,
        '2020-09-16 21:42:59.074270000', 17, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (86, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292583/moovsmart/193160_Dohany_IMG_10_0000_zzbe0e.jpg',
        73379, 'image/jpeg', '193160_Dohany_IMG_10_0000.jpg', 'moovsmart/193160_Dohany_IMG_10_0000_zzbe0e', null,
        '2020-09-16 21:43:02.700017000', 17, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (87, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600292587/moovsmart/193160_Dohany_IMG_11_0000_bwtzcw.jpg',
        76897, 'image/jpeg', '193160_Dohany_IMG_11_0000.jpg', 'moovsmart/193160_Dohany_IMG_11_0000_bwtzcw', null,
        '2020-09-16 21:43:06.537691000', 17, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (88, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293027/moovsmart/193160_Eotvos_IMG_19_0001_dzl0wj.jpg',
        63802, 'image/jpeg', '193160_Eotvos_IMG_19_0001.jpg', 'moovsmart/193160_Eotvos_IMG_19_0001_dzl0wj', null,
        '2020-09-16 21:50:27.287041000', 18, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (89, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293032/moovsmart/193160_Eotvos_IMG_00_0000_fcguyb.jpg',
        89987, 'image/jpeg', '193160_Eotvos_IMG_00_0000.jpg', 'moovsmart/193160_Eotvos_IMG_00_0000_fcguyb', null,
        '2020-09-16 21:50:31.839462000', 18, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (90, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293036/moovsmart/193160_Eotvos_IMG_02_0000_gorqws.jpg',
        86820, 'image/jpeg', '193160_Eotvos_IMG_02_0000.jpg', 'moovsmart/193160_Eotvos_IMG_02_0000_gorqws', null,
        '2020-09-16 21:50:36.109724000', 18, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (91, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293040/moovsmart/193160_Eotvos_IMG_03_0000_w8wi3h.jpg',
        79089, 'image/jpeg', '193160_Eotvos_IMG_03_0000.jpg', 'moovsmart/193160_Eotvos_IMG_03_0000_w8wi3h', null,
        '2020-09-16 21:50:39.986452000', 18, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (92, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293044/moovsmart/193160_Eotvos_IMG_05_0000_ra9xui.jpg',
        92478, 'image/jpeg', '193160_Eotvos_IMG_05_0000.jpg', 'moovsmart/193160_Eotvos_IMG_05_0000_ra9xui', null,
        '2020-09-16 21:50:44.000839000', 18, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (93, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293047/moovsmart/193160_Eotvos_IMG_13_0000_fvyjxv.jpg',
        92314, 'image/jpeg', '193160_Eotvos_IMG_13_0000.jpg', 'moovsmart/193160_Eotvos_IMG_13_0000_fvyjxv', null,
        '2020-09-16 21:50:47.312481000', 18, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (94, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293136/moovsmart/193160_Outca44Small_IMG_06_0000_znyidv.jpg',
        79425, 'image/jpeg', '193160_Outca44Small_IMG_06_0000.jpg', 'moovsmart/193160_Outca44Small_IMG_06_0000_znyidv',
        null, '2020-09-16 21:52:15.845975000', 19, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (95, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293141/moovsmart/193160_Outca44Small_IMG_00_0000_ma3hze.jpg',
        77909, 'image/jpeg', '193160_Outca44Small_IMG_00_0000.jpg', 'moovsmart/193160_Outca44Small_IMG_00_0000_ma3hze',
        null, '2020-09-16 21:52:20.908258000', 19, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (96, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293145/moovsmart/193160_Outca44Small_IMG_07_0000_uf0fwr.jpg',
        109349, 'image/jpeg', '193160_Outca44Small_IMG_07_0000.jpg', 'moovsmart/193160_Outca44Small_IMG_07_0000_uf0fwr',
        null, '2020-09-16 21:52:25.218248000', 19, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (97, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293149/moovsmart/193160_Outca44Small_IMG_17_0000_h3woyr.jpg',
        76181, 'image/jpeg', '193160_Outca44Small_IMG_17_0000.jpg', 'moovsmart/193160_Outca44Small_IMG_17_0000_h3woyr',
        null, '2020-09-16 21:52:28.878051000', 19, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (98, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293153/moovsmart/193160_Outca44Small_IMG_11_0000_lpj2rj.jpg',
        59374, 'image/jpeg', '193160_Outca44Small_IMG_11_0000.jpg', 'moovsmart/193160_Outca44Small_IMG_11_0000_lpj2rj',
        null, '2020-09-16 21:52:32.665598000', 19, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (99, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293239/moovsmart/193160_Outca44Big_IMG_07_0000_nlomre.jpg',
        84380, 'image/jpeg', '193160_Outca44Big_IMG_07_0000.jpg', 'moovsmart/193160_Outca44Big_IMG_07_0000_nlomre',
        null, '2020-09-16 21:53:59.522215000', 20, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (100, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293245/moovsmart/193160_Outca44Big_IMG_05_0000_jswnho.jpg',
        87684, 'image/jpeg', '193160_Outca44Big_IMG_05_0000.jpg', 'moovsmart/193160_Outca44Big_IMG_05_0000_jswnho',
        null, '2020-09-16 21:54:05.112864000', 20, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (101, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293263/moovsmart/193160_Outca44Big_IMG_06_0000_vsijqg.jpg',
        87017, 'image/jpeg', '193160_Outca44Big_IMG_06_0000.jpg', 'moovsmart/193160_Outca44Big_IMG_06_0000_vsijqg',
        null, '2020-09-16 21:54:23.454282000', 20, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (102, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293269/moovsmart/193160_Outca44Big_IMG_08_0000_i3pjyh.jpg',
        98832, 'image/jpeg', '193160_Outca44Big_IMG_08_0000.jpg', 'moovsmart/193160_Outca44Big_IMG_08_0000_i3pjyh',
        null, '2020-09-16 21:54:29.248436000', 20, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (103, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293273/moovsmart/193160_Outca44Big_IMG_09_0000_bjmttn.jpg',
        69218, 'image/jpeg', '193160_Outca44Big_IMG_09_0000.jpg', 'moovsmart/193160_Outca44Big_IMG_09_0000_bjmttn',
        null, '2020-09-16 21:54:33.203046000', 20, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (104, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293277/moovsmart/193160_Outca44Big_IMG_10_0000_fzk38f.jpg',
        80254, 'image/jpeg', '193160_Outca44Big_IMG_10_0000.jpg', 'moovsmart/193160_Outca44Big_IMG_10_0000_fzk38f',
        null, '2020-09-16 21:54:37.172110000', 20, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (105, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293281/moovsmart/193160_Outca44Big_IMG_11_0000_vbvhei.jpg',
        72602, 'image/jpeg', '193160_Outca44Big_IMG_11_0000.jpg', 'moovsmart/193160_Outca44Big_IMG_11_0000_vbvhei',
        null, '2020-09-16 21:54:41.181588000', 20, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (106, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293285/moovsmart/193160_Outca44Big_IMG_17_0000_pzrudw.jpg',
        66905, 'image/jpeg', '193160_Outca44Big_IMG_17_0000.jpg', 'moovsmart/193160_Outca44Big_IMG_17_0000_pzrudw',
        null, '2020-09-16 21:54:45.409070000', 20, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (107, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293359/moovsmart/206939_SKRVG_IMG_00_0000_dillgv.jpg',
        143096, 'image/jpeg', '206939_SKRVG_IMG_00_0000.jpg', 'moovsmart/206939_SKRVG_IMG_00_0000_dillgv', null,
        '2020-09-16 21:55:59.601565000', 21, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (108, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293365/moovsmart/206939_SKRVG_IMG_01_0000_skdy4k.jpg',
        120915, 'image/jpeg', '206939_SKRVG_IMG_01_0000.jpg', 'moovsmart/206939_SKRVG_IMG_01_0000_skdy4k', null,
        '2020-09-16 21:56:04.763664000', 21, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (109, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293369/moovsmart/206939_SKRVG_IMG_03_0000_naoaln.jpg',
        129931, 'image/jpeg', '206939_SKRVG_IMG_03_0000.jpg', 'moovsmart/206939_SKRVG_IMG_03_0000_naoaln', null,
        '2020-09-16 21:56:09.514976000', 21, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (110, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293379/moovsmart/206939_SKRVG_IMG_05_0000_maqqdw.jpg',
        116874, 'image/jpeg', '206939_SKRVG_IMG_05_0000.jpg', 'moovsmart/206939_SKRVG_IMG_05_0000_maqqdw', null,
        '2020-09-16 21:56:19.530182000', 21, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (111, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293385/moovsmart/206939_SKRVG_IMG_06_0000_zsyf75.jpg',
        160040, 'image/jpeg', '206939_SKRVG_IMG_06_0000.jpg', 'moovsmart/206939_SKRVG_IMG_06_0000_zsyf75', null,
        '2020-09-16 21:56:25.024217000', 21, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (112, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293390/moovsmart/206939_SKRVG_IMG_07_0000_ojnjad.jpg',
        111370, 'image/jpeg', '206939_SKRVG_IMG_07_0000.jpg', 'moovsmart/206939_SKRVG_IMG_07_0000_ojnjad', null,
        '2020-09-16 21:56:30.126679000', 21, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (113, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293400/moovsmart/206939_SKRVG_IMG_09_0000_rc6clz.jpg',
        94359, 'image/jpeg', '206939_SKRVG_IMG_09_0000.jpg', 'moovsmart/206939_SKRVG_IMG_09_0000_rc6clz', null,
        '2020-09-16 21:56:40.274418000', 21, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (114, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293406/moovsmart/206939_SKRVG_IMG_11_0000_yau09e.jpg',
        112740, 'image/jpeg', '206939_SKRVG_IMG_11_0000.jpg', 'moovsmart/206939_SKRVG_IMG_11_0000_yau09e', null,
        '2020-09-16 21:56:46.318631000', 21, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (115, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293413/moovsmart/206939_SKRVG_IMG_17_0000_qxeeuu.jpg',
        140811, 'image/jpeg', '206939_SKRVG_IMG_17_0000.jpg', 'moovsmart/206939_SKRVG_IMG_17_0000_qxeeuu', null,
        '2020-09-16 21:56:53.610121000', 21, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (116, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293556/moovsmart/31747765_202123020_l_ndouxm.jpg',
        161904, 'image/jpeg', '31747765_202123020_l.jpg', 'moovsmart/31747765_202123020_l_ndouxm', null,
        '2020-09-16 21:59:15.874535000', 22, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (117, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293561/moovsmart/31747765_202123021_hd_wfdv9f.jpg',
        492927, 'image/jpeg', '31747765_202123021_hd.jpg', 'moovsmart/31747765_202123021_hd_wfdv9f', null,
        '2020-09-16 21:59:21.622267000', 22, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (118, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293566/moovsmart/31747765_202123024_hd_veoeql.jpg',
        322359, 'image/jpeg', '31747765_202123024_hd.jpg', 'moovsmart/31747765_202123024_hd_veoeql', null,
        '2020-09-16 21:59:26.390773000', 22, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (119, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293570/moovsmart/31747765_202123029_hd_yulanb.jpg',
        358197, 'image/jpeg', '31747765_202123029_hd.jpg', 'moovsmart/31747765_202123029_hd_yulanb', null,
        '2020-09-16 21:59:30.426744000', 22, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (120, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293574/moovsmart/31747765_202123047_hd_jbtizg.jpg',
        282564, 'image/jpeg', '31747765_202123047_hd.jpg', 'moovsmart/31747765_202123047_hd_jbtizg', null,
        '2020-09-16 21:59:34.340286000', 22, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (121, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293658/moovsmart/30986491_199399980_hd_pzakiy.jpg',
        351998, 'image/jpeg', '30986491_199399980_hd.jpg', 'moovsmart/30986491_199399980_hd_pzakiy', null,
        '2020-09-16 22:00:58.368733000', 23, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (122, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293664/moovsmart/30986491_199399981_hd_shmooy.jpg',
        329464, 'image/jpeg', '30986491_199399981_hd.jpg', 'moovsmart/30986491_199399981_hd_shmooy', null,
        '2020-09-16 22:01:04.402347000', 23, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (123, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293669/moovsmart/30986491_199399984_hd_xkfn84.jpg',
        348264, 'image/jpeg', '30986491_199399984_hd.jpg', 'moovsmart/30986491_199399984_hd_xkfn84', null,
        '2020-09-16 22:01:08.888342000', 23, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (124, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293673/moovsmart/30986491_193963114_hd_suwatw.jpg',
        277018, 'image/jpeg', '30986491_193963114_hd.jpg', 'moovsmart/30986491_193963114_hd_suwatw', null,
        '2020-09-16 22:01:13.192940000', 23, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (125, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293678/moovsmart/30986491_193963186_hd_yfvugn.jpg',
        312577, 'image/jpeg', '30986491_193963186_hd.jpg', 'moovsmart/30986491_193963186_hd_yfvugn', null,
        '2020-09-16 22:01:17.845493000', 23, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (126, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293686/moovsmart/30986491_193963216_hd_nvb2ot.jpg',
        300281, 'image/jpeg', '30986491_193963216_hd.jpg', 'moovsmart/30986491_193963216_hd_nvb2ot', null,
        '2020-09-16 22:01:25.759529000', 23, 'ACTIVE');
INSERT INTO upload (id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                    upload_datetime, property_id, state)
VALUES (127, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1600293692/moovsmart/30986491_193963252_hd_fccrsv.jpg',
        270714, 'image/jpeg', '30986491_193963252_hd.jpg', 'moovsmart/30986491_193963252_hd_fccrsv', null,
        '2020-09-16 22:01:32.169855000', 23, 'ACTIVE');

INSERT INTO upload(id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                   upload_datetime, property_id, state)
VALUES (128, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1599513718/moovsmart/rimbun-sanctuary-apartment-fb_vi0eex.jpg',
        220007, 'image/jpeg', 'rimbun-sanctuary-apartment-fb.jpg', 'moovsmart/rimbun-sanctuary-apartment-fb_vi0eex',
        NULL, '2020-09-07 21:21:59.041060', 24, 'ACTIVE');
INSERT INTO upload(id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                   upload_datetime, property_id, state)
VALUES (129, 'moovsmart',
        'https://res.cloudinary.com/diruti3zy/image/upload/v1599513803/moovsmart/hbz-ariana-grande-pete-davidson-apartment-01-1529527439_xzi2pv.jpg',
        26763, 'image/jpeg', 'hbz-ariana-grande-pete-davidson-apartment-01-1529527439.jpg',
        'moovsmart/hbz-ariana-grande-pete-davidson-apartment-01-1529527439_xzi2pv', NULL, '2020-09-07 21:23:23.783071',
        25, 'ACTIVE');
INSERT INTO upload(id, category, file_path, file_size, media_type, original_file_name, public_id, title,
                   upload_datetime, property_id, state)
VALUES (130, 'moovsmart', 'https://res.cloudinary.com/diruti3zy/image/upload/v1599513851/moovsmart/og_b9la3u.jpg',
        672690,
        'image/jpeg', 'og.jpg', 'moovsmart/og_b9la3u', NULL, '2020-09-07 21:24:12.006836', 26, 'ACTIVE');


