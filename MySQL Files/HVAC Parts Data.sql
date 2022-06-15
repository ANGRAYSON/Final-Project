-- Technicians--
INSERT INTO technicians(first_name, last_name, active_status) VALUES ('JOE','SMITH','ACTIVE');
INSERT INTO technicians(first_name, last_name, active_status) VALUES ('CARRY', 'BARTOLETT', 'ACTIVE');
INSERT INTO technicians(first_name, last_name, active_status) VALUES ('CHRIS', 'CLAYTON', 'ACTIVE');

-- Parts electric--
INSERT INTO parts(part_num, part_name, type) VALUES ('81W33', 'BLOWER MOTOR', 'ELECTRIC');
INSERT INTO parts(part_num, part_name, type) VALUES ('16C81', 'CONTROL BOARD', 'ELECTRIC');
INSERT INTO parts(part_num, part_name, type) VALUES ('42J32', 'TRANSFORMER', 'ELECTRIC');
INSERT INTO parts(part_num, part_name, type) VALUES ('89M80', 'CAPACITOR 45+5', 'ELECTRIC');
INSERT INTO parts(part_num, part_name, type) VALUES ('95M55', 'CONTACTOR', 'ELECTRIC');
INSERT INTO parts(part_num, part_name, type) VALUES ('33W12', 'BLOWER MOTOR', 'ELECTRIC');
INSERT INTO parts(part_num, part_name, type) VALUES ('14C95', 'CONTORL BOARD', 'ELECTRIC');
INSERT INTO parts(part_num, part_name, type) VALUES ('11X75', 'CONDENSOR FAN MOTOR', 'ELECTRIC');
INSERT INTO parts(part_num, part_name, type) VALUES ('43W49', 'CONDENSOR FAN MOTOR', 'ELECTRIC');
INSERT INTO parts(part_num, part_name, type) VALUES ('89M71', 'CAPACITOR 30+5', 'ELECTRIC');

-- Parts Cooling--
INSERT INTO parts(part_num, part_name, type) VALUES ('95M48', 'FILTER DRIER', 'COOLING');
INSERT INTO parts(part_num, part_name, type) VALUES ('93W01', 'HI PRESSURE SWITCH', 'COOLING');
INSERT INTO parts(part_num, part_name, type) VALUES ('93M04', 'CRANKCASE HEATER', 'COOLING');
INSERT INTO parts(part_num, part_name, type) VALUES ('11Y33', 'LO PRESSURE SWITCH', 'COOLING');
INSERT INTO parts(part_num, part_name, type) VALUES ('80N84', 'R-410A 25LB', 'COOLING');
INSERT INTO parts(part_num, part_name, type) VALUES ('37X95', 'NO RINSE EVAP CLEANER', 'COOLING');
INSERT INTO parts(part_num, part_name, type) VALUES ('71M15', 'TXV 2.5/3 TON', 'COOLING');
INSERT INTO parts(part_num, part_name, type) VALUES ('21D23', 'FREEZE STAT', 'COOLING');
INSERT INTO parts(part_num, part_name, type) VALUES ('Y9622', 'UV DYE', 'COOLING');

-- Parts Heating--
INSERT INTO parts(part_num, part_name, type) VALUES ('69W88', 'BURNER ASSEMBLY', 'HEATING');
INSERT INTO parts(part_num, part_name, type) VALUES ('70W16', 'IGNITOR', 'HEATING');
INSERT INTO parts(part_num, part_name, type) VALUES ('69W43', 'FLAME SENSOR', 'HEATING');
INSERT INTO parts(part_num, part_name, type) VALUES ('17F50', 'COLLECTOR BOX', 'HEATING');
INSERT INTO parts(part_num, part_name, type) VALUES ('74W26', 'GAS VALVE', 'HEATING');
INSERT INTO parts(part_num, part_name, type) VALUES ('75W15', 'LIMIT SWITCH', 'HEATING');
INSERT INTO parts(part_num, part_name, type) VALUES ('11U67', 'PRESSURE SWITCH', 'HEATING');
INSERT INTO parts(part_num, part_name, type) VALUES ('71W49', 'LIMIT SWITCH', 'HEATING');
INSERT INTO parts(part_num, part_name, type) VALUES ('93W13', 'INDUCED DRAFT ASSEMBLY', 'HEATING');
INSERT INTO parts(part_num, part_name, type) VALUES ('11K49', 'LP KIT', 'HEATING');

-- Locations--
INSERT INTO locations(location_num, location_name) VALUES (1,'SHOP');
INSERT INTO locations(location_num, location_name) VALUES (2,'VAN 1');
INSERT INTO locations(location_num, location_name) VALUES (3,'VAN 2');
INSERT INTO locations(location_num, location_name) VALUES (4,'TRUCK 1');

-- Inventory shop--
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('11K49', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('93W13', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('71W49', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('11U67', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('75W15', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('74W26', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('17F50', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('69W43', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('70W16', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('69W88', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('Y9622', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('21D23', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('71M15', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('37X95', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('80N84', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('11Y33', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('93M04', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('93W01', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('95M48', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('89M71', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('43W49', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('11X75', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('14C95', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('33W12', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('95M55', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('89M80', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('42J32', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('16C81', 1, 10);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('81W33', 1, 10);

-- Inventory van 1 --
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('Y9622', 2, 5);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('89M80', 2, 5);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('21D23', 2, 5);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('17F50', 2, 5);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('11Y33', 2, 5);

-- Inventory van 2--
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('11Y33', 3, 5);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('33W12', 3, 5);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('71M15', 3, 5);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('14C95', 3, 5);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('81W33', 3, 5);

-- Inventory Truck 1--
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('93W13', 4, 5);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('81W33', 4, 5);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('71M15', 4, 5);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('43W49', 4, 5);
INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES ('42J32', 4, 5);



