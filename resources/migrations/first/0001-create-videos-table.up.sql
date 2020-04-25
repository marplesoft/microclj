create table videos (
    id serial primary key, 
    owner_id text,
    name text,
    description text,
    transcoding_status text,
    view_count int default 0
);