--
-- Structure for table mydashboard_notification
--
DROP TABLE IF EXISTS mydashboard_notification;
CREATE TABLE mydashboard_notification (
	id_notification INT AUTO_INCREMENT,
	is_read SMALLINT DEFAULT 0 NOT NULL,
    id_user VARCHAR(255) DEFAULT '' NOT NULLL,
	object VARCHAR(255) DEFAULT '' NOT NULL,
	message LONG VARCHAR,
	date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	sender VARCHAR(255) DEFAULT '' NOT NULL,
	PRIMARY KEY (id_notification)
);
