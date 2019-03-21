--
-- PostgreSQL database dump
--

-- Dumped from database version 10.7
-- Dumped by pg_dump version 10.7

-- Started on 2019-03-22 01:17:54 MSK

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
-- TOC entry 2909 (class 1262 OID 16822)
-- Name: ejudge; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE ejudge WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE ejudge OWNER TO postgres;

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
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2912 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 513 (class 1247 OID 16824)
-- Name: progr_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.progr_type AS ENUM (
    'python',
    'c',
    'c++'
);


ALTER TYPE public.progr_type OWNER TO postgres;

--
-- TOC entry 516 (class 1247 OID 16832)
-- Name: role_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.role_type AS ENUM (
    'user',
    'admin'
);


ALTER TYPE public.role_type OWNER TO postgres;

--
-- TOC entry 598 (class 1247 OID 16838)
-- Name: sex_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.sex_type AS ENUM (
    'male',
    'female'
);


ALTER TYPE public.sex_type OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 16845)
-- Name: answers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.answers (
    id uuid NOT NULL,
    program_text text NOT NULL,
    programming_language public.progr_type NOT NULL
);


ALTER TABLE public.answers OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 16853)
-- Name: categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categories (
    id uuid NOT NULL,
    name text NOT NULL
);


ALTER TABLE public.categories OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16873)
-- Name: profiles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profiles (
    id uuid NOT NULL,
    name text,
    surname text,
    sex public.sex_type,
    birthday bigint
);


ALTER TABLE public.profiles OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 16879)
-- Name: status; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.status (
    id uuid NOT NULL,
    result text NOT NULL,
    error_test_id uuid,
    extended_information json
);


ALTER TABLE public.status OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16885)
-- Name: task_limits; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.task_limits (
    id uuid NOT NULL,
    memory_limit integer,
    time_limit integer,
    task_id uuid NOT NULL,
    programming_language public.progr_type NOT NULL
);


ALTER TABLE public.task_limits OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16888)
-- Name: tasks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tasks (
    id uuid NOT NULL,
    name text NOT NULL,
    discription text,
    report_permission text NOT NULL,
    category_id uuid NOT NULL
);


ALTER TABLE public.tasks OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16894)
-- Name: tests; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tests (
    id uuid NOT NULL,
    input_data text NOT NULL,
    output_data text NOT NULL,
    task_id uuid NOT NULL
);


ALTER TABLE public.tests OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16900)
-- Name: user_solutions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_solutions (
    id uuid NOT NULL,
    user_id uuid NOT NULL,
    task_id uuid NOT NULL,
    answer_id uuid NOT NULL,
    status_id uuid NOT NULL
);


ALTER TABLE public.user_solutions OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16903)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id uuid NOT NULL,
    username text NOT NULL,
    password_hash text NOT NULL,
    email text NOT NULL,
    token text NOT NULL,
    profile_id uuid NOT NULL,
    role public.role_type NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 2757 (class 2606 OID 16910)
-- Name: answers answers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.answers
    ADD CONSTRAINT answers_pkey PRIMARY KEY (id);


--
-- TOC entry 2755 (class 2606 OID 16911)
-- Name: profiles birthday_check; Type: CHECK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE public.profiles
    ADD CONSTRAINT birthday_check CHECK ((birthday > 0)) NOT VALID;


--
-- TOC entry 2759 (class 2606 OID 16913)
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


--
-- TOC entry 2761 (class 2606 OID 16915)
-- Name: profiles profiles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profiles
    ADD CONSTRAINT profiles_pkey PRIMARY KEY (id);


--
-- TOC entry 2763 (class 2606 OID 16917)
-- Name: status status_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_pkey PRIMARY KEY (id);


--
-- TOC entry 2765 (class 2606 OID 16919)
-- Name: task_limits task_limits_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task_limits
    ADD CONSTRAINT task_limits_pkey PRIMARY KEY (id);


--
-- TOC entry 2767 (class 2606 OID 16921)
-- Name: tasks tasks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (id);


--
-- TOC entry 2769 (class 2606 OID 16923)
-- Name: tests tests_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tests
    ADD CONSTRAINT tests_pkey PRIMARY KEY (id);


--
-- TOC entry 2771 (class 2606 OID 16925)
-- Name: user_solutions user_solutions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_solutions
    ADD CONSTRAINT user_solutions_pkey PRIMARY KEY (id);


--
-- TOC entry 2773 (class 2606 OID 16927)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2778 (class 2606 OID 16928)
-- Name: user_solutions answer_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_solutions
    ADD CONSTRAINT answer_id_fk FOREIGN KEY (answer_id) REFERENCES public.answers(id);


--
-- TOC entry 2776 (class 2606 OID 16933)
-- Name: tasks category_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT category_id_fk FOREIGN KEY (category_id) REFERENCES public.categories(id);


--
-- TOC entry 2774 (class 2606 OID 16938)
-- Name: status error_test_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT error_test_id_fk FOREIGN KEY (error_test_id) REFERENCES public.tests(id);


--
-- TOC entry 2782 (class 2606 OID 16943)
-- Name: users profile_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT profile_id_fk FOREIGN KEY (profile_id) REFERENCES public.profiles(id);


--
-- TOC entry 2779 (class 2606 OID 16948)
-- Name: user_solutions status_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_solutions
    ADD CONSTRAINT status_id FOREIGN KEY (status_id) REFERENCES public.status(id);


--
-- TOC entry 2777 (class 2606 OID 16953)
-- Name: tests task_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tests
    ADD CONSTRAINT task_id FOREIGN KEY (task_id) REFERENCES public.tasks(id);


--
-- TOC entry 2775 (class 2606 OID 16958)
-- Name: task_limits task_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task_limits
    ADD CONSTRAINT task_id_fk FOREIGN KEY (task_id) REFERENCES public.tests(id);


--
-- TOC entry 2780 (class 2606 OID 16963)
-- Name: user_solutions task_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_solutions
    ADD CONSTRAINT task_id_fk FOREIGN KEY (task_id) REFERENCES public.tasks(id);


--
-- TOC entry 2781 (class 2606 OID 16968)
-- Name: user_solutions user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_solutions
    ADD CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 2911 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2019-03-22 01:17:54 MSK

--
-- PostgreSQL database dump complete
--

