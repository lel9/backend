--
-- PostgreSQL database dump
--

-- Dumped from database version 10.7
-- Dumped by pg_dump version 10.7

-- Started on 2019-03-22 00:32:15 MSK

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE ejudge;
--
-- TOC entry 2954 (class 1262 OID 16406)
-- Name: ejudge; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE ejudge WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


\connect ejudge

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12964)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2957 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 646 (class 1247 OID 16720)
-- Name: progr_type; Type: TYPE; Schema: public; Owner: -
--

CREATE TYPE public.progr_type AS ENUM (
    'python',
    'c',
    'c++'
);


--
-- TOC entry 643 (class 1247 OID 16714)
-- Name: role_type; Type: TYPE; Schema: public; Owner: -
--

CREATE TYPE public.role_type AS ENUM (
    'user',
    'admin'
);


--
-- TOC entry 640 (class 1247 OID 16708)
-- Name: sex_type; Type: TYPE; Schema: public; Owner: -
--

CREATE TYPE public.sex_type AS ENUM (
    'male',
    'female'
);


--
-- TOC entry 206 (class 1259 OID 16577)
-- Name: id_seq_answers; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.id_seq_answers
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 204 (class 1259 OID 16453)
-- Name: answers; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.answers (
    id bigint DEFAULT nextval('public.id_seq_answers'::regclass) NOT NULL,
    program_text text NOT NULL,
    programming_language public.progr_type NOT NULL
);


--
-- TOC entry 209 (class 1259 OID 16633)
-- Name: id_seq_categories; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.id_seq_categories
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 199 (class 1259 OID 16429)
-- Name: categories; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.categories (
    id bigint DEFAULT nextval('public.id_seq_categories'::regclass) NOT NULL,
    name text NOT NULL
);


--
-- TOC entry 205 (class 1259 OID 16550)
-- Name: id_seq_profiles; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.id_seq_profiles
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 213 (class 1259 OID 16690)
-- Name: id_seq_status; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.id_seq_status
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 212 (class 1259 OID 16681)
-- Name: id_seq_task_limits; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.id_seq_task_limits
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 208 (class 1259 OID 16611)
-- Name: id_seq_tasks; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.id_seq_tasks
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 211 (class 1259 OID 16659)
-- Name: id_seq_tests; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.id_seq_tests
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 210 (class 1259 OID 16650)
-- Name: id_seq_user_solutions; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.id_seq_user_solutions
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 207 (class 1259 OID 16594)
-- Name: id_seq_users; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.id_seq_users
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 197 (class 1259 OID 16413)
-- Name: profiles; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.profiles (
    id bigint DEFAULT nextval('public.id_seq_profiles'::regclass) NOT NULL,
    name text,
    surname text,
    sex public.sex_type,
    birthday bigint
);


--
-- TOC entry 203 (class 1259 OID 16447)
-- Name: status; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.status (
    id bigint DEFAULT nextval('public.id_seq_status'::regclass) NOT NULL,
    result text NOT NULL,
    error_test_id bigint,
    extended_information json
);


--
-- TOC entry 202 (class 1259 OID 16444)
-- Name: task_limits; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.task_limits (
    id bigint DEFAULT nextval('public.id_seq_task_limits'::regclass) NOT NULL,
    memory_limit integer,
    time_limit integer,
    task_id bigint NOT NULL,
    programming_language public.progr_type NOT NULL
);


--
-- TOC entry 198 (class 1259 OID 16423)
-- Name: tasks; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tasks (
    id bigint DEFAULT nextval('public.id_seq_tasks'::regclass) NOT NULL,
    name text NOT NULL,
    discription text,
    report_permission text NOT NULL,
    category_id bigint NOT NULL
);


--
-- TOC entry 201 (class 1259 OID 16438)
-- Name: tests; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tests (
    id bigint DEFAULT nextval('public.id_seq_tests'::regclass) NOT NULL,
    input_data text NOT NULL,
    output_data text NOT NULL,
    task_id bigint NOT NULL
);


--
-- TOC entry 200 (class 1259 OID 16435)
-- Name: user_solutions; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.user_solutions (
    id bigint DEFAULT nextval('public.id_seq_user_solutions'::regclass) NOT NULL,
    user_id bigint NOT NULL,
    task_id bigint NOT NULL,
    answer_id bigint NOT NULL,
    status_id bigint NOT NULL
);


--
-- TOC entry 196 (class 1259 OID 16407)
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users (
    id bigint DEFAULT nextval('public.id_seq_users'::regclass) NOT NULL,
    username text NOT NULL,
    password_hash text NOT NULL,
    email text NOT NULL,
    token text NOT NULL,
    profile_id bigint NOT NULL,
    role public.role_type NOT NULL
);


--
-- TOC entry 2939 (class 0 OID 16453)
-- Dependencies: 204
-- Data for Name: answers; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2934 (class 0 OID 16429)
-- Dependencies: 199
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2932 (class 0 OID 16413)
-- Dependencies: 197
-- Data for Name: profiles; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2938 (class 0 OID 16447)
-- Dependencies: 203
-- Data for Name: status; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2937 (class 0 OID 16444)
-- Dependencies: 202
-- Data for Name: task_limits; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2933 (class 0 OID 16423)
-- Dependencies: 198
-- Data for Name: tasks; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2936 (class 0 OID 16438)
-- Dependencies: 201
-- Data for Name: tests; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2935 (class 0 OID 16435)
-- Dependencies: 200
-- Data for Name: user_solutions; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2931 (class 0 OID 16407)
-- Dependencies: 196
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2958 (class 0 OID 0)
-- Dependencies: 206
-- Name: id_seq_answers; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.id_seq_answers', 1, false);


