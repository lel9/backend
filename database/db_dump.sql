--
-- PostgreSQL database dump
--

-- Dumped from database version 10.7
-- Dumped by pg_dump version 10.7

-- Started on 2019-03-29 22:50:34 MSK

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
-- TOC entry 2927 (class 1262 OID 16822)
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
-- TOC entry 2930 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 642 (class 1247 OID 17212)
-- Name: progr_type; Type: TYPE; Schema: public; Owner: -
--

CREATE TYPE public.progr_type AS ENUM (
    'python',
    'c',
    'cpp'
);


--
-- TOC entry 515 (class 1247 OID 16832)
-- Name: role_type; Type: TYPE; Schema: public; Owner: -
--

CREATE TYPE public.role_type AS ENUM (
    'user',
    'admin'
);


--
-- TOC entry 597 (class 1247 OID 16838)
-- Name: sex_type; Type: TYPE; Schema: public; Owner: -
--

CREATE TYPE public.sex_type AS ENUM (
    'male',
    'female'
);


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 16845)
-- Name: answers; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.answers (
    id uuid NOT NULL,
    program_text text NOT NULL,
    programming_language public.progr_type NOT NULL
);


--
-- TOC entry 197 (class 1259 OID 16853)
-- Name: categories; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.categories (
    id uuid NOT NULL,
    name text NOT NULL
);


--
-- TOC entry 206 (class 1259 OID 16987)
-- Name: email_tokens; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.email_tokens (
    id uuid NOT NULL,
    token text NOT NULL,
    creation_date bigint NOT NULL,
    user_id uuid NOT NULL
);


--
-- TOC entry 198 (class 1259 OID 16873)
-- Name: profiles; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.profiles (
    id uuid NOT NULL,
    name text,
    surname text,
    sex public.sex_type,
    birthday bigint
);


--
-- TOC entry 205 (class 1259 OID 16974)
-- Name: refresh_tokens; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.refresh_tokens (
    id uuid NOT NULL,
    token text NOT NULL,
    user_id uuid NOT NULL
);


--
-- TOC entry 199 (class 1259 OID 16879)
-- Name: status; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.status (
    id uuid NOT NULL,
    result text NOT NULL,
    error_test_id uuid,
    extended_information json
);


--
-- TOC entry 200 (class 1259 OID 16885)
-- Name: task_limits; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.task_limits (
    id uuid NOT NULL,
    memory_limit integer,
    time_limit integer,
    task_id uuid NOT NULL,
    programming_language public.progr_type NOT NULL
);


--
-- TOC entry 201 (class 1259 OID 16888)
-- Name: tasks; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tasks (
    id uuid NOT NULL,
    name text NOT NULL,
    description text,
    report_permission text NOT NULL,
    category_id uuid NOT NULL,
    owner_id uuid NOT NULL
);


--
-- TOC entry 202 (class 1259 OID 16894)
-- Name: tests; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tests (
    id uuid NOT NULL,
    input_data text NOT NULL,
    output_data text NOT NULL,
    task_id uuid NOT NULL
);


--
-- TOC entry 203 (class 1259 OID 16900)
-- Name: user_solutions; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.user_solutions (
    id uuid NOT NULL,
    user_id uuid NOT NULL,
    task_id uuid NOT NULL,
    answer_id uuid NOT NULL,
    status_id uuid NOT NULL
);


--
-- TOC entry 204 (class 1259 OID 16903)
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users (
    id uuid NOT NULL,
    username text NOT NULL,
    password_hash text NOT NULL,
    email text NOT NULL,
    profile_id uuid NOT NULL,
    role public.role_type NOT NULL,
    enabled boolean NOT NULL
);


--
-- TOC entry 2768 (class 2606 OID 16910)
-- Name: answers answers_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.answers
    ADD CONSTRAINT answers_pkey PRIMARY KEY (id);


--
-- TOC entry 2765 (class 2606 OID 16911)
-- Name: profiles birthday_check; Type: CHECK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE public.profiles
    ADD CONSTRAINT birthday_check CHECK ((birthday > 0)) NOT VALID;


--
-- TOC entry 2770 (class 2606 OID 16913)
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


