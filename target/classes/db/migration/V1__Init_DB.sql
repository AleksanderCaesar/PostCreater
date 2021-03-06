DROP TABLE IF EXISTS users;

CREATE TABLE public.users
(
    user_id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    code character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    is_moderator integer,
    name character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    photo character varying(255) COLLATE pg_catalog."default",
    reg_time timestamp without time zone,
    CONSTRAINT users_pkey PRIMARY KEY (user_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

DROP TABLE IF EXISTS tags;

CREATE TABLE tags
(
    tag_id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT tags_pkey PRIMARY KEY (tag_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

DROP TABLE IF EXISTS posts;

CREATE TABLE posts
(
    post_id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    is_active integer,
    moderation_status character varying(255) COLLATE pg_catalog."default",
    moderator_id integer,
    text character varying(255) COLLATE pg_catalog."default",
    "time" timestamp without time zone,
    title character varying(255) COLLATE pg_catalog."default",
    view_count integer,
    user_id integer,
    CONSTRAINT posts_pkey PRIMARY KEY (post_id),
    CONSTRAINT fk5lidm6cqbc7u4xhqpxm898qme FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

DROP TABLE IF EXISTS tag2post;

CREATE TABLE tag2post
(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    post_id integer,
    tag_id integer,
    CONSTRAINT tag2post_pkey PRIMARY KEY (id),
    CONSTRAINT fkjou6suf2w810t2u3l96uasw3r FOREIGN KEY (tag_id)
        REFERENCES public.tags (tag_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkpjoedhh4h917xf25el3odq20i FOREIGN KEY (post_id)
        REFERENCES public.posts (post_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

DROP TABLE IF EXISTS post_votes;

CREATE TABLE post_votes
(
    votes_id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    "time" timestamp without time zone,
    value integer,
    post_id integer,
    user_id integer,
    CONSTRAINT post_votes_pkey PRIMARY KEY (votes_id),
    CONSTRAINT fk9jh5u17tmu1g7xnlxa77ilo3u FOREIGN KEY (post_id)
        REFERENCES public.posts (post_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk9q09ho9p8fmo6rcysnci8rocc FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

DROP TABLE IF EXISTS post_comments;

CREATE TABLE post_comments
(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    parent_id integer,
    "time" timestamp without time zone,
    title character varying(255) COLLATE pg_catalog."default",
    post_id integer,
    user_id integer,
    CONSTRAINT post_comments_pkey PRIMARY KEY (id),
    CONSTRAINT fkaawaqxjs3br8dw5v90w7uu514 FOREIGN KEY (post_id)
        REFERENCES public.posts (post_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fksnxoecngu89u3fh4wdrgf0f2g FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

DROP TABLE IF EXISTS global_settings;

CREATE TABLE global_settings
(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    code character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    value character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT global_settings_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

DROP TABLE IF EXISTS captcha_codes;

CREATE TABLE captcha_codes
(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    code character varying(255) COLLATE pg_catalog."default",
    secret_code character varying(255) COLLATE pg_catalog."default",
    "time" timestamp without time zone,
    CONSTRAINT captcha_codes_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
