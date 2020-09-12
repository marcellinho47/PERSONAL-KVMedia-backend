USE KVMEDIA
GO

CREATE TABLE Perfis (

	ID_Perfil INT NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	DESC_Perfil VARCHAR(20) NOT NULL
);

CREATE TABLE Operadores (

	ID_Operador INT NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	FLAG_MasterAdmin BIT DEFAULT 0 NOT NULL,
	DESC_Login VARCHAR(60) NOT NULL,
	DESC_Senha VARCHAR(65) NOT NULL,
	DATA_Inclusao DATETIME NOT NULL DEFAULT GETDATE(),
	ID_OperadorInclusao INT NOT NULL,
	DATA_Exclusao DATETIME,
	ID_OperadorExclusao INT
);

CREATE TABLE OperadoresPerfis (

	ID_OperadorPerfil INT NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	ID_Operador INT NOT NULL,
	ID_Perfil INT NOT NULL,

	FOREIGN KEY(ID_Operador) REFERENCES Operadores(ID_Operador),
	FOREIGN KEY(ID_Perfil) REFERENCES Perfis(ID_Perfil) 
);

CREATE TABLE TiposLogradouro (

	ID_TipoLogradouro INT NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	DESC_Logradouro VARCHAR(30) NOT NULL
);

CREATE TABLE Paises (

	ID_Pais INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	DESC_Pais VARCHAR(40) NOT NULL
);

CREATE TABLE Municipios (

	ID_Municipio INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	ID_Pais INT NOT NULL,
	CODG_MunicipioIBGE INT NOT NULL,
	DESC_Municipio VARCHAR(100) NOT NULL,
	DESC_UF VARCHAR(2) NOT NULL,

	FOREIGN KEY(ID_Pais) REFERENCES Paises(ID_Pais)
);

CREATE TABLE Enderecos (

	ID_Endereco INT NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	ID_TipoLogradouro INT NOT NULL,
	DESC_Logradouro VARCHAR(100) NOT NULL,
	FLAG_PossuiNumero BIT NOT NULL DEFAULT 0,
	NUMR_Numero INT,
	NUMR_Complemento VARCHAR(100),
	DESC_Bairro VARCHAR(60),
	DESC_CEP VARCHAR(8) NOT NULL,
	ID_Municipio INT NOT NULL,

	FOREIGN KEY(ID_TipoLogradouro) REFERENCES TiposLogradouro(ID_TipoLogradouro),
	FOREIGN KEY(ID_Municipio) REFERENCES Municipios(ID_Municipio)
);

