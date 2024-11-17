INSERT INTO beauty_items (name, price, description, duration)
VALUES
('Corte Feminino', 80.00, 'Corte moderno e personalizado para cada cliente, feito com técnicas atualizadas.', 60)
ON CONFLICT (id) DO NOTHING;

INSERT INTO beauty_items (name, price, description, duration)
VALUES
('Corte Masculino', 50.00, 'Corte masculino com estilo e precisão, para valorizar o visual.', 45)
ON CONFLICT (id) DO NOTHING;

INSERT INTO beauty_items (name, price, description, duration)
VALUES
('Coloração Completa', 150.00, 'Coloração feita por especialistas com produtos de alta qualidade.', 90)
ON CONFLICT (id) DO NOTHING;

INSERT INTO beauty_items (name, price, description, duration)
VALUES
('Escova e Penteado', 60.00, 'Escova modeladora e penteado sofisticado para ocasiões especiais.', 75)
ON CONFLICT (id) DO NOTHING;

INSERT INTO beauty_items (name, price, description, duration)
VALUES
('Manicure e Pedicure', 40.00, 'Cuidado completo para suas mãos e pés, com esmaltação impecável.', 60)
ON CONFLICT (id) DO NOTHING;

INSERT INTO beauty_items (name, price, description, duration)
VALUES
('Tratamento Capilar', 120.00, 'Tratamentos capilares para hidratação, reconstrução e nutrição dos fios.', 90)
ON CONFLICT (id) DO NOTHING;



INSERT INTO users (name, email, password, about, instagram)
VALUES
('Maria Silva', 'maria.silva@example.com', '$2a$10$hashsenha', 'Especialista em cortes femininos.', '@maria.silva')
ON CONFLICT (email) DO NOTHING;

INSERT INTO users (name, email, password, about, instagram)
VALUES

('Antonia Joana', 'antonia.joana@example.com', '$2a$10$hashsenha', 'Especialista em coloração e penteados.', '@antonia.joana')
ON CONFLICT (email) DO NOTHING;

INSERT INTO users (name, email, password, about, instagram)
VALUES
('Joana Pereira', 'joana.pereira@example.com', '$2a$10$hashsenha', 'Manicure e pedicure.', '@joana.pereira')
ON CONFLICT (email) DO NOTHING;



INSERT INTO user_authority_junction (user_id, authority)
SELECT id, 'ROLE_EMPLOYEE'
FROM users
WHERE email IN ('maria.silva@example.com', 'carlos.souza@example.com', 'joana.pereira@example.com')
ON CONFLICT (user_id, authority) DO NOTHING;