create TABLE type (
  id serial primary key,
  name varchar(50)
);

create TABLE rule (
  id serial primary key,
  name varchar(50)
);

create TABLE accident (
  id serial primary key,
  name varchar(50),
  text varchar(500),
  address varchar(200),
  type_id int references type (id)
);

create TABLE accident_rule (
  accident_id int references accident (id),
  rule_id int references rule (id)
);