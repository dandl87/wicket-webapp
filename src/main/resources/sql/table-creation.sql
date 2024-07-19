CREATE TABLE section (ID INT NOT NULL, name VARCHAR(150) UNIQUE,  PRIMARY KEY (ID));
CREATE TABLE tech (ID INT NOT NULL, section_id INT, name VARCHAR(150) UNIQUE,  PRIMARY KEY (ID), constraint fk_section 
foreign 
key (section_id) references section(ID));
CREATE TABLE article (ID INT NOT NULL, tech_id INT, name VARCHAR(150), content text,  PRIMARY KEY (ID), constraint fk_tech foreign 
key (tech_id) references tech(ID));


CREATE SEQUENCE seqArticle INCREMENT 1 START 1 owned by public.article.ID;
CREATE SEQUENCE seqSection INCREMENT 1 START 1 owned by public.section.ID;
CREATE SEQUENCE seqTech INCREMENT 1 START 1 owned by public.tech.ID;



INSERT INTO public.section(id, "name")VALUES(nextval('seqSection'), 'Dev');
INSERT INTO public.section(id, "name")VALUES(nextval('seqSection'), 'Frameworks');
INSERT INTO public.section(id, "name")VALUES(nextval('seqSection'), 'Persistence');
INSERT INTO public.section(id, "name")VALUES(nextval('seqSection'), 'Infrastructure');
  

INSERT INTO public.tech(id, section_id, "name")VALUES(nextval('seqTech'), 1, 'intellij');
INSERT INTO public.tech(id, section_id, "name")VALUES(nextval('seqTech'), 1, 'java');
INSERT INTO public.tech(id, section_id, "name")VALUES(nextval('seqTech'), 1, 'git');

INSERT INTO public.tech(id, section_id, "name")VALUES(nextval('seqTech'), 2, 'spring');
INSERT INTO public.tech(id, section_id, "name")VALUES(nextval('seqTech'), 2, 'wicket');

INSERT INTO public.tech(id, section_id, "name")VALUES(nextval('seqTech'), 3, 'openjpa');
INSERT INTO public.tech(id, section_id, "name")VALUES(nextval('seqTech'), 3, 'postgresql');

INSERT INTO public.tech(id, section_id, "name")VALUES(nextval('seqTech'), 4, 'linux');
INSERT INTO public.tech(id, section_id, "name")VALUES(nextval('seqTech'), 4, 'docker');
INSERT INTO public.tech(id, section_id, "name")VALUES(nextval('seqTech'), 4, 'maven');


INSERT INTO public.article(id, tech_id, "name", "content")VALUES(nextval('seqArticle'), 1, 'Run/debug configuration templates',
'All run/debug configurations are based on templates, which implement the startup logic, define the list of parameters and their default values. The list of available templates is predefined in the installation and can only be extended via plugins. However, you can edit default parameter values in each template to streamline the setup of new run/debug configurations.
');
INSERT INTO public.article(id, tech_id, "name", "content")VALUES(nextval('seqArticle'), 8, 'Facade',
'Usato da wicket. I model sono essezialmente interfacce facade che permettono ai componenti di accedere e modificare i loro dati senza conoscere alcun dettaglio su come questi sono gestiti o vengono memorizzati');
