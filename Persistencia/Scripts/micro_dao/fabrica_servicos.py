from random import uniform, choice, randint

from micro_dao.servico import Servico
import micro_dao.dados as dados

class FabricaServico():

	__valor_min = 15
	__valor_max = 60
	__min_anos = 2
	__max_anos = 25

	# Nomes para serem concatenados as frases dos serviços
	__aplicacoes = dados.aplicacoes
	__bancos_tools = dados.sgbds
	__dev_mob_tools = dados.dev_mobile_tools
	__dev_embarc_tools = dados.dev_embarc_tools
	__dev_desk_tools = dados.dev_desk_tools
	__dev_web_tools = dados.dev_web_tools
	__dev_frontend_tools = dados.dev_mobile_tools

	__des_graf_tools = dados.design_graf_tools
	__des_ux_tools = dados.design_ux_tools
	__des_web_tools = dados.design_web_tools

	__redes_tools = dados.redes_tools
	__redes_marcas = dados.redes_marcas
	

	# Título preparados para receber algum dos termos acima;
	__titulos_analise = [
		{"msg": "Levantamento de requisitos para {}.", "tipo": 1, "opcoes": __aplicacoes},
		{"msg": "Construo documentação de {}.", "tipo": 1, "opcoes": __aplicacoes},
		{"msg": "Realizo a modelagem de {}.", "tipo": 1, "opcoes": __aplicacoes}
	]

	__titulos_bd = [
		{"msg": "Administração de Banco de Dados: {}.", "tipo": 1, "opcoes": __bancos_tools},
		{"msg": "Análise de Banco de Dados: {}.", "tipo": 1, "opcoes": __bancos_tools},
		{"msg": "Ciência de Dados em {}.", "tipo": 1, "opcoes": __bancos_tools},
		{"msg": "Modelagem de Banco de Dados {}.", "tipo": 1, "opcoes": __bancos_tools},

		{"msg": "Há {} anos administro banco de dados {}.", "tipo": 2, "opcoes": __bancos_tools},
		{"msg": "Ciência e Análise de Dados há mais de {} anos com {}.", "tipo": 2, "opcoes": __bancos_tools},
		{"msg": "Modelagem de banco de dados há {} anos com foco em {}.", "tipo": 2, "opcoes": __bancos_tools}
	]

	__titulos_dev = [
		{"msg": "Desenvolvimento Mobile com {}.", "tipo": 1, "opcoes": __dev_mob_tools},
		{"msg": "Desenvolvimento Web com {}.", "tipo": 1, "opcoes": __dev_web_tools},
		{"msg": "Desenvolvimento Desktop usando {}.", "tipo": 1, "opcoes": __dev_desk_tools},
		{"msg": "Sistemas Embarcados com {}.", "tipo": 1, "opcoes": __dev_embarc_tools},

		{"msg": "Há {} anos desenvolvo para plataforma Mobile com {}.", "tipo": 2, "opcoes": __dev_mob_tools},
		{"msg": "Desenvolvimento Web há mais de {} anos com {}.", "tipo": 2, "opcoes": __dev_web_tools},
		{"msg": "Desenvolvimento Desktop há {} anos, usando {}.", "tipo": 2, "opcoes": __dev_desk_tools},
		{"msg": "Desenvolvo Sistemas Embarcados [Exp.: {} anos][{}]", "tipo": 2, "opcoes": __dev_embarc_tools},

		{"msg": "{} com qualidade, {} anos de sucesso e muito {}.", "tipo": 3, "opcoes": __dev_mob_tools},
		{"msg": "{} feitos por quem há mais de {} anos entende de {}.", "tipo": 3, "opcoes": __dev_frontend_tools},
		{"msg": "{} eficientes há mais de {} anos no mercado [{}].", "tipo": 3, "opcoes": __dev_desk_tools},
	]

	__titulos_design = [
		{"msg": "Desenvolvimento de layouts com {}.", "tipo": 1, "opcoes": __des_web_tools},
		{"msg": "Elaboração de interfaces ricas e agradáveis [{}].", "tipo": 1, "opcoes": __des_ux_tools},
		{"msg": "Design de produtos de forma divina graças ao {}.", "tipo": 1, "opcoes": __des_graf_tools},

		{"msg": "Há {} anos dando vida às aplicações web com {}.", "tipo": 2, "opcoes": __des_web_tools},
		{"msg": "Experiência do usuário em 1º lugar há mais de {} anos com {}.", "tipo": 2, "opcoes": __des_ux_tools},
		{"msg": "Criação de arte e design de produto há {} anos, usando {}.", "tipo": 2, "opcoes": __des_graf_tools},

		{"msg": "Dedicação no visual de {} há mais de {} anos com muito {}.", "tipo": 3, "opcoes": __des_web_tools},
		{"msg": "{} com telas acessíveis e belas, feito por quem há mais de {} anos entende de {}.", "tipo": 3, "opcoes": __des_ux_tools}
	 ]

	__titulos_redes = [
		{"id": 1, "msg": "Sniffer de rede local com {}.", "tipo": 1, "opcoes": __redes_tools},
		{"id": 2, "msg": "Monitoramento e análise de performance [{}].", "tipo": 1, "opcoes": __redes_tools},
		{"id": 3, "msg": "Atualização de firmware e análise com {}.", "tipo": 1, "opcoes": __redes_tools},
		{"id": 4, "msg": "Instalação de dispositivos {}.", "tipo": 1, "opcoes": __redes_marcas},

		{"id": 5, "msg": "Detecção de marmanjo de {} anos tentando roubar WIFI [{}].", "tipo": 2, "opcoes": __redes_tools},
		{"id": 6, "msg": "Rede segura em 1º lugar há mais de {} anos com {}.", "tipo": 2, "opcoes": __redes_tools},

		{"id": 7, "msg": "Dedicação na análise do tráfego de {} há mais de {} anos, com uso de {}.", "tipo": 3, "opcoes": __redes_tools},
		{"id": 8, "msg": "{} com segurança, feito por quem há mais de {} anos entende de {}.", "tipo": 3, "opcoes": __redes_tools}
	]

	# TODO adicionar restante dos subtipos

	def __init__(self):
		pass

	def __carregar_linhas(self, path_arquivo):

		with open(path_arquivo, "r") as arq:
			linhas = arq.readlines()

		return [linha.strip() for linha in linhas]

	def get_serv_analise(self, fk_contrato: int, fk_usuario: int):

		titulo = choice(self.__titulos_analise)
		msg = titulo["msg"].format(choice(titulo["opcoes"]))

		servico: Servico = Servico(
			uniform(self.__valor_min, self.__valor_max),
			msg, "...", fk_usuario, 1, fk_contrato)

		return servico

	def get_serv_banco_dados(self, fk_contrato: int, fk_usuario: int):

		titulo = choice(self.__titulos_bd)
		msg: str

		if (titulo["tipo"] == 1):
			msg = titulo["msg"].format(choice(titulo["opcoes"]))

		elif (titulo["tipo"] == 2):
			msg = titulo["msg"].format(randint(self.__min_anos, self.__max_anos),
			                           choice(titulo["opcoes"]))

		servico: Servico = Servico(
			uniform(self.__valor_min, self.__valor_max),
			msg, "...", fk_usuario, 2, fk_contrato)

		return servico

	def get_serv_desenvolvimento(self, fk_contrato: int, fk_usuario: int):

		titulo = choice(self.__titulos_dev)
		msg: str

		if (titulo["tipo"] == 1):
			msg = titulo["msg"].format(choice(titulo["opcoes"]))

		elif (titulo["tipo"] == 2):
			msg = titulo["msg"].format(randint(self.__min_anos, self.__max_anos),
			                           choice(titulo["opcoes"]))

		elif (titulo["tipo"] == 3):

			linguagens = []

			for i in range(randint(1, 3)):
				opcao = choice(titulo["opcoes"])

				if opcao not in linguagens:
					linguagens.append(opcao)

			msg = titulo["msg"].format(
				choice(self.__aplicacoes),
				randint(self.__min_anos, self.__max_anos),
				", ".join(linguagens))

		servico: Servico = Servico(
			uniform(self.__valor_min, self.__valor_max),
			msg, "...", fk_usuario, 3, fk_contrato)

		return servico

	def get_serv_design(self, fk_contrato: int, fk_usuario: int):

		titulo = choice(self.__titulos_design)
		msg: str

		if (titulo["tipo"] == 1):
			msg = titulo["msg"].format(choice(titulo["opcoes"]))

		elif (titulo["tipo"] == 2):
			msg = titulo["msg"].format(randint(self.__min_anos, self.__max_anos),
			                           choice(titulo["opcoes"]))

		elif (titulo["tipo"] == 3):

			linguagens = []

			for i in range(randint(1, 3)):
				opcao = choice(titulo["opcoes"])

				if opcao not in linguagens:
					linguagens.append(opcao)

			msg = titulo["msg"].format(
				choice(self.__aplicacoes),
				randint(self.__min_anos, self.__max_anos),
				", ".join(linguagens))

		servico: Servico = Servico(
			uniform(self.__valor_min, self.__valor_max), msg,
			"...", fk_usuario, 4, fk_contrato)

		return servico

	def get_serv_redes(self, fk_contrato: int, fk_usuario: int):

		titulo = choice(self.__titulos_redes)
		msg: str

		if (titulo["tipo"] == 1):
			msg = titulo["msg"].format(choice(titulo["opcoes"]))

		elif (titulo["tipo"] == 2):

			if (titulo["id"] == 5):
				msg = titulo["msg"].format(randint(self.__min_anos + 10, self.__max_anos + 10),
				                           choice(titulo["opcoes"]))

			else:
				msg = titulo["msg"].format(randint(self.__min_anos + 10, self.__max_anos + 10),
				                           choice(titulo["opcoes"]))

		elif (titulo["tipo"] == 3):

			linguagens = []

			for i in range(randint(1, 3)):
				opcao = choice(titulo["opcoes"])

				if opcao not in linguagens:
					linguagens.append(opcao)

			msg = titulo["msg"].format(
				choice(self.__aplicacoes),
				randint(self.__min_anos, self.__max_anos),
				", ".join(linguagens))

		servico: Servico = Servico(
			uniform(self.__valor_min, self.__valor_max), msg,
			"...", fk_usuario, 5, fk_contrato)

		return servico

