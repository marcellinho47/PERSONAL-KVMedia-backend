USE KVMEDIA
GO

ALTER TABLE 
	Municipios
ADD CONSTRAINT 
	CK_Municipios_UF 
CHECK (DESC_UF IN ('AC', 'AL', 'AM', 'AP', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA', 'MG', 'MS', 'MT', 'PA', 'PB', 'PE', 'PI', 'PR', 'RJ', 'RN', 'RO', 'RR', 'RS', 'SC', 'SE', 'SP', 'TO'));

ALTER TABLE
	Operadores
ADD FOREIGN KEY(ID_OperadorInclusao) REFERENCES Operadores(ID_Operador);

ALTER TABLE
	Operadores
ADD	FOREIGN KEY(ID_OperadorExclusao) REFERENCES Operadores(ID_Operador);