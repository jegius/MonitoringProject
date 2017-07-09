--
-- PostgreSQL database dump
--
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS search CASCADE;
DROP TABLE IF EXISTS search_item CASCADE;
DROP TABLE IF EXISTS roles CASCADE;

--
-- Name: users; Type: TABLE; Schema: public
--

CREATE TABLE users (
  user_id       BIGINT                  NOT NULL PRIMARY KEY,
  role_id       BIGINT,
  user_login    CHARACTER(200),
  user_password CHARACTER VARYING(1000) NOT NULL
);

--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public
--

CREATE SEQUENCE users_user_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

ALTER TABLE users
  ALTER COLUMN user_id SET DEFAULT nextval('users_user_id_seq');
ALTER SEQUENCE users_user_id_seq OWNED BY users.user_id;
--
-- Name: categories; Type: TABLE; Schema: public
--

CREATE TABLE search (
  search_id           BIGINT         NOT NULL PRIMARY KEY,
  user_id             BIGINT,
  search_status       BIGINT,
  file_path           CHARACTER(200),
  file_directory_path CHARACTER(200),
  file_original_name  CHARACTER(200),
  product_quantity    BIGINT,
  creation_date       CHARACTER(200) NOT NULL,
  last_search_date    CHARACTER(200)
);

--
-- Name: categories_category_id_seq; Type: SEQUENCE; Schema: public
--

CREATE SEQUENCE search_search_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

--
-- Name: categories_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public
--
ALTER TABLE search
  ALTER COLUMN search_id SET DEFAULT nextval('search_search_id_seq');
ALTER SEQUENCE search_search_id_seq OWNED BY search.search_id;

--
-- Name: categories; Type: TABLE; Schema: public
--

CREATE TABLE search_item (
  search_item_id BIGINT NOT NULL PRIMARY KEY,
  search_id      BIGINT,
  cell_id        BIGINT,
  product_price  BIGINT,
  product_name   CHARACTER(200)
);

--
-- Name: categories_category_id_seq; Type: SEQUENCE; Schema: public
--

CREATE SEQUENCE search_search_item_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

--
-- Name: categories_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public
--
ALTER TABLE search_item
  ALTER COLUMN search_item_id SET DEFAULT nextval('search_search_item_seq');
ALTER SEQUENCE search_search_item_seq OWNED BY search_item.search_item_id;

--
-- Name: roles; Type: TABLE; Schema: public
--

CREATE TABLE roles (
  role_id BIGINT                 NOT NULL PRIMARY KEY,
  name    CHARACTER VARYING(100) NOT NULL
);

--
-- Name: roles_role_id_seq; Type: SEQUENCE; Schema: public
--

CREATE SEQUENCE roles_role_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

--
-- Name: roles_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public
--
ALTER TABLE roles
  ALTER COLUMN role_id SET DEFAULT nextval('roles_role_id_seq');
ALTER SEQUENCE roles_role_id_seq OWNED BY roles.role_id;


ALTER TABLE ONLY users
  ADD CONSTRAINT "	FK_users_users" FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE ONLY search
  ADD CONSTRAINT "	FK_users_search" FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE ONLY search_item
  ADD CONSTRAINT "	FK_search_search_item" FOREIGN KEY (search_id) REFERENCES search (search_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER SEQUENCE users_user_id_seq RESTART WITH 1;
ALTER SEQUENCE search_search_id_seq RESTART WITH 1;
ALTER SEQUENCE search_search_item_seq RESTART WITH 1;
ALTER SEQUENCE roles_role_id_seq RESTART WITH 1;

INSERT INTO roles (name)
VALUES ('SuperUser'),
  ('User');

INSERT INTO users (user_id, role_id, user_login, user_password)
VALUES (1, 1, 'jegius', '20503573445431994242781524265713587176');
