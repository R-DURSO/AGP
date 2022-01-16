CREATE TABLE transport (
    id_transport varchar(50) NOT NULL,
    prix SMALLINT,
    confort SMALLINT,
    vitesse SMALLINT,
    PRIMARY KEY (id_transport)
);

CREATE TABLE destination (
    id_destination varchar(50) NOT NULL,
    nom_destination varchar(50),
    PRIMARY KEY (id_destination)
);

CREATE TABLE site_touristique (
    id_site varchar(50) NOT NULL,
    nom_site varchar(50),
    type_lieux varchar(50),
    niveau_effort smallint,
    duree_activite smallint,
    prix smallint,
    position_site POINT NOT NULL,
    id_destination varchar(50),
    PRIMARY KEY (id_site),
    FOREIGN KEY (id_destination) REFERENCES destination(id_destination)
);

CREATE TABLE ligne_transport (
    id_ligne varchar(50) NOT NULL,
    nom_ligne varchar(50),
    id_transport varchar(50),
    PRIMARY KEY (id_ligne),
    FOREIGN KEY (id_transport) REFERENCES transport(id_transport)
);

CREATE TABLE station (
    id_station varchar(50) NOT NULL,
    position POINT NOT NULL,
    nom_station varchar(50),
    id_ligne varchar(50),
    id_destination varchar(50),
    PRIMARY KEY (id_station),
    FOREIGN KEY (id_destination) REFERENCES destination(id_destination),
    FOREIGN KEY (id_ligne) REFERENCES ligne_transport(id_ligne)
);

CREATE TABLE hotel (
    id_hotel varchar(50) NOT NULL,
    nom_hotel varchar(50),
    position POINT NOT NULL,
    prix smallint,
    confort smallint,
    id_destination varchar(50),
    PRIMARY KEY (id_hotel),
    FOREIGN KEY (id_destination) REFERENCES destination(id_destination)
);