--
-- TOC entry 2959 (class 0 OID 0)
-- Dependencies: 209
-- Name: id_seq_categories; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.id_seq_categories', 1, false);


--
-- TOC entry 2960 (class 0 OID 0)
-- Dependencies: 205
-- Name: id_seq_profiles; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.id_seq_profiles', 1, true);


--
-- TOC entry 2961 (class 0 OID 0)
-- Dependencies: 213
-- Name: id_seq_status; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.id_seq_status', 1, false);


--
-- TOC entry 2962 (class 0 OID 0)
-- Dependencies: 212
-- Name: id_seq_task_limits; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.id_seq_task_limits', 1, false);


--
-- TOC entry 2963 (class 0 OID 0)
-- Dependencies: 208
-- Name: id_seq_tasks; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.id_seq_tasks', 1, false);


--
-- TOC entry 2964 (class 0 OID 0)
-- Dependencies: 211
-- Name: id_seq_tests; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.id_seq_tests', 1, false);


--
-- TOC entry 2965 (class 0 OID 0)
-- Dependencies: 210
-- Name: id_seq_user_solutions; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.id_seq_user_solutions', 1, false);


--
-- TOC entry 2966 (class 0 OID 0)
-- Dependencies: 207
-- Name: id_seq_users; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.id_seq_users', 1, false);


--
-- TOC entry 2800 (class 2606 OID 16580)
-- Name: answers answers_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.answers
    ADD CONSTRAINT answers_pkey PRIMARY KEY (id);


--
-- TOC entry 2775 (class 2606 OID 16821)
-- Name: profiles birthday_check; Type: CHECK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE public.profiles
    ADD CONSTRAINT birthday_check CHECK ((birthday > 0)) NOT VALID;


--
-- TOC entry 2790 (class 2606 OID 16636)
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


--
-- TOC entry 2786 (class 2606 OID 16553)
-- Name: profiles profiles_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.profiles
    ADD CONSTRAINT profiles_pkey PRIMARY KEY (id);


--
-- TOC entry 2798 (class 2606 OID 16693)
-- Name: status status_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_pkey PRIMARY KEY (id);


--
-- TOC entry 2796 (class 2606 OID 16684)
-- Name: task_limits task_limits_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.task_limits
    ADD CONSTRAINT task_limits_pkey PRIMARY KEY (id);


--
-- TOC entry 2788 (class 2606 OID 16614)
-- Name: tasks tasks_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (id);


--
-- TOC entry 2794 (class 2606 OID 16662)
-- Name: tests tests_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tests
    ADD CONSTRAINT tests_pkey PRIMARY KEY (id);


--
-- TOC entry 2792 (class 2606 OID 16653)
-- Name: user_solutions user_solutions_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_solutions
    ADD CONSTRAINT user_solutions_pkey PRIMARY KEY (id);


--
-- TOC entry 2784 (class 2606 OID 16597)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2805 (class 2606 OID 16791)
-- Name: user_solutions answer_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_solutions
    ADD CONSTRAINT answer_id_fk FOREIGN KEY (answer_id) REFERENCES public.answers(id);


--
-- TOC entry 2802 (class 2606 OID 16749)
-- Name: tasks category_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT category_id_fk FOREIGN KEY (category_id) REFERENCES public.categories(id);


--
-- TOC entry 2809 (class 2606 OID 16728)
-- Name: status error_test_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT error_test_id_fk FOREIGN KEY (error_test_id) REFERENCES public.tests(id);


--
-- TOC entry 2801 (class 2606 OID 16809)
-- Name: users profile_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT profile_id_fk FOREIGN KEY (profile_id) REFERENCES public.profiles(id);


--
-- TOC entry 2806 (class 2606 OID 16800)
-- Name: user_solutions status_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_solutions
    ADD CONSTRAINT status_id FOREIGN KEY (status_id) REFERENCES public.status(id);


--
-- TOC entry 2807 (class 2606 OID 16761)
-- Name: tests task_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tests
    ADD CONSTRAINT task_id FOREIGN KEY (task_id) REFERENCES public.tasks(id);


--
-- TOC entry 2808 (class 2606 OID 16740)
-- Name: task_limits task_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.task_limits
    ADD CONSTRAINT task_id_fk FOREIGN KEY (task_id) REFERENCES public.tests(id);


--
-- TOC entry 2804 (class 2606 OID 16782)
-- Name: user_solutions task_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_solutions
    ADD CONSTRAINT task_id_fk FOREIGN KEY (task_id) REFERENCES public.tasks(id);


--
-- TOC entry 2803 (class 2606 OID 16773)
-- Name: user_solutions user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_solutions
    ADD CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 2956 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: -
--

GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2019-03-22 00:32:15 MSK

--
-- PostgreSQL database dump complete
--

