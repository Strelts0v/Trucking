CREATE SCHEMA IF NOT EXISTS `itechart_group_db` DEFAULT CHARACTER SET utf8mb4 ;
USE `itechart_group_db` ;

-- -----------------------------------------------------
-- Table `itechart_group_db`.`car_park`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itechart_group_db`.`car_park` (
  `id` BINARY(16) NOT NULL,
  `version` BIGINT NULL,
  `car_name` VARCHAR(45) NULL,
  `car_number` VARCHAR(45) NULL,
  `car_type` ENUM('C', 'F', 'T') NULL DEFAULT NULL,
  `car_consumption` INT NULL COMMENT 'the using up of a diesel',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `itechart_group_db`.`items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itechart_group_db`.`items` (
  `id` BINARY(16) NOT NULL,
  `version` BIGINT NULL,
  `item_name` VARCHAR(45) NULL,
  `item_price` INT NULL,
  `item_type` ENUM('C', 'V') NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `itechart_group_db`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itechart_group_db`.`users` (
  `id` BINARY(16) NOT NULL,
  `version` BIGINT NULL,
  `user_firstname` VARCHAR(45) NULL,
  `user_lastname` VARCHAR(45) NULL,
  `user_middlename` VARCHAR(45) NULL,
  `user_birthday` DATE NULL DEFAULT NULL,
  `user_email` VARCHAR(45) NULL,
  `user_city` VARCHAR(45) NULL,
  `user_street` VARCHAR(45) NULL,
  `user_house` VARCHAR(45) NULL,
  `user_apartment` INT NULL,
  `user_role` ENUM('S', 'A', 'M', 'D', 'DR', 'O') NULL DEFAULT NULL,
  `user_login` VARCHAR(45) NOT NULL,
  `user_password` VARCHAR(45) NULL,
  `user_passport` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `itechart_group_db`.`warehouses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itechart_group_db`.`warehouses` (
  `id` BINARY(16) NOT NULL,
  `version` BIGINT NULL,
  `warehouse_name` VARCHAR(45) NULL,
  `warehouse_country` VARCHAR(45) NULL,
  `warehouse_city` VARCHAR(45) NULL,
  `warehouse_street` VARCHAR(45) NULL,
  `warehouse_house` VARCHAR(45) NULL,
  `warehouse_lat` VARCHAR(45) NULL,
  `warehouse_lng` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `itechart_group_db`.`clients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itechart_group_db`.`clients` (
  `id` BINARY(16) NOT NULL,
  `version` BIGINT NULL,
  `client_name` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `itechart_group_db`.`waybills`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itechart_group_db`.`waybills` (
  `id` BINARY(16) NOT NULL,
  `version` BIGINT NULL,
  `waybill_departure_date` DATE NULL DEFAULT NULL,
  `waybill_status` ENUM('S', 'C') NOT NULL DEFAULT 'S',
  `warehouses_id_from` BINARY(16) NOT NULL,
  `warehouses_id_to` BINARY(16) NOT NULL,
  `car_park_id` BINARY(16) NOT NULL,
  `clients_id` BINARY(16) NOT NULL,
  `users_id_driver` BINARY(16) NOT NULL,
  `waybills_issuedate` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_waybills_departure_date1_idx` (`waybill_departure_date` ASC),
  INDEX `fk_waybills_status1_idx` (`waybill_status` ASC),
  INDEX `fk_waybills_warehouses1_idx` (`warehouses_id_from` ASC),
  INDEX `fk_waybills_warehouses2_idx` (`warehouses_id_to` ASC),
  INDEX `fk_waybills_driver1_idx` (`users_id_driver` ASC),
  CONSTRAINT `fk_waybills_car_park`
  FOREIGN KEY (`car_park_id`)
  REFERENCES `itechart_group_db`.`car_park` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_waybills_clients1`
  FOREIGN KEY (`clients_id`)
  REFERENCES `itechart_group_db`.`clients` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_waybills_warehouses1`
  FOREIGN KEY (`warehouses_id_from`)
  REFERENCES `itechart_group_db`.`warehouses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_waybills_warehouses2`
  FOREIGN KEY (`warehouses_id_to`)
  REFERENCES `itechart_group_db`.`warehouses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `itechart_group_db`.`invoices`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itechart_group_db`.`invoices` (
  `id` BINARY(16) NOT NULL,
  `version` BIGINT NULL,
  `invoice_checkdate` DATE NULL DEFAULT NULL,
  `invoice_status` ENUM('I', 'C', 'D') NULL DEFAULT NULL,
  `invoice_issuedate` DATE NOT NULL,
  `users_id_creator` BINARY(16) NOT NULL,
  `users_id_inspector` BINARY(16) NULL,
  `waybills_id` BINARY(16) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_invoices_checkdate1_idx` (`invoice_checkdate` ASC),
  INDEX `fk_invoices_status1_idx` (`invoice_status` ASC),
  INDEX `fk_invoices_issuedate1_idx` (`invoice_issuedate` ASC),
  INDEX `fk_invoices_inspector1_idx` (`users_id_inspector` ASC),
  CONSTRAINT `fk_invoices_users_creator1`
  FOREIGN KEY (`users_id_creator`)
  REFERENCES `itechart_group_db`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoices_users_inspector1`
  FOREIGN KEY (`users_id_inspector`)
  REFERENCES `itechart_group_db`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoices_waybills1`
  FOREIGN KEY (`waybills_id`)
  REFERENCES `itechart_group_db`.`waybills` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `itechart_group_db`.`item_consignments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itechart_group_db`.`item_consignments` (
  `invoices_id` BINARY(16) NOT NULL,
  `items_id` BINARY(16) NOT NULL,
  `item_amount` INT NULL,
  `item_status` ENUM('R', 'C', 'D') NULL DEFAULT NULL,
  PRIMARY KEY (`invoice_id`, `item_id`),
  INDEX `fk_item_consignments_items1_idx` (`items_id` ASC),
  INDEX `fk_item_consignments_invoices1_idx` (`invoices_id` ASC),
  CONSTRAINT `fk_item_consignments_items1`
  FOREIGN KEY (`items_id`)
  REFERENCES `itechart_group_db`.`items` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_consignments_invoices1`
  FOREIGN KEY (`invoices_id`)
  REFERENCES `itechart_group_db`.`invoices` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `itechart_group_db`.`checkpoints`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itechart_group_db`.`checkpoints` (
  `id` BINARY(16) NOT NULL,
  `version` BIGINT NULL,
  `checkpoint_name` VARCHAR(45) NULL,
  `checkpoint_lat` VARCHAR(45) NULL,
  `checkpoint_lng` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `itechart_group_db`.`waybills_has_checkpoints`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itechart_group_db`.`waybills_has_checkpoints` (
  `waybills_id` BINARY(16) NOT NULL,
  `checkpoints_id` BINARY(16) NOT NULL,
  `checkpoint_datetime` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`waybills_id`, `checkpoints_id`),
  INDEX `fk_waybills_has_checkpoints_checkpoints1_idx` (`checkpoints_id` ASC),
  INDEX `fk_waybills_has_checkpoints_waybills1_idx` (`waybills_id` ASC),
  CONSTRAINT `fk_waybills_has_checkpoints_waybills1`
  FOREIGN KEY (`waybills_id`)
  REFERENCES `itechart_group_db`.`waybills` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_waybills_has_checkpoints_checkpoints1`
  FOREIGN KEY (`checkpoints_id`)
  REFERENCES `itechart_group_db`.`checkpoints` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `itechart_group_db`.`act_of_loss`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itechart_group_db`.`act_of_loss` (
  `invoices_id` BINARY(16) NOT NULL,
  `items_id` BINARY(16) NOT NULL,
  `item_amount` INT NOT NULL,
  `act_date_issue` DATE NOT NULL,
  PRIMARY KEY (`invoices_id`, `items_id`),
  INDEX `fk_act_of_loss_invoices1_idx` (`invoices_id` ASC),
  INDEX `fk_act_of_loss_items1_idx` (`items_id` ASC),
  CONSTRAINT `fk_act_of_loss_invoices1`
  FOREIGN KEY (`invoices_id`)
  REFERENCES `itechart_group_db`.`invoices` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_act_of_loss_items1`
  FOREIGN KEY (`items_id`)
  REFERENCES `itechart_group_db`.`items` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;