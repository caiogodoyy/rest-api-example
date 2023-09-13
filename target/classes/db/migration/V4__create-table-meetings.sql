create table meetings(
    id bigint not null auto_increment,
    teacher_id bigint not null,
    student_id bigint not null,
    date_time datetime not null,
    primary key(id),
    constraint fk_meetings_teacher_id foreign key(teacher_id) references teachers(id),
    constraint fk_meetings_student_id foreign key(student_id) references students(id)
);