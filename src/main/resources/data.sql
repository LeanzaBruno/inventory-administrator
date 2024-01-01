DROP FROM article;
DROP FROM vat;
DROP FROM category;
DROP FROM article_category;


INSERT INTO vat (rate, description) VALUES 
(27, 'IVA GENERAL: telecomunicaciones, agua, electricidad y gas'),
(21, 'IVA Reducido I: mayoría de bienes y servicios'),
(10.5, 'IVA Reducido II: servicios médicos, frutas, verduras, carnes, transporte público y taxi'),
(2.5, 'IVA Superreducido: importación, periódicos, revistas impresas o digitales'),
(0.0, 'No IVA');

INSERT INTO category (name) VALUES
('lácteos'),
('esenciales'),
('comestibles'),
('golosinas'),
('juguetería'),
('limpieza');

INSERT INTO article (title, brand, description, purchase_gross_price, vat_rate, gain_percentage, stock) VALUES
('Yogurt La serenisima 500 ml', 'La Serenisima', 'Yogurt la serenisma', 500, 0, 30, 10),
('KitKat chocolate', 'KitKat', 'Kitkat de cocholate 200gr', 300, 27, 50, 30),
('Lavandina para lavar', 'Lavandina', 'Lavandina para el hogar', 630, 2.5, 40, 20),
('Leche La Serenisima 1L', 'La Serenisima', 'chele', 500, 0, 20, 50);

INSERT INTO article_category (article_code, category_id) VALUES 
(1, 1),
(1, 2),
(2, 4),
(3, 2),
(3, 6),
(4, 1),
(4, 2);
