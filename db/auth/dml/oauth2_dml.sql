USE sc_auth;

INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES ('admin', NULL, '$2a$10$gpzdDca0wu6zDEaPXdnh7.GIMWwRnLvrxabNVqrbKKWWtBihznBXG', 'read,write', 'client_credentials,authorization_code,password,refresh_token', 'http://baidu.com', NULL, 7200, 108000, NULL, NULL);

insert into oauth_client_details values('orderApp','gateway-server,order-server,admin-server','$2a$10$gpzdDca0wu6zDEaPXdnh7.GIMWwRnLvrxabNVqrbKKWWtBihznBXG','read,write','password,authorization_code,refresh_token',null,null,3600,3600,null,null);
insert into oauth_client_details values('orderService','order-server','$2a$10$gpzdDca0wu6zDEaPXdnh7.GIMWwRnLvrxabNVqrbKKWWtBihznBXG','read,write','password',null,null,3600,null,null,null);
insert into oauth_client_details values('bookService','book-server','$2a$10$gpzdDca0wu6zDEaPXdnh7.GIMWwRnLvrxabNVqrbKKWWtBihznBXG','read,write','password',null,null,3600,null,null,null);
insert into oauth_client_details values('personService','book-server,person-server','$2a$10$gpzdDca0wu6zDEaPXdnh7.GIMWwRnLvrxabNVqrbKKWWtBihznBXG','read,write','password',null,null,3600,null,null,null);
insert into oauth_client_details values('gateway','','$2a$10$gpzdDca0wu6zDEaPXdnh7.GIMWwRnLvrxabNVqrbKKWWtBihznBXG','read,write','password',null,null,3600,null,null,null);
