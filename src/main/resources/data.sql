insert into car (manufacturer, model, year_of_manufacture, engine_capacity, vehicle_mileage, price_per_day, status, discount, registration_number) values('Daewoo', 'Lanos', 1998, 1.5, 140123, 21.0, 'Avalible', 0, 'RZE1212');
insert into car (manufacturer, model, year_of_manufacture, engine_capacity, vehicle_mileage, price_per_day, status, discount, registration_number) values('Ferrari', 'Enzo', 1996, 4.2, 189000, 111.23, 'Avalible', 0, 'RZE1236');
insert into car (manufacturer, model, year_of_manufacture, engine_capacity, vehicle_mileage, price_per_day, status, discount, registration_number) values('Corvette', 'Stingray', 1980, 3.6, 211555, 97.45, 'Avalible', 0, 'RZE9912'); 

insert into client (first_name, last_name, e_mail, city, street, house_number, discount) values('Andrzej', 'Kowal', 'akowal@o2.pl', 'Rzeszow', 'Mickiewicza', '21', 0); 
insert into client (first_name, last_name, e_mail, city, street, house_number, discount) values('Szymek', 'Trembecki', 'strembecki@o2.pl', 'Rzeszow', 'Wesola', '5', 0); 
insert into client (first_name, last_name, e_mail, city, street, house_number, discount) values('Alfons', 'Kot', 'akot@o2.pl', 'Rzeszow', 'Zielona', '34', 0); 

insert into seller (first_name, last_name, login, e_mail, password, city, street, house_number, rights) values('Maciej', 'Wiertnik', 'wiertel', 'mwiertnik@o2.pl', 'mw', 'Rzeszow', 'Sienkiwicza', '14', 'Admin'); 
insert into seller (first_name, last_name, login, e_mail, password, city, street, house_number, rights) values('Dominik', 'Chochlik', 'chochel', 'dchochlik@o2.pl', 'dc', 'Rzeszow', 'Marynarska', '99', 'Manager'); 
insert into seller (first_name, last_name, login, e_mail, password, city, street, house_number, rights) values('Janusz', 'Tranowski', 'tranel', 'jtranowski@o2.pl', 'jt', 'Rzeszow', 'Wojenna', '19', 'Employee');

insert into hire (client_id, car_id, seller_id, hire_date, hire_end_date, status, price_for_hire, default_interest) values(1, 1, 1, '2012-12-13 14:54:30', '2012-12-18 14:54:30', 'Paid', 105.0, 0);  
insert into hire (client_id, car_id, seller_id, hire_date, hire_end_date, status, price_for_hire, default_interest) values(2, 2, 2, '2012-12-13 14:54:30', '2012-12-18 14:54:30', 'Paid', 556.15, 0); 
insert into hire (client_id, car_id, seller_id, hire_date, hire_end_date, status, price_for_hire, default_interest) values(3, 3, 3, '2012-12-13 14:54:30', '2012-12-18 14:54:30', 'Paid', 487.25, 0);   