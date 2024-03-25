-- SEQUENCE: public.groups_id_seq
-- DROP SEQUENCE IF EXISTS public.groups_id_seq;
CREATE SEQUENCE IF NOT EXISTS public.groups_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

-- Table: public.groups
-- DROP TABLE IF EXISTS public.groups;
CREATE TABLE IF NOT EXISTS public.groups
(
    id bigint NOT NULL DEFAULT nextval('groups_id_seq'::regclass),
    title text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    created_at timestamp without time zone,
    created_by bigint,
    updated_at timestamp without time zone,
    updated_by bigint,
    deleted_at timestamp without time zone,
    deleted_by bigint,
    CONSTRAINT groups_pkey PRIMARY KEY (id),
    CONSTRAINT groups_title_key UNIQUE (title)
    );

-- Table: public.instructors
-- DROP TABLE IF EXISTS public.instructors;
CREATE TABLE IF NOT EXISTS public.instructors
(
    id BIGSERIAL PRIMARY KEY,
    first_name text COLLATE pg_catalog."default" NOT NULL,
    last_name text COLLATE pg_catalog."default" NOT NULL,
    email text COLLATE pg_catalog."default" NOT NULL,
    bio text COLLATE pg_catalog."default",
    profile_picture text COLLATE pg_catalog."default",
    created_at timestamp without time zone,
    created_by bigint,
    updated_at timestamp without time zone,
    updated_by bigint,
    deleted_at timestamp without time zone,
    deleted_by bigint
    );

-- Table: public.instructors_groups
-- DROP TABLE IF EXISTS public.instructors_groups;
CREATE TABLE IF NOT EXISTS public.instructors_groups
(
    id BIGSERIAL PRIMARY KEY,
    instructor_id bigint,
    group_id bigint,
    created_at timestamp without time zone,
    created_by bigint,
    updated_at timestamp without time zone,
    updated_by bigint,
    deleted_at timestamp without time zone,
    deleted_by bigint,
    CONSTRAINT instructors_groups_instructor_id_group_id_key UNIQUE (instructor_id, group_id)
    );

-- Table: public.students
-- DROP TABLE IF EXISTS public.students;
CREATE TABLE IF NOT EXISTS public.students
(
    id BIGSERIAL PRIMARY KEY,
    first_name text COLLATE pg_catalog."default" NOT NULL,
    last_name text COLLATE pg_catalog."default",
    email text COLLATE pg_catalog."default" NOT NULL,
    phone_number text COLLATE pg_catalog."default",
    address text COLLATE pg_catalog."default",
    gender character(1) COLLATE pg_catalog."default",
    created_at timestamp without time zone,
    created_by bigint,
    updated_at timestamp without time zone,
    updated_by bigint,
    deleted_at timestamp without time zone,
    deleted_by bigint,
    profile_picture bytea
    );

-- Table: public.students_groups
-- DROP TABLE IF EXISTS public.students_groups;
CREATE TABLE IF NOT EXISTS public.students_groups
(
    id BIGSERIAL PRIMARY KEY,
    student_id bigint,
    group_id bigint,
    created_at timestamp without time zone,
    created_by bigint,
    updated_at timestamp without time zone,
    updated_by bigint,
    deleted_at timestamp without time zone,
    deleted_by bigint,
    CONSTRAINT students_groups_student_id_group_id_key UNIQUE (student_id, group_id)
    );

-- Table: public.trainings
-- DROP TABLE IF EXISTS public.trainings;
CREATE TABLE IF NOT EXISTS public.trainings
(
    id BIGSERIAL PRIMARY KEY,
    title text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default" NOT NULL,
    price numeric,
    cover text COLLATE pg_catalog."default",
    status bigint,
    created_at timestamp without time zone,
    created_by bigint,
    updated_at timestamp without time zone,
    updated_by bigint,
    deleted_at timestamp without time zone,
    deleted_by bigint
    );

-- Table: public.subjects
-- DROP TABLE IF EXISTS public.subjects;
CREATE TABLE IF NOT EXISTS public.subjects
(
    id BIGSERIAL PRIMARY KEY,
    title text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    icon text COLLATE pg_catalog."default",
    training_id bigint,
    created_at timestamp without time zone,
    created_by bigint,
    updated_at timestamp without time zone,
    updated_by bigint,
    deleted_at timestamp without time zone,
    deleted_by bigint,
    CONSTRAINT subjects_training_id_fkey FOREIGN KEY (training_id)
    REFERENCES public.trainings (id) MATCH SIMPLE
                         ON UPDATE NO ACTION
                         ON DELETE NO ACTION
    );
