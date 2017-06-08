insert ignore into car (id, manufacturer, model, year_of_manufacture, engine_capacity, vehicle_mileage, price_per_day, price_per_day_after_discount, status, discount, registration_number) values(1, 'Daewoo', 'Lanos', 1998, 1.5, 140123, 21.0, 21.0, 'Avalible', 0, 'RZE1212');
insert ignore into car (id, manufacturer, model, year_of_manufacture, engine_capacity, vehicle_mileage, price_per_day, price_per_day_after_discount, status, discount, registration_number) values(2, 'Ferrari', 'Enzo', 1996, 4.2, 189000, 111.23, 111.23, 'Avalible', 0, 'RZE1236');
insert ignore into car (id, manufacturer, model, year_of_manufacture, engine_capacity, vehicle_mileage, price_per_day, price_per_day_after_discount, status, discount, registration_number) values(3, 'Corvette', 'Stingray', 1980, 3.6, 211555, 97.45, 97.45, 'Avalible', 0, 'RZE9912'); 

insert ignore into client (id, first_name, last_name, e_mail, city, street, house_number, discount) values(1, 'Andrzej', 'Kowal', 'akowal@o2.pl', 'Rzeszow', 'Mickiewicza', '21', 0); 
insert ignore into client (id, first_name, last_name, e_mail, city, street, house_number, discount) values(2, 'Szymek', 'Trembecki', 'strembecki@o2.pl', 'Rzeszow', 'Wesola', '5', 0); 
insert ignore into client (id, first_name, last_name, e_mail, city, street, house_number, discount) values(3, 'Alfons', 'Kot', 'akot@o2.pl', 'Rzeszow', 'Zielona', '34', 0); 

insert ignore into seller (id, first_name, last_name, login, e_mail, password, city, street, house_number, rights) values(1, 'Maciej', 'Wiertnik', 'wiertel', 'mwiertnik@o2.pl', 'mw', 'Rzeszow', 'Sienkiewicza', '14', 'Admin'); 
insert ignore into seller (id, first_name, last_name, login, e_mail, password, city, street, house_number, rights) values(2, 'Dominik', 'Chochlik', 'chochel', 'dchochlik@o2.pl', 'dc', 'Rzeszow', 'Marynarska', '99', 'Manager'); 
insert ignore into seller (id, first_name, last_name, login, e_mail, password, city, street, house_number, rights) values(3, 'Janusz', 'Tranowski', 'tranel', 'jtranowski@o2.pl', 'jt', 'Rzeszow', 'Wojenna', '19', 'Employee');

insert ignore into hire (id, client_id, car_id, seller_id, hire_date, hire_end_date, status, price_for_hire, default_interest) values(1, 1, 1, 1, '2012-12-13 14:54', '2012-12-18 14:54', 'Paid', 105.0, 0);  
insert ignore into hire (id, client_id, car_id, seller_id, hire_date, hire_end_date, status, price_for_hire, default_interest) values(2, 2, 2, 2, '2012-12-13 14:54', '2012-12-18 14:54', 'Paid', 556.15, 0); 
insert ignore into hire (id, client_id, car_id, seller_id, hire_date, hire_end_date, status, price_for_hire, default_interest) values(3, 3, 3, 3, '2012-12-13 14:54', '2012-12-18 14:54', 'Paid', 487.25, 0);    