# encoding: utf-8
from random import choice
from typing import List

from micro_dao.contato import Contato
from micro_dao.tabela_model import Tabela
from micro_dao.tipo_contato import TipoContato
from micro_dao.tipo_contato_tabela import TabelaTipoContato
from micro_dao.usuario_tabela import TabelaUsuario


class TabelaContato(Tabela):

    _num_contato_por_usuario = 1

    def __init__(self, tab_tipo_cont: TabelaTipoContato, tab_usu: TabelaUsuario,
                 preencher: bool = False):
        super().__init__("contato", Contato.get_id)
        self.add_ID(Contato.get_id)
        self.add_VARCHAR(Contato.get_descricao, "descricao", 100)
        self.add_FK(Contato.get_fk_usuario, "fk_usuario", tab_usu)
        self.add_FK(Contato.get_fk_tipo_cont, "fk_tipo_contato", tab_tipo_cont)
        self.__inserted__ = False
        if preencher: self.__preencher__(tab_tipo_cont, tab_usu)

    def __preencher__(self, tab_tipo_cont: TabelaTipoContato, tab_usu: TabelaUsuario):

        midias = ["facebook", "skype", "twitter"]

        for usuario in tab_usu.get_all():
            self.insert(
                Contato(
                    tab_tipo_cont.get_by_nome(choice(midias)).get_id(),
                    usuario.get_email().split("@")[0],
                    usuario.get_id()
                )
            )

        self.__inserted__ = True
        return self

    def get_all(self) -> List[TipoContato]:
        return super().get_all()

    def get_by_nome(self, nome: str) -> TipoContato:
        return super().buscar(TipoContato.get_nome, nome)