--
-- TOC entry 2766 (class 2606 OID 17000)
-- Name: email_tokens creation_date_check; Type: CHECK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE public.email_tokens
    ADD CONSTRAINT creation_date_check CHECK ((creation_date > 0)) NOT VALID;


--
-- TOC entry 2788 (class 2606 OID 16994)
-- Name: email_tokens email_tokens_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.email_tokens
    ADD CONSTRAINT email_tokens_pkey PRIMARY KEY (id);


--
-- TOC entry 2772 (class 2606 OID 16915)
-- Name: profiles profiles_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.profiles
    ADD CONSTRAINT profiles_pkey PRIMARY KEY (id);


--
-- TOC entry 2786 (class 2606 OID 16981)
-- Name: refresh_tokens refresh_tokens_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.refresh_tokens
    ADD CONSTRAINT refresh_tokens_pkey PRIMARY KEY (id);


--
-- TOC entry 2774 (class 2606 OID 16917)
-- Name: status status_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_pkey PRIMARY KEY (id);


--
-- TOC entry 2776 (class 2606 OID 16919)
-- Name: task_limits task_limits_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.task_limits
    ADD CONSTRAINT task_limits_pkey PRIMARY KEY (id);


--
-- TOC entry 2778 (class 2606 OID 16921)
-- Name: tasks tasks_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (id);


--
-- TOC entry 2780 (class 2606 OID 16923)
-- Name: tests tests_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tests
    ADD CONSTRAINT tests_pkey PRIMARY KEY (id);


--
-- TOC entry 2782 (class 2606 OID 16925)
-- Name: user_solutions user_solutions_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_solutions
    ADD CONSTRAINT user_solutions_pkey PRIMARY KEY (id);


--
-- TOC entry 2784 (class 2606 OID 16927)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2794 (class 2606 OID 16928)
-- Name: user_solutions answer_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_solutions
    ADD CONSTRAINT answer_id_fk FOREIGN KEY (answer_id) REFERENCES public.answers(id);


--
-- TOC entry 2791 (class 2606 OID 16933)
-- Name: tasks category_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT category_id_fk FOREIGN KEY (category_id) REFERENCES public.categories(id);


--
-- TOC entry 2789 (class 2606 OID 16938)
-- Name: status error_test_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT error_test_id_fk FOREIGN KEY (error_test_id) REFERENCES public.tests(id);


--
-- TOC entry 2792 (class 2606 OID 17219)
-- Name: tasks owner_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT owner_id_fk FOREIGN KEY (owner_id) REFERENCES public.users(id);


--
-- TOC entry 2798 (class 2606 OID 16943)
-- Name: users profile_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT profile_id_fk FOREIGN KEY (profile_id) REFERENCES public.profiles(id);


--
-- TOC entry 2795 (class 2606 OID 16948)
-- Name: user_solutions status_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_solutions
    ADD CONSTRAINT status_id FOREIGN KEY (status_id) REFERENCES public.status(id);


--
-- TOC entry 2793 (class 2606 OID 16953)
-- Name: tests task_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tests
    ADD CONSTRAINT task_id FOREIGN KEY (task_id) REFERENCES public.tasks(id);


--
-- TOC entry 2796 (class 2606 OID 16963)
-- Name: user_solutions task_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_solutions
    ADD CONSTRAINT task_id_fk FOREIGN KEY (task_id) REFERENCES public.tasks(id);


--
-- TOC entry 2790 (class 2606 OID 17206)
-- Name: task_limits task_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.task_limits
    ADD CONSTRAINT task_id_fk FOREIGN KEY (task_id) REFERENCES public.tasks(id);


--
-- TOC entry 2797 (class 2606 OID 16968)
-- Name: user_solutions user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_solutions
    ADD CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 2799 (class 2606 OID 16982)
-- Name: refresh_tokens user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.refresh_tokens
    ADD CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 2800 (class 2606 OID 16995)
-- Name: email_tokens user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.email_tokens
    ADD CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 2929 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: -
--

GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2019-03-29 22:50:34 MSK

--
-- PostgreSQL database dump complete
--

