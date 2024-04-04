ALTER TABLE medicos
ADD telefone VARCHAR(20);

-- Preencher os valores para a nova coluna telefone
UPDATE medicos SET telefone = 'valor_padrao' WHERE telefone IS NULL;

-- Adicionar a restrição NOT NULL
ALTER TABLE medicos
ALTER COLUMN telefone SET NOT NULL;