CREATE TABLE autoservice_st2.news
(
    id   uuid primary key default gen_random_uuid(),
    title TEXT NOT NULL,
    content TEXT NOT NULL,
    img_url TEXT NOT NULL
);

