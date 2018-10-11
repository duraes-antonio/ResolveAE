# encoding: utf-8
import gc

from database import Database
from micro_dao.avaliacao_tabela import TabelaAvaliacao
from micro_dao.bairro_tabela import TabelaBairro
from micro_dao.cidade_tabela import TabelaCidade
from micro_dao.contato_tabela import TabelaContato
from micro_dao.contrato_tabela import TabelaContrato
from micro_dao.dia_semana_tabela import TabelaDiaSemana
from micro_dao.endereco_tabela import TabelaEndereco
from micro_dao.estado_tabela import TabelaEstado
from micro_dao.horario_prestacao_tabela import TabelaHorarioPrestacao
from micro_dao.info_profissional_tabela import TabelaInfoProfissional
from micro_dao.servico_subtipo_servico_tabela import TabelaServicoSubtipoServico
from micro_dao.servico_tabela import TabelaServico
from micro_dao.subtipo_servico_tabela import TabelaSubtipoServico
from micro_dao.tipo_contato_tabela import TabelaTipoContato
from micro_dao.tipo_info_profissional_tabela import TabelaTipoInfoProfissional
from micro_dao.tipo_servico_tabela import TabelaTipoServico
from micro_dao.usuario_tabela import TabelaUsuario


def main():

    # db = Database(qtd_user=50000, database="resolve_ae")
    # db.execute("drop schema if exists public cascade;")
    # db.execute("create schema public;")
    # db.builder_all(False)
    # db.to_arq_sql_all("./saida")

    # USUÁRIO
    usuario = TabelaUsuario()

    # ENDEREÇO
    estado = TabelaEstado()
    cidade = TabelaCidade(estado)
    bairro = TabelaBairro(cidade, estado)
    endereco = TabelaEndereco(bairro, usuario)

    # CONTATO
    tipo_contato = TabelaTipoContato()
    contato = TabelaContato(tipo_contato, usuario)

    # TABELAS INFORMAÇÃO PROFISSIONAL
    tipo_info_pro = TabelaTipoInfoProfissional()
    info_pro = TabelaInfoProfissional(usuario, tipo_info_pro)

    # TABELAS SERVIÇO PRESTACAO
    contrato = TabelaContrato(usuario)
    tipo_serv = TabelaTipoServico()
    subtipo_serv = TabelaSubtipoServico(tipo_serv)
    servico = TabelaServico(contrato, tipo_serv, subtipo_serv, usuario)
    serv_subtipo_serv = TabelaServicoSubtipoServico(servico, subtipo_serv)
    avaliacao = TabelaAvaliacao(servico, usuario)

    # TABELAS HORARIO PRESTACAO
    dia_semana = TabelaDiaSemana()
    horario_prest = TabelaHorarioPrestacao(usuario, contrato, dia_semana)

    path = "./saida/"
    usuario.gerar_arq_SQL(path, True, True)
    estado.gerar_arq_SQL(path, True, True)
    cidade.gerar_arq_SQL(path, True, True)
    bairro.gerar_arq_SQL(path, True, True)
    endereco.gerar_arq_SQL(path, True, True)

    del estado, cidade, bairro, endereco

    tipo_contato.gerar_arq_SQL(path, True, True)
    contato.gerar_arq_SQL(path, True, True)

    tipo_info_pro.gerar_arq_SQL(path, True, True)
    info_pro.gerar_arq_SQL(path, True, True)

    contrato.gerar_arq_SQL(path, True, True)
    tipo_serv.gerar_arq_SQL(path, True, True)
    subtipo_serv.gerar_arq_SQL(path, True, True)
    servico.gerar_arq_SQL(path, True, True)
    serv_subtipo_serv.gerar_arq_SQL(path, True, True)
    avaliacao.gerar_arq_SQL(path, True, True)

    dia_semana.gerar_arq_SQL(path, True, True)
    horario_prest.gerar_arq_SQL(path, True, True)

    return 0

main()