CREATE TABLE PessoasPF (

	ID_PessoaFisica INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	DESC_Nome VARCHAR(100) NOT NULL,
	DESC_Sexo VARCHAR(1) NOT NULL,
	DESC_EstadoCivil VARCHAR(25) NOT NULL,
	DESC_CPF VARCHAR(11) NOT NULL,
	DESC_RGNumero VARCHAR(20),
	DESC_RGOrgaoExp VARCHAR(20),
	DESC_UF VARCHAR(2),
	DESC_Profissao VARCHAR(60),
	ID_PaisNacionalidade INT NOT NULL,
	ID_Endereco INT NOT NULL,
	
	CHECK (DESC_EstadoCivil IN ('Solteiro (a)', 'Casado (a)', 'Separado Judicialmente', 'Divorciado (a)', 'Víuvo (a)', 'Outros')),
	CHECK (DESC_UF IN ('AC', 'AL', 'AM', 'AP', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA', 'MG', 'MS', 'MT', 'PA', 'PB', 'PE', 'PI', 'PR', 'RJ', 'RN', 'RO', 'RR', 'RS', 'SC', 'SE', 'SP', 'TO')),
	FOREIGN KEY(ID_PaisNacionalidade) REFERENCES Paises(ID_Pais),
	FOREIGN KEY(ID_Endereco) REFERENCES Enderecos(ID_Endereco)
);

CREATE TABLE PessoasPJ (

	ID_PessoaJuridica INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	DESC_RazaoSocial VARCHAR(100) NOT NULL,
	DESC_NomeFantasia VARCHAR(100) NOT NULL,
	DESC_CNPJ VARCHAR(14) NOT NULL,
	DESC_InscricaoEstadual VARCHAR(20),
	DESC_InscricaoMunicipal VARCHAR(20),
	ID_Endereco INT NOT NULL,
	ID_PessoaFisica_Representante INT NOT NULL,
	
	FOREIGN KEY(ID_Endereco) REFERENCES Enderecos(ID_Endereco),
	FOREIGN KEY(ID_PessoaFisica_Representante) REFERENCES PessoasPF(ID_PessoaFisica)
);

CREATE TABLE Contatos (

	ID_Contato INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	DESC_DDDTelefone VARCHAR(2),
	DESC_Telefone VARCHAR(9),
	DESC_DDDCelular VARCHAR(2),
	DESC_Celular VARCHAR(9),
	DESC_Email VARCHAR(60),
	DESC_EmailCorporativo VARCHAR(60)

);

CREATE TABLE Contatos_PessoasPF (

	ID_PessoaFisica INT NOT NULL,
	ID_Contato INT NOT NULL,

	CONSTRAINT PK_Contatos_PessoasPF PRIMARY KEY (ID_PessoaFisica, ID_Contato),
	FOREIGN KEY (ID_PessoaFisica) REFERENCES PessoasPF(ID_PessoaFisica),
	FOREIGN KEY (ID_Contato) REFERENCES Contatos(ID_Contato)
);

CREATE TABLE Contatos_PessoasPJ (

	ID_PessoaJuridica INT NOT NULL,
	ID_Contato INT NOT NULL,

	CONSTRAINT PK_Contatos_PessoasPJ PRIMARY KEY (ID_PessoaJuridica, ID_Contato),
	FOREIGN KEY (ID_PessoaJuridica) REFERENCES PessoasPJ(ID_PessoaJuridica),
	FOREIGN KEY (ID_Contato) REFERENCES Contatos(ID_Contato)
);

CREATE TABLE SituacoesContrato (
	
	ID_SituacaoContrato INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	DESC_Situacao VARCHAR(50) NOT NULL
);

CREATE TABLE Contratos (

	ID_Contrato INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	ID_PessoaFisica_Contratante INT,
	ID_PessoaJuridica_Contratante INT,
	ID_PessoaFisica_Representante INT,
	ID_PessoaJuridica_Contratado INT NOT NULL,
	ID_PessoaFisica_Testemunha INT,
	ID_Situacao INT NOT NULL,
	DATA_Contrato DATE NOT NULL,
	NUMR_MesesDuracao INT NOT NULL,
	DATA_PrevisaoTermino DATE NOT NULL,
	DATA_Termino DATE,
	DESC_Observacao VARCHAR(250),
	DATA_Inclusao DATETIME NOT NULL DEFAULT GETDATE(),
	ID_OperadorInclusao INT NOT NULL,
	DATA_Exclusao DATETIME,
	ID_OperadorExclusao INT, 

	FOREIGN KEY (ID_PessoaFisica_Contratante) REFERENCES PessoasPF(ID_PessoaFisica),
	FOREIGN KEY (ID_PessoaJuridica_Contratante) REFERENCES PessoasPJ(ID_PessoaJuridica),
	FOREIGN KEY (ID_PessoaFisica_Representante) REFERENCES PessoasPF(ID_PessoaFisica),
	FOREIGN KEY (ID_Situacao) REFERENCES SituacoesContrato(ID_SituacaoContrato),
	FOREIGN KEY(ID_OperadorInclusao) REFERENCES Operadores(ID_Operador),
	FOREIGN KEY(ID_OperadorExclusao) REFERENCES Operadores(ID_Operador)
);

CREATE TABLE MidiasSociais (

	ID_MidiaSocial INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	DESC_MidiaSocial VARCHAR(60) NOT NULL

);

CREATE TABLE Contratos_MidiasSociais (

	ID_Contrato_MidiaSocial INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	ID_Contrato INT NOT NULL,
	ID_MidiaSocial INT NOT NULL,
	DESC_Observacao VARCHAR(250)

	FOREIGN KEY (ID_Contrato) REFERENCES Contratos(ID_Contrato),
	FOREIGN KEY (ID_MidiaSocial) REFERENCES MidiasSociais(ID_MidiaSocial)
);

CREATE TABLE Contratos_MidiasSociais_Atividades (

	ID_Contrato_MidiaSocial_Atividade INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	ID_Contrato_MidiaSocial INT NOT NULL,
	DESC_Atividade VARCHAR(100)

	FOREIGN KEY (ID_Contrato_MidiaSocial) REFERENCES Contratos_MidiasSociais(ID_Contrato_MidiaSocial)
);

CREATE TABLE Renovacoes (

	ID_Renovacao INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	ID_Contrato INT NOT NULL,
	DATA_Renovacao DATE NOT NULL,
	NUMR_MesesDuracao INT NOT NULL,
	DATA_PrevisaoTermino DATE NOT NULL,
	DATA_Termino DATE,
	DESC_Observacao VARCHAR(250),
	DATA_Inclusao DATETIME NOT NULL DEFAULT GETDATE(),
	ID_OperadorInclusao INT NOT NULL,
	DATA_Exclusao DATETIME,
	ID_OperadorExclusao INT, 

	FOREIGN KEY (ID_Contrato) REFERENCES Contratos(ID_Contrato),
	FOREIGN KEY(ID_OperadorInclusao) REFERENCES Operadores(ID_Operador),
	FOREIGN KEY(ID_OperadorExclusao) REFERENCES Operadores(ID_Operador)
);

CREATE TABLE Renovacoes_MidiasSociais (

	ID_Renovacao_MidiaSocial INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	ID_Renovacao INT NOT NULL,
	ID_MidiaSocial INT NOT NULL,
	DESC_Observacao VARCHAR(250)

	FOREIGN KEY (ID_Renovacao) REFERENCES Renovacoes(ID_Renovacao),
	FOREIGN KEY (ID_MidiaSocial) REFERENCES MidiasSociais(ID_MidiaSocial)
);

CREATE TABLE Renovacoes_MidiasSociais_Atividades (

	ID_Renovacao_MidiaSocial_Atividade INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	ID_Renovacao_MidiaSocial INT NOT NULL,
	DESC_Atividade VARCHAR(100)

	FOREIGN KEY (ID_Renovacao_MidiaSocial) REFERENCES Renovacoes_MidiasSociais(ID_Renovacao_MidiaSocial)
);

CREATE TABLE SiteFormularios (

	ID_SiteFormulario INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	DESC_Site VARCHAR(60) NOT NULL,
	DESC_Nome VARCHAR(60) NOT NULL,
	DESC_Email VARCHAR(100) NULL,
	DESC_DDDTelefone VARCHAR(2) NULL,
	DESC_Telefone VARCHAR(10) NULL,
	DESC_Mensagem VARCHAR(1000) NOT NULL,
	DATA_Envio DATETIME NOT NULL,
	DATA_Inclusao DATETIME NOT NULL DEFAULT GETDATE(),
	ID_OperadorInclusao INT NOT NULL

	FOREIGN KEY(ID_OperadorInclusao) REFERENCES Operadores(ID_Operador)
);

CREATE TABLE ListaEmailsNewsletter (

	ID_ListaEmailNewsLetter INT NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	NUMR_OrdemEnvio INT NULL,
	FLAG_EmailAdicional BIT NOT NULL DEFAULT 0,
	FLAG_EmailAdicionalEnviado BIT NOT NULL DEFAULT 0,
	DESC_Assunto VARCHAR(60) NOT NULL,
	DESC_Corpo VARCHAR(MAX) NOT NULL,
	DATA_Inclusao DATETIME NOT NULL DEFAULT GETDATE(),
	ID_OperadorInclusao INT NOT NULL,
	DATA_Exclusao DATETIME NULL,
	ID_OperadorExclusao INT NULL,

	FOREIGN KEY(ID_OperadorInclusao) REFERENCES Operadores(ID_Operador),
	FOREIGN KEY(ID_OperadorExclusao) REFERENCES Operadores(ID_Operador)
);

CREATE TABLE ListaNewsletter (

	ID_ListaNewsletter INT NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	DESC_Email VARCHAR(100) NOT NULL,
	DESC_OrigemCadastro VARCHAR(100) NULL,
	DATA_Inclusao DATETIME NOT NULL DEFAULT GETDATE(),
	ID_OperadorInclusao INT NOT NULL,
	DATA_Exclusao DATETIME NULL,
	ID_OperadorExclusao INT NULL,

	FOREIGN KEY(ID_OperadorInclusao) REFERENCES Operadores(ID_Operador),
	FOREIGN KEY(ID_OperadorExclusao) REFERENCES Operadores(ID_Operador)
);