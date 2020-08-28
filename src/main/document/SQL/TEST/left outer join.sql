        select m.email, name, picture, role, phone, car_id, car_name, car_model, car_scs_type
        from member_view m left outer join car_view c
        on m.email = c.email
        where m.email = #{email}