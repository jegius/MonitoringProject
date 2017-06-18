--
-- PostgreSQL database dump
--
Drop TABLE IF EXISTS users CASCADE;
Drop TABLE IF EXISTS searches CASCADE;
Drop TABLE IF EXISTS searchitem CASCADE;


--
-- Name: users; Type: TABLE; Schema: public
--

CREATE TABLE users (
  user_id bigint NOT NULL PRIMARY KEY,
  login character varying,
  password character varying(1000) NOT NULL
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

--
-- Name: categories; Type: TABLE; Schema: public
--

CREATE TABLE search (
    search_id bigint NOT NULL PRIMARY KEY,
    user_id bigint,
    product_quantity bigint,
    creation_date date NOT NULL
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
ALTER TABLE search ALTER COLUMN search_id SET DEFAULT nextval('search_search_id_seq');
ALTER SEQUENCE search_search_id_seq OWNED BY search.search_id;

--
-- Name: categories; Type: TABLE; Schema: public
--

CREATE TABLE search_item (
  search_item_id bigint NOT NULL PRIMARY KEY,
  search_id bigint,
  product_price bigint,
  product_name character varying
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
ALTER TABLE search_item ALTER COLUMN search_item_id SET DEFAULT nextval('search_search_item_seq');
ALTER SEQUENCE search_search_item_seq OWNED BY search_item.search_item_id;



ALTER TABLE ONLY users
  ADD CONSTRAINT "	FK_users_users" FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE ONLY search
  ADD CONSTRAINT "	FK_users_search" FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE ONLY search_item
  ADD CONSTRAINT "	FK_search_search_item" FOREIGN KEY (search_id) REFERENCES search(search_id) ON DELETE CASCADE ON UPDATE CASCADE;
