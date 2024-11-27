CREATE TABLE historico_estoque (
    id VARCHAR(255) DEFAULT gen_random_uuid() PRIMARY KEY,
    posto_id VARCHAR(255) NOT NULL,
    data_upload TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    funcionario_id VARCHAR(255) NOT NULL,
    CONSTRAINT fk_posto FOREIGN KEY (posto_id) REFERENCES posto (id) ON DELETE CASCADE,
    CONSTRAINT fk_funcionario FOREIGN KEY (funcionario_id) REFERENCES usuario (id)
);