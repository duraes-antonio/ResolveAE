# encoding: utf-8
import random
import datetime as dt
from faker import Faker

# Classe que se destina a gerar dicionários que representam "Value" de uma tabela;
class Random():
    
    faker_gen = Faker("pt_br")

    def gerar_endereco(self):

        endereco = self.faker_gen.address().split('\n')
        bairro = endereco[1]
        cep_cidade_estado = endereco[2].split(" / ")
        cep = cep_cidade_estado[0][:9]
        cidade = endereco[0][9:].strip()
        estado = endereco[1]

        return [bairro, cidade, estado]

    # Retorna data no padrão internacional 'ano/mês/dia'
    @staticmethod
    def gerar_date(year: int):
        return dt.datetime(year, random.randint(1, 12), random.randint(1, 28)).strftime("%Y/%m/%d")

    # Retorna horário no padrão 'HH/MM/SS'
    @staticmethod
    def gerar_horario(hora_min, hora_max, minuto_min, minuto_max):
        return dt.time(random.randint(hora_min, hora_max),
                       random.randint(minuto_min, minuto_max)).strftime("%H:%M:%S")

    ##INSTACIACAO DOS ITENS DO BANCO
    def get_random_user(self):
        return {"email": self.faker_gen.email(), "nome": self.faker_gen.name(),
                "senha": "null", "sobre": "..."}
    
    def get_random_endereco(self):
        endereco = self.gerar_endereco()
        return {"bairro": endereco[0], "cidade": endereco[1], "estado": endereco[2]}

    def get_random_cidade(self):
        return {"nome": self.faker_gen.city()}

    def get_random_estado(self):
        return {"nome": self.faker_gen.state()}

    def get_random_servico_prestacao(self):
        return {"descricao": self.faker_gen.sentence(), "valor": random.uniform(20,60), "titulo": self.faker_gen.sentence()}
    
    def get_random_tipo_servico(self):
        return {"descricao": self.faker_gen.sentence()}
    
    def get_random_subtipo_servico(self):
        return {"descricao":self.faker_gen.sentence()}
    
    def get_random_contrato(self):
        contrato = {"dataContrato": self.gerar_date(2018),"dataInicioPrestacao":self.gerar_date(2018),"dataFimPrestacao":self.gerar_date(2018),"dataUltModif":self.gerar_date(2018)}
        return contrato
    
    def get_random_avaliacao(self):
        return {"nota":random.randint(1,5), "comentario":self.faker_gen.sentence()}
    
    def get_random_horario_prestacao(self):
        return {"diaSemana":random.randint(1,7),
                "horarioPrestacaoInicio": self.gerar_horario(1, 12, 0, 59),
                "horarioPrestacaoFim": self.gerar_horario(13, 23, 0, 59),
                "disponivel": random.choice([True, False])}
    
    def get_random_info_profissional(self):
        info_prestacao = {"tipoInfo":random.randint(1,2),"descricao":self.faker_gen.sentence(),"dataInicio":self.gerar_date(2011),"dataFim":self.gerar_date(2016)}
        return info_prestacao
