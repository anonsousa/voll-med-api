ALTER TABLE medicos
ADD ativo BOOLEAN;

-- Preencher os valores para a nova coluna ativo
UPDATE medicos SET ativo = true WHERE ativo IS NULL;

-- Adicionar a restrição NOT NULL
ALTER TABLE medicos
ALTER COLUMN ativo SET NOT NULL;
