CREATE SCHEMA IF NOT EXISTS `ds0`;
CREATE SCHEMA IF NOT EXISTS `ds1`;

DROP TABLE IF EXISTS `ds0`.`t_order0`;
CREATE TABLE IF NOT EXISTS `ds0`.`t_order0` (`order_id` INT PRIMARY KEY, `user_id` INT NOT NULL, `config_id` INT NOT NULL, `remark` VARCHAR(50));
DROP TABLE IF EXISTS `ds0`.`t_order1`;
CREATE TABLE IF NOT EXISTS `ds0`.`t_order1` (`order_id` INT PRIMARY KEY, `user_id` INT NOT NULL, `config_id` INT NOT NULL, `remark` VARCHAR(50));
DROP TABLE IF EXISTS `ds0`.`t_order_item0`;
CREATE TABLE IF NOT EXISTS `ds0`.`t_order_item0` (`item_id` BIGINT PRIMARY KEY AUTO_INCREMENT, `order_id` BIGINT NOT NULL, `remark` VARCHAR(50) NOT NULL);
DROP TABLE IF EXISTS `ds0`.`t_order_item1`;
CREATE TABLE IF NOT EXISTS `ds0`.`t_order_item1` (`item_id` BIGINT PRIMARY KEY AUTO_INCREMENT, `order_id` BIGINT NOT NULL, `remark` VARCHAR(50) NOT NULL);
DROP TABLE IF EXISTS `ds0`.`t_config`;
CREATE TABLE IF NOT EXISTS `ds0`.`t_config` (`id` INT PRIMARY KEY, `remark` VARCHAR(50));

DROP TABLE IF EXISTS `ds1`.`t_order0`;
CREATE TABLE IF NOT EXISTS `ds1`.`t_order0` (`order_id` INT PRIMARY KEY, `user_id` INT NOT NULL, `config_id` INT NOT NULL,`remark` VARCHAR(50));
DROP TABLE IF EXISTS `ds1`.`t_order1`;
CREATE TABLE IF NOT EXISTS `ds1`.`t_order1` (`order_id` INT PRIMARY KEY, `user_id` INT NOT NULL, `config_id` INT NOT NULL,`remark` VARCHAR(50));
DROP TABLE IF EXISTS `ds1`.`t_order_item0`;
CREATE TABLE IF NOT EXISTS `ds1`.`t_order_item0` (`item_id` BIGINT PRIMARY KEY AUTO_INCREMENT, `order_id` BIGINT NOT NULL, `remark` VARCHAR(50) NOT NULL);
DROP TABLE IF EXISTS `ds1`.`t_order_item1`;
CREATE TABLE IF NOT EXISTS `ds1`.`t_order_item1` (`item_id` BIGINT PRIMARY KEY AUTO_INCREMENT, `order_id` BIGINT NOT NULL, `remark` VARCHAR(50) NOT NULL);
DROP TABLE IF EXISTS `ds1`.`t_config`;
CREATE TABLE IF NOT EXISTS `ds1`.`t_config` (`id` INT PRIMARY KEY, `remark` VARCHAR(50));